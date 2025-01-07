<%@page import="test.dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<String>name = new ArrayList();
	name.add("김구라");
	name.add("주뎅이");
	name.add("원숭이");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/friends.jsp</title>
</head>
<body>
	<%for(int i=0;i<3;i++){ %>
	<h1>친구목록<%=i %></h1>
	<ul>
		<%for(String tmp:name){%>
			<li><%=tmp%></li>
		<%}%>
	</ul>
	<%} %>
</body>
</html>