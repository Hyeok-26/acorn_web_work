<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	int num = Integer.parseInt(request.getParameter("num"));
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	MemberDto dto = new MemberDto();
	dto.setNum(num);
	dto.setName(name);
	dto.setAddr(addr);
	MemberDao dao = new MemberDao();
	boolean isSuccess = dao.update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/update.jsp</title>
</head>
<body>
	<div class="contianer">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<p>회원정보를 수정 완료했습니다
			<a href="list.jsp">목록보기</a>
			</p>
		<%}else{ %>
			<p>회원정보 수정 실패!
			<a href="list.jsp">목록으로 돌아가기</a>
			</p>
		<%} %>
	</div>
</body>
</html>