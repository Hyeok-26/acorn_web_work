<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//get 방식 파라미터 url 이라는 이름으로 전달되는 값이 잇는지 읽어와본다
	String url = request.getParameter("url");
	//만일 넘어오는 값이 없다면
	if(url==null){
		String cPath = request.getContextPath();
		url = cPath+"/index.jsp";
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login-form.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>로그인 폼</h1>
		<form action="${pageContext.request.contextPath }/user/login.jsp" method="post">
			<%--로그인 후 이동할 페이지 정보도 같이 전송되독록 한다 --%>
			<input type="hidden" name="url" value="<%=url%>"/>
			<div>
				<label for="userName">아이디</label>
				<input type="text" name="userName" id="userName"/>
			</div>
			<div>
				<label for="password">비밀번호</label>
				<input type="password" name="password" id="password"/>
			</div>
			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>