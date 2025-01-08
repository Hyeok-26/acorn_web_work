<%@page import="test.food.dto.FoodDto"%>
<%@page import="java.util.List"%>
<%@page import="test.food.dao.FoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//음식 목록을 FoodDao 객체를 이용해서 얻어온다
	FoodDao dao = new FoodDao();
	List<FoodDto>list = dao.getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>음식 목록입니다</h1>
		<a href="insertform.jsp">음식 추가</a>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>종류</th>
					<th>이름</th>
					<th>가격</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<%for(FoodDto tmp:list){%>
					<tr>
						<!-- 그때마다 달라지는 동적인 내용 -->
						<td><%=tmp.getNum()%></td>
						<td><%=tmp.getType() %></td>
						<td><%=tmp.getName() %></td>
						<td><%=tmp.getPrice() %></td>
						<td><a href="updateform.jsp?num=<%=tmp.getNum()%>">수정</a></td>
						<!-- javascript:영역 -->
						<td><a href="javascript:deleteConfirm(<%=tmp.getNum()%>)">삭제</a></td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</div>
	<script>
		//매개변수을 설정하여 함수가 각 num 에 맞는 주소를 찾아갈 수 있게 설정
		function deleteConfirm(num){
			const isDelete = confirm("삭제할까요?");
			if(isDelete){
				//javascript 를 이용해서 페이지 이동
				location.href = "delete.jsp?num="+num;	
			}
		}
	</script>
</body>
</html>