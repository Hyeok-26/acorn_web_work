<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sticky Footer Example</title>

<style>
	html, body {
	    height: 100%; /* 전체 페이지 높이를 100%로 설정 */
	    margin: 0; /* 기본 마진 제거 */
	    display: flex;
	    flex-direction: column;
	}
	
	main {
	    flex-grow: 1; /* 메인 콘텐츠가 나머지 공간을 차지하게 설정 */
	}
	
	footer {
	    background-color: #343a40; /* 어두운 배경색 */
	    color: white; /* 흰색 텍스트 */
	    text-align: center; /* 가운데 정렬 */
	    padding: 10px 0; /* 상하 여백 추가 */
	}
</style>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body class="d-flex flex-column min-vh-100">
	<!-- Navbar -->
	<jsp:include page="/include/navbar2.jsp"></jsp:include>
	<!-- Main Content -->
	
	<!-- Footer -->
	<jsp:include page="/include/footer.jsp" />
</body>
</html>