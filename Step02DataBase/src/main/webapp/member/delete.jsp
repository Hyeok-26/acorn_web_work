<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//1.get 방식 파라미터로 전달되는 삭제할 회원의 번호를 얻어온다
	int num = Integer.parseInt(request.getParameter("num"));
	//2.MemberDao 객체를 이용해서 실제 DB 에서 삭제
	MemberDao dao = new MemberDao();
	boolean isSuccess = dao.delete(num);
	//3.응답하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/delete.jsp</title>
</head>
<body>
	<div class="contianer">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<p><strong><%=num %></strong>번 회원의 정보 삭제!
			<a href="list.jsp">목록보기</a>
			</p>
		<%}else{ %>
			<p>회원정보 저장 실패!
			<a href="list.jsp">삭제 실패했는데요?</a>
			</p>
		<%} %>
	</div>
</body>
</html>