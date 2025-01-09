<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDto dto = (MemberDto)request.getAttribute("dto");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/member.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원 한명의 정보</h1>
		<p>번호 : <%=dto.getNum() %>이름 : ${dto.getName() } 주소 : ${dto.getAddr()}</p>	
		<%--EL 을 이용해서 Scope 에 담긴 내용을 추출할 수 있다 --%>
		<p>이름: ${requestScope.dto.getNum() }</p>
		<%--requestScope. 은 생략 가능--%>
		<p>번호: ${dto.getName() }</p>
		<%-- .필드명 만 명시해도 getter 메소드 자동 호출 --%>
		<p>주소: ${dto.addr }</p>
	</div>
</body>
</html>