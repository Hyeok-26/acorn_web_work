<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/insertform.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath }">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath }/food/list.jsp">음식 목록</a>
				</li>
				<li class="breadcrumb-item active">음식 추가</li>
			</ol>
		</nav>
		<h1>맛집</h1>
		<form action="${pageContext.request.contextPath}/food/insert.jsp"
			method="post">
			<label class="form-label" for="type">카테고리</label> 
			<select class="form-select" name="type" id="type">
				<!-- 둘이 같다면 value 따로 안 적어도 된다 -->
				<option value="">선택</option>
				<option value="한식">한식</option>
				<option>중식</option>
				<option>일식</option>
				<option>양식</option>
				<option>기타</option>
			</select>
			<div class="mb-3">
				<label class="form-label" for="name">이름</label> 
				<input class="form-control" type="text" name="name" />
			</div>
			<div class="mb-3">
				<label class="form-label" for="price">가격</label> 
				<input class="form-control" type="number" name="price" step="100" min="1000" max="100000" />
			</div>
			<button class="btn btn-primary" type="submit">추가욤</button>
		</form>
	</div>
</body>
</html>