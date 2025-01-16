<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user/signup_form.jsp</title>
<style>
	.invalid-feedback{
		display: none;
		color: red;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>회원가입 폼 입니다</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id"/>
				<small>영문자 소문자로 시작하고 5~10 글자 이내로 입력하세요</small>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다</div>
			</div>
			
			<button type="submit">가입</button>
		</form>
	</div>
	<script>
		/*
			폼에 submit 이벤트가 발생하묜 입력한 내용을 검증해서 조건을 만족하지 못하면
			폼 제출을 만는 예제
		*/
		//아이디를 검증할 정규 표현식
		const reg_id = /^[a-z].{4,9}$/;
		//폼 안에 있는 submit 버튼을 누르면 form 이 제출되는 기본 동작을 한다
		document.querySelector("button[type=submit]").addEventListener("click",(event)=>{
			//입력한 아이디를 읽어온다
			const idCheck = document.querySelector("#id").value;
			//아이디의 패턴을 확인하고 정규표현식에 막혔다면
			if (!reg_id.test(idCheck)) {
				event.preventDefault();
				//.invalid-feedback인 div 를 보이게 해보세요
				document.querySelector(".invalid-feedback").style.display="block";
				
			}else{
				document.querySelector(".invalid-feedback").style.display="none";
			}
		});
		/*
			document.querySelector("#signupForm").addEventListener("submit",(event)=>{
				const idCheck = document.querySelector("#id").value;
				
				if (!reg_id.test(idCheck)) {
					event.preventDefault();
				}
			});
		*/
	</script>
</body>
</html>