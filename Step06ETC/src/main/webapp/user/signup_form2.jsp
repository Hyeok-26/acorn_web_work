<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user/signup_form2.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>회원가입 폼 입니다</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div>
				<label class="form-label"for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"/>
				<small class="form-text">영문자 소문자로 시작하고 5~10 글자 이내로 입력하세요</small>
				<div class="valid-feedback">잘 입력했군요! 짱구가 아니네요~</div>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다</div>
			</div>
			
			<button class="btn btn-success" type="submit" disabled="disabled">가입</button>
		</form>
	</div>
	<script>
	
		//state(상태)값을 관리하는 변수
		//아이디 유효 여부를 관리할 변수를 만들고 초기값 부여
		let isIdValid = false;
		//비밀번호 유효성 여부를 관리항 변수를 만들고 초기값 부여
		let isPwdValid = false;
		// 이메일 유효성 여부를 관리할 변수를 만들고 초기값 부여
		let idEmailValid = false;
		
		const checkForm=()=>{
			//상태값에 따른 동작을 하도록 분기한다
			//폼 전체의 유효성 여부에 따라 분기한다(지금은 id 유효성 여부만)
			if(isIdValid){
				//type 속성이 submit 인 요소를 찾아서 disabled 속성을 제거
				document.querySelector("[type=submit]").removeAttribute("disabled");
				
			}else{
				//type 속성이 submit 인 요소를 찾아서 "disabled","disabled" 속성을 추 가
				document.querySelector("[type=submit]").setAttribute("disabled","disabled");
				
			}
			
		};
				//아이디를 검증할 정규 표현식
		const reg_id = /^[a-z].{4,9}$/;
		
		//아이디를 입력할 때마다 실행할 함수 등록
		document.querySelector("#id").addEventListener("input",(event)=>{
			//일단 is-valid, is-invalid 클래스를 모두 지우고
			event.target.classList.remove("is-valid","is-invalid");
			//현재까지 입력한 아이디를 읽어온다
			let inputId = event.target.value;
			//만일 정규표현식을 통과하지 못했다면
			if(!reg_id.test(inputId)) {
				/*
					어떤 요소에 class 를 추가하는 방법
					.classList.add("클래스 명")
				*/
		        event.target.classList.add("is-invalid");
		        isIdValid = false;
			}else{
		        event.target.classList.add("is-valid");
		        isIdValid = true;
			}
			checkForm();
		});
		
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