<%@page import="test.guest.dto.GuestDto"%>
<%@page import="test.guest.dao.GuestDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//방명록 글 목록을 얻어낸다
	List<GuestDto>list = GuestDao.getInstance().getList();
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
		<jsp:param value="guest" name="current"/>
	</jsp:include>
	<div class="container main flex-grow-1">
		<div style="display: flex; justify-content: space-between; align-items: center;">
			<h1 class="mt-2" style="margin-right: 10px;">방명록 목록</h1>
			<a href="insertform.jsp" >
				새글 추가
			</a>
		</div>
		<table class="table table-borederd">
			<thead class="table-dark">
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>내용</th>
					<th>등록일</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<%for(GuestDto tmp:list){ %>
					<tr>
						<td><%=tmp.getNum() %></td>
						<td><%=tmp.getWriter() %></td>
						<td>
							<textarea class="form-control" style="height:80px; resize:none;" readonly><%=tmp.getContent() %></textarea>
						</td>
						<td><%=tmp.getRegdate() %></td>
						<td>
							<a href="updateform.jsp?num=<%=tmp.getNum()%>">수정</a>
						</td>
						<td>
							<form action="delete.jsp" method="post">
								<input type="hidden" name="num" value="<%=tmp.getNum() %>" />
								<input type="text" name="pwd" placeholder="비밀번호..." />
								<button class="btn btn-danger btn-sm" type="submit">삭제</button>
							</form>
						</td>
					</tr>
				<%} %>
			</tbody>
		</table>
	</div>
	<jsp:include page="/include/footer.jsp"/>
</body>
</html>