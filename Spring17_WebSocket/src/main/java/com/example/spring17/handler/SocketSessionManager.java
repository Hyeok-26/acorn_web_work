package com.example.spring17.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SocketSessionManager {
	// Thread Safe 한 동기화된 리스트 객체 사용하기(웹소켓 접속한 모든 클라이언트의 session 이 저장되어있다) 
	List<WebSocketSession> sessionList=Collections.synchronizedList(new ArrayList<>());
	/*
	 *  userName <=> SocketSession 를 저장시키기 위한 Map
	 *  ConcurrentHashMap 객체도 Thread Safe 한 동기화된 Map 객체
	 *  (대화방에 입장한 모든 클라이언트의 session 과 userName 이 저장되어있다)
	 */
	//유저네임으로 소켓id 찾기
	Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
	//소켓id로 username 찾기
	Map<WebSocketSession, String> sessionUsers = new ConcurrentHashMap<>();
	//객체 <=> json 상호 변경할수 있는 객체
	ObjectMapper mapper = new ObjectMapper();
	
	//대화방에 참여한 모든 userName 목록을 리턴하는 메소드
	public List<String> getAllUserNames(){
		//Map 에 있는 모든 키(userName)값을 Set<String> 으로 얻어내기
		Set<String> keySet = userSessions.keySet();
		//Set 에 들어있는 내용을 이용해서 List 얻어내기
		List<String> userList = new ArrayList<String>(keySet);
		return userList;
	}
	
	//대화방에 참여한 user 의 session 을 저장하는 메소드
	public void enterUser(String userName,WebSocketSession session) {
		userSessions.put(userName, session);
		sessionUsers.put(session, userName);
	}
	// userName 을 전달하면 해당 Session 을 리턴해주는 메소드
	public WebSocketSession getUserSession(String userName) {
		return userSessions.get(userName);
	}
	//session 을 전달하면 해당 session 을 사용하는 userName 을 리턴해주는메소드
	public String getSessionUser(WebSocketSession session) {
		return sessionUsers.get(session);
	}
	
	//모든 user session 정보를 리턴하는 메소드
	public Map<String, WebSocketSession> getAllUserSession(){
		return userSessions;
	}
	//userName 을 Map 에서 제거하는 메소드
	public void removeUser(String userName) {
		//제거하고 제거된 session 이 리턴
		WebSocketSession removedSession = userSessions.remove(userName);
		//sessionUsers 에서 해당 정보를 제거하기
		sessionUsers.remove(removedSession);
		//누가 퇴장했는지 TextMessage 를 방에 입장한 모든 클라이언트에게 전송한다.
		Map<String, Object> map = Map.of(
			"type","leave",
			"payload",Map.of(
				"userName",userName
			)
		);
		//json 으로 변경하고
		String json= "{}";
		try {
			//json 문자열을 이쁘게 출력
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		TextMessage msg = new TextMessage(json);
		broadcast(msg);
	}
	//대화방에 입장한 모든 session 에 TextMessage 를 중계하는 메소드
	public void broadcast(TextMessage msg) {
		//Map 에 저장된 모든 key,value 를 순회하면서
		sessionUsers.forEach((key, value)->{
			//key:session value: userName
			try {
				key.sendMessage(msg);
			}catch(IOException e){
				e.printStackTrace();
			}
		});
	}
	//특정 session 에만 TextMessage 를 전송하는 메소드
	public void privateMessage(String userName, TextMessage msg) {
		//userName 에만 보낼수 있는 session 을 얻어내서
		WebSocketSession session = userSessions.get(userName);
		//만일 없으면 메소드 종료
		if(session ==null)return;
		try {
			session.sendMessage(msg);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void register(WebSocketSession session) {
		sessionList.add(session);
	}
	
	public void remove(WebSocketSession session) {
		sessionList.remove(session);
	}
	
	public List<WebSocketSession> getSessions(){
		return sessionList;
	}
}