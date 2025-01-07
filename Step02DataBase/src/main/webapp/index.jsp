<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지입니다</h1>
		<ul>
			<li><a href="member/list.jsp">친구 목록 보기</a></li>
			<li><a href="friends">친구 목록</a></li>
			<li><a href="friends.jsp">친구목록</a></li>
			<li><a href="connection/test.jsp">Connection 테스트</a></li>
		</ul>
		<form action="send" method="get">
            <label for="msg">메세지</label>
            <input type="text" name="msg"placeholder="입력...">
            <button type="submit">전송</button>
            <button type="reset">취소</button>
        </form>
        <form action="send" method = "get">
        	<h1>회원가입 폼</h1>
        	<label for="name">이름</label>
        	<input type="text" name="name" placeholder="이름 입력..."/>
        	<label for="email">이메일</label>
        	<input type="email" name="email" placeholder="이메일 입력..." />
        	<button type = "submit">가입</button>
        </form>
	</div>
</body>
</html>