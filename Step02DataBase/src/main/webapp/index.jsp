<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<%--이 부분은 resource.jsp 페이지가 응답하도록 한다 --%>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>			<!-- flex 요소로 만들어 컬럼 조정 min-vh-100 높이 100프로 -->
<body class="d-flex flex-column min-vh-100">
	<%--남는 공간이 있으면 다 가지도록(스티키 푸터) --%>
	<div class="main flex-grow-1">
		<%--이 부분은 navbar.jsp 페이지가 응답하도록 한다 --%>
		<jsp:include page="/include/navbar.jsp">
			<jsp:param value="index" name="current" />
		</jsp:include>
		<div class="container">
			<h1>인덱스 페이지입니다</h1>
			<ul>
				<li><a href="connection/test.jsp">Connection 테스트</a></li>
				<li><a href="member/list.jsp">회원 목록 보기</a></li>
				<li><a href="friends">친구 목록</a></li>
				<li><a href="friends.jsp">친구목록</a></li>
				<li><a href="food/list.jsp">맛집 목록</a></li>
			</ul>
		</div>
	</div>
	
	<%-- footer.jsp include --%>
	<jsp:include page="/include/footer.jsp"/>
</body>
</html>