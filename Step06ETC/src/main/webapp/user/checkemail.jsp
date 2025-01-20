<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String email = request.getParameter("email");
	
	boolean canUse = false;
	
	if(!email.equals("gura@naver.com")){
		canUse=true;
	}
%>
{
	"canUse":<%=canUse %>
}