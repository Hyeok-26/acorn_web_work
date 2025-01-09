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
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body class="d-flex flex-column min-vh-100">
	<jsp:include page="/include/navbar.jsp">
		<jsp:param value="food" name="current"/>
	</jsp:include>
	<div class="container main flex-grow-1">	
		<div style="display: flex; justify-content: space-between; align-items: center;">
			<h1 class="mt-2" style="margin-right: 10px;">음식 목록</h1>
			<a href="insertform.jsp" >
				<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-plus-circle-fill" viewBox="0 0 16 16">
	  				<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3z"/>
				</svg>
				음식 추가
			</a>
		</div>
		<table class="table table-borederd">
			<thead class="table-dark">
				<tr>
					<th>번호</th>
					<th>종류</th>
					<th>이름</th>
					<th>가격</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody style="text-align">
				<%for(FoodDto tmp:list){%>
					<tr>
						<!-- 그때마다 달라지는 동적인 내용 -->
						<td><%=tmp.getNum()%></td>
						<td><%=tmp.getType() %></td>
						<td><%=tmp.getName() %></td>
						<td><%=tmp.getPrice() %></td>
						<td>
							<a href="updateform.jsp?num=<%=tmp.getNum()%>">
								<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
  									<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
  									<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
								</svg>
							</a>
						</td>
						<!-- javascript:영역 -->
						<td>
							<a href="javascript:deleteConfirm(<%=tmp.getNum()%>)">
								<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-x-square-fill" viewBox="0 0 16 16">
 									 <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708"/>
								</svg>
							</a>
						</td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</div>
	<jsp:include page="/include/footer.jsp"/>
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