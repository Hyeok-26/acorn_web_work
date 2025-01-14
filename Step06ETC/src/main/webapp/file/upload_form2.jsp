<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/file/upload _form2.jsp</title>
</head>
<body>
	<div class="container">
		<h3>이미지 업로드 폼</h3>
		<form action="${pageContext.request.contextPath }/file/upload2" method="post" enctype = "multipart/form-data">
			<input type="text" name="title" placeholder=" 설명 입력..."/><br />
			이미지 <input type="file" name="myImage" accept="image/*"/><br />
			<button type="submit">업로드</button>		<!-- 모든 이미지 파일의 확장자를 가르킨다 -->
		</form>
	</div>
</body>
</html>