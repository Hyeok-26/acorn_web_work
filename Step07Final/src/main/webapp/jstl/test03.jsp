<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setAttribute("jumsu", 50);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="true">
		<p>이 문장이 출력될까?</p>
	</c:if>
	<c:if test="false">
		<p>이 문장이 출력될까???</p>
	</c:if>
	<c:if test="${10%2 eq 0 }">
		<p>10 은 짝수입니다</p>
	</c:if>
	<c:if test="${requestScope.jumsu%2 eq 0} ">
		<p>${jumsu} 는 짝수 입니다</p>
	</c:if>
	
 	<p>획득한 점수는  <strong>${jumsu}</strong>입니다</p>
 	<p>
 		따라서 이번 학기 학점은
 		<c:choose>
			<c:when test="${jumsu ge 90} ">A</c:when>
			<c:when test="${jumsu ge 80}">B</c:when>
			<c:when test="${jumsu ge 70}">C</c:when>
			<c:when test="${jumsu ge 60}">D</c:when>
			<c:otherwise>F</c:otherwise>	 		
 		</c:choose>
 		입니다
	</p>
	
	<p>
		<strong>${jumsu} </strong>는
		<c:choose>
			<c:when test="${requestScope.jumsu%2 eq 0 }">짝수수</c:when>
			<c:otherwise>홀수</c:otherwise>
		</c:choose>
		입니다
	</p>
</body>
</html>