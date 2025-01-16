<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//get 방식 파라미터로 전달되는 입력한 id 값
	String id =request.getParameter("id");
	//db에서 해당 회원의 정보가 있는디 확인해서 사용가능 여부를 알아낸다
	boolean canUse = false;
	//"kimgura" 라는 아이디는 이미 등록되어 있다고 생각하면 된다
	if(!id.equals("kimgura")){
		canUse=true;
	}
%>
{
	"canUse":<%=canUse %>
}