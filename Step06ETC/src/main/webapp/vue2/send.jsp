<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//요청 파라미터 얻어오기
	String msg = request.getParameter("msg");
	//익어온 내용을 콘솔에 출력하기
	System.out.println(msg);
	//json문자열 응답

%>
{
	"fromServer":"메세지 자~알 받았습니다!"
}