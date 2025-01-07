<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<String> name = new ArrayList<>();
	name.add("이강권");
	name.add("송창엽");
	name.add("이우진");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>friends.jsp</title>
</head>
<body>
	<h1>나의 칭구들</h1>
	<ul>
		<%for(String tmp:name){ %>
			<li><%=tmp %></li>
		<%} %>
	</ul>
</body>
</html>