<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		//get 압식 parameter 로 전달되는 회원번호 추출(updateform.jsp?num=x )
		int num = Integer.parseInt(request.getParameter("num"));
		//num 에 해당되는 회원 정보를 MemberDao 객체를 이용해서 얻어온다	
		MemberDto dto = new MemberDao().getData(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/member/updateform.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath }">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath }/member/list.jsp">회원목록</a>
				</li>
				<li class="breadcrumb-item active">회원 수정</li>
			</ol>
		</nav>
		<h1>회원 정보 수정</h1>
		<form action="${pageContext.request.contextPath}/member/update.jsp" method="post">
			<div class="mb-3">
				<label class="form.label" for="num">번호</label>
				<!-- 숫자는 건들지 말아야 되기 때문에 readonly 를 붙인다 -->
				<input class="form-control" type="text" name="num" value="<%= dto.getNum() %>" readonly/>
			</div>
			<div class="mb-3">
				<label class="form.label" for="name">이름</label>
				<input class="form-control" type="text" name="name" id="name" value="<%= dto.getName() %>"/>
			</div>
			<div class="mb-3">
				<label class="form.label" for="addr">주소</label>
				<input class="form-control" type="text" name="addr" id="addr" value="<%= dto.getAddr() %>"/>
			</div>		
			<button class="btn btn-success" type="submit">수정</button>
			<!-- 원래의 value 상태로 돌아가기 -->
			<button class="btn btn-danger" type="reset">취소</button>
		</form>
	</div>
</body>
</html>