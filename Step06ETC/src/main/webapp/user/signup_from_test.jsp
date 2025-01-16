<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>회원가입 폼 입니다</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div class="mb-2">
				<label class="form-label"for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"/>
				<small class="form-text">영문자 소문자로 시작하고 5~10 글자 이내로 입력하세요</small>
				<div class="valid-feedback">통과!</div>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다</div>
			</div>
			
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"/>
				<small class="form-text">특수 문자를 하나 이상 조합하세요.</small>
				<div class="invalid-feedback">비밀 번호를 확인 하세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="password"  id="pwd2"/>
				<div class="invalid-feedback">비밀 번호가 같지 않습니다</div>
			</div>
			<button class="btn btn-success" type="submit" disabled="disabled">가입</button>
			
		</form>
	</div>
	<script>
		//상태값 관리 변수
		let idValid =  false;
		let pwdValid = false;
		let emailValid = false;
		
		//정규 표현식
		const reg_id = /^[a-z]\S{4,9}$/;
		const reg_pwd = /[\W]/;
		const reg_email = 1;
		
		document.querySelector("#signupForm").addEventListener("submit",(event)=>{
			const idCheck = document.querySelector("#id").value;
			
			if (!reg_id.test(idCheck)) {
				event.preventDefault();
			}
		});
		
		const checkForm=()=>{
			//상태값에 따른 동작을 하도록 분기한다
			//폼 전체의 유효성 여부에 따라 분기한다(지금은 id 유효성 여부만)
			if(idValid&&pwdValid){
				//type 속성이 submit 인 요소를 찾아서 disabled 속성을 제거
				document.querySelector("[type=submit]").removeAttribute("disabled");
				
			}else{
				//type 속성이 submit 인 요소를 찾아서 "disabled","disabled" 속성을 추 가
				document.querySelector("[type=submit]").setAttribute("disabled","disabled");
				
			}
			
		};
		
		document.querySelector("#id").addEventListener("input",(event)=>{
			event.target.classList.remove("is-valid","is-invalid");
			let inputId = event.target.value;
			if(!reg_id.test(inputId)) {
		        event.target.classList.add("is-invalid");
		        idValid = false;
			}else{
				event.target.classList.add("is-valid");
		        idValid = true;
			}
			checkForm();
		});
		
		const checkPwd = ()=>{
			const pwd = document.querySelector("#pwd").value;
			const pwd2 = document.querySelector("#pwd2").value;
			
			document.querySelector("#pwd").classList.remove("is-valid","is-invalid");
			document.querySelector("#pwd2").classList.remove("is-valid","is-invalid");
			
			if(!reg_pwd.test(pwd)){
				document.querySelector("#pwd").classList.add("is-invalid");
			}else{
				document.querySelector("#pwd").classList.add("is-valid");
				pwdValid = true;
			}
			if(pwd2==""){
				document.querySelector("#pwd2").classList.remove("is-valid","is-invalid");
				pwdValid = false;
			}else{
				if(pwd==pwd2){
					document.querySelector("#pwd2").classList.add("is-valid");
					pwdValid = true;
				}else{
					document.querySelector("#pwd2").classList.add("is-invalid");
					pwdValid = false;
				}
			}checkForm();
		};
		
		document.querySelector("#pwd").addEventListener("input", checkPwd)
		document.querySelector("#pwd2").addEventListener("input", checkPwd)
		
		const checkEmail = ()=>{
			
		};
	
	</script>
</body>
</html>