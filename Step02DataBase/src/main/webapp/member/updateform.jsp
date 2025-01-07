<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		int num = Integer.parseInt(request.getParameter("num"));
		MemberDao dao = new MemberDao();
		MemberDto dto = null;
		
		for (MemberDto mem : dao.getList()) {
	        if (mem.getNum() == num) {
	            dto = mem;
	            break;
	        }
	    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/member/updateform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원 정보 수정</h1>
		<form action="${pageContext.request.contextPath}/member/update.jsp" method="post">
			<input type="hidden" name="num" value="<%= dto.getNum() %>" />
			<div>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" value="<%= dto.getName() %>" placeholder="이름 입력..." />
			</div>
			<div>
				<label for="addr">주소</label>
				<input type="text" name="addr" id="addr" value="<%= dto.getAddr() %>" placeholder="주소 입력..." />
			</div>		
			<button type="submit">수정</button>
		</form>
	</div>
</body>
</html>