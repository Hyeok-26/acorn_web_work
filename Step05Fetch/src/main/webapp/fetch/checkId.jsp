<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = "acor";
	String inputid = request.getParameter("id");
	System.out.println(inputid);
	String Message;
	if(id.equals(inputid)){
		Message="이미 존재하는 아이디입니다";
	}else{
		Message="생성 가능한 아이디입니다";
	}
    // 서버에서 클라이언트에게 반환할 메시지
    out.print(Message);
%>