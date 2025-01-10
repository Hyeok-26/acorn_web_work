<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<button id="getBtn">친구 이름 목록 받아오기</button>
	<ul id="friendList">
		
	</ul>
	
	<!-- jquery 로딩딩-->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<script>
		$("#getBtn").on("click",()=>{
			fetch("friends.jsp")
			.then(res=>res.json())
			//data는 친구 이름이 순차적으로 들어있는 배열
			.then(data=>{
				//반복문 돌면서
				for(let i=0;i<data.length;i++){
					/*
						const li = document.createElement("li");
						li.innerText = data[i];
						$("#friendList").append(li);
					*/
					//li 요소를 만들어서 선택하고 innerText  출력한 후 jquery 객체(배열)을 상수에 담기
					const li = $("<li></li>").text(data[i]);
					//id 가 friendList 인 요소에 추가하기
					$("#friendList").append(li);
				}
				
			})
			.catch(error=>{
				console.log("에러 정보:"+error);
			});
		})
	
	</script>
</body>
</html>