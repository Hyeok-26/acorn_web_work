<%@page import="test.food.dao.FoodDao"%>
<%@page import="test.food.dto.FoodDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	
	//폼 전송되는 파라미터 읽어오기
	int num = Integer.parseInt(request.getParameter("num"));
	String type = request.getParameter("type");	
	String name = request.getParameter("name");
	int price = Integer.parseInt(request.getParameter("price"));
	//FoodDto 에 담기
	FoodDto dto = new FoodDto(num, type, name, price);
	//DB 에 수정 반영하기
	FoodDao dao = new FoodDao();
	boolean isSuccess = dao.update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		<%if(isSuccess){ %>
			alert("수정했어요~~~");
			location.href = "list.jsp";//목록 페이지로 이동,특정 페이지로 리다이렉트한다
		<%}else{ %>
			alert("수정 실패..ㅠㅠ");
			window.location.href = "updateform.jsp?num=<%=num%>";//window 생략 가능
		<%} %>
	</script>
</body>
</html>