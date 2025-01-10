<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test01.jsp</title>
</head>
<body>
	<h3>fetch 함수 테스트</h3>
	<button id="myBtn">눌러보셈</button>
	<script>
	//필요하다면 함수로 요청 가능 
		document.querySelector("#myBtn").addEventListener("click",()=>{
			// javascript 로 서버에 요청하기
			/*
				서버(jsp or sevlet) 에서 응답한 문자열이 json 형식이면
				return res.json();
			
				그 이외의 형식이면 html, xml, plain text 등등
				return res.text();
			*/
			
			fetch("${pageContext.request.contextPath }/index.jsp")
			.then(function(res){
				return res.text();
			})
			.then(function(data){
				console.log(data);
			});
			
			//페이지 전환없이 javascript 로 요청을 하면 응답된 문자열이 두번쨰
			//then() 에 전달한 함수의 매개변수에 전달된다.
			fetch("${pageContext.request.contextPath }/index.jsp")
			.then(res=>{
				return res.text();
			})
			.then(data=>{
				/*
					위의 then() 함수에서 res.json() 을 리턴하면 data는 응답된 json문자열을
					JSON.parse() 과정을 이미 거친 object 나  array 이다
					
					위의 then() 함수에서 res.text() 를 리턴하면 data 는 서버가 응답한 문자열(String) 이다
				*/
				console.log(data);
			});
			
			fetch("${pageContext.request.contextPath }/index.jsp")
			.then(res=>res.text())
			.then(data=>{
				console.log(data);
			});
		});
	</script>
</body>
</html>