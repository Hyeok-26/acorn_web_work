<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/insertform.jsp</title>
</head>
<body>
	<h1>맛집</h1>
	<form action="${pageContext.request.contextPath}/food/insert.jsp" method="post">
		<label for="type">카테고리</label>
        <select name="type" id="type">
        		<!-- 둘이 같다면 value 따로 안 적어도 된다 -->
                <option value="">선택</option>
                <option value="한식">한식</option>
                <option>중식</option>
                <option>일식</option>
                <option>양식</option>
                <option>기타</option>
        </select>
		<div>
			<label for="name">이름</label>
			<input type="text" name="name"/>
		</div>
		<div>
			<label for="price">가격</label>
			<input type="number" name="price" step="100" min="1000" max="100000"/>
		</div>
		<button type="submit">추가욤</button>
	</form>
</body>
</html>