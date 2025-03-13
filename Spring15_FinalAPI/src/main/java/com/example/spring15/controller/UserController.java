package com.example.spring15.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring15.util.JwtUtil;
import com.example.spring15.dto.UserDto;

@RestController
public class UserController {
	
	@Autowired JwtUtil jwtUtil;
	//SecurityConfig 클래스에서 Bean 이된 AuthenticationManager 객체 주입밤기	
	@Autowired AuthenticationManager authManager;
	
	//토큰을 받는 메소드
	@PostMapping("/auth")
	public ResponseEntity<String> auth(@RequestBody UserDto dto) throws Exception{
		Authentication authentication=null;
		try{
			UsernamePasswordAuthenticationToken authToken=
					new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
				//인증 매니져 객체를 이용해서 인증 진행
			authentication = authManager.authenticate(authToken);
		}catch(Exception e){
			//예외가 발생하면 인증 실패(아이디 혹은 비밀번호 틀림 등등)
			e.printStackTrace();
			//401 UNAUTHORIZED 에러를 응답하면서 문자열 한줄 보내기
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패!");
		}
		//Authentication 객체에는 인증된 사용자 정보가 들어있다. userName, role 등등
		//현재는 role 을 하나만 부여하기 떄문에 0번 방에 있는 데이터만 불러오면 된다
		GrantedAuthority authority= authentication.getAuthorities().stream().toList().get(0);
		//ROLE_XXX형식
		String role = authority.getAuthority();
		//role 이라는 키 값으로 Map 에 담기
		Map<String, Object> claims = Map.of("role", role);
		
		//에외가 발생하지 않고 여기 까지 실행순서가 넘어오면 인증을 통과 했으므로 토큰을 발급해서 응답한다.
		String token=jwtUtil.generateToken(authentication.getName(), claims);
		System.out.println(authentication+"값이 없는데요?");
		//발급받은 토큰 문자열을 ResponseEntity 에 담아서 리턴한다
		return ResponseEntity.ok("Bearer "+token);
	}
}
