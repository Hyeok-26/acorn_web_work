<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button id="getBtn">회원정보 가져오기</button>
	<p>번호: <strong id="num"></strong></p>
	<p>이름: <strong id="name"></strong></p>
	<p>주소:	<strong id="addr"></strong></p>
	<p>번호: <strong id="tmp"></strong> </p>
	<p>이름: <strong id="tmp1"></strong> </p>
	<p>주소: <strong id="tmp2"></strong> </p>
	
	
	<!-- jquery 로딩딩-->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		
		$("#getBtn").on("click",()=>{
			fetch("member.jsp")
			.then(res=>res.json())
			.then(data=>{
				document.querySelector("#num").textContent = data.num;
				document.querySelector("#name").textContent = data.name;
				document.querySelector("#addr").textContent = data.addr;
				//jquery 의 text() 동작을 이용해서 data object 에 담긴 정보를 innerText 에 출력
				$("#tmp").text(data.num);
				$("#tmp1").text(data.name);
				$("#tmp2").text(data.addr);
			})
			.catch(error=>{
				console.log("에러 정보:"+error);
			});
		});
	</script>
</body>
</html>