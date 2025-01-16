<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user/signup_form4.jsp</title>
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
				<div class="valid-feedback">사용 가능한 아이디 입니다</div>
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
			</div>
			<div class="mb-2">
				<label class="form-label" for="email">이메일</label>
				<input class="form-control" type="email" name="email" id="email"/>
				<div class="invalid-feedback">이메일 형식에 맞게 입력하세요.</div>
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
		let isEmailValid = false;
		
		const checkForm=()=>{
			//상태값에 따른 동작을 하도록 분기한다
			//폼 전체의 유효성 여부에 따라 분기한다(지금은 id 유효성 여부만)
			if(isIdValid&&isPwdValid&&isEmailValid){
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
		        //아이디 상태값 변경이 버튼의 disabled 속성에 변화를 주도록 checkForm() 호출
		        checkForm();
		        return;
			}//정규 표현식을 통과했다면
			//서버에 입력한 아이디를 전송해서 사용 가능 여부를 응답 받는다
			//fetch() 함수 이용해서 get 방식으로 입력한 아이디를 보내고 사용가능 여부를 json으로 응답
			fetch("${pageContext.request.contextPath }/user/checkid.jsp?id="+inputId)
			.then(res=>res.json())
			.then(data=>{
				//클래스 제거 후
				event.target.classList.remove("is-valid","is-invalid");
				//만일 사용할 수 있는 아이디라면
				if(data.canUse){
					event.target.classList.add("is-valid");
					isIdValid = true;
				}else{
					event.target.classList.add("is-invalid");
					isIdValid = false;
				}
				checkForm();
			})
			.catch(error=>{
				console.log("에러 정보:"+error);
			});
		});
		
		//비밀번호를 검증할 정규 표현식 객체
		const reg_pwd = /[\W]/;
		//함수를 미리 만들어서
		const checkPwd = (event)=>{
			//양쪽에 입력한 비밀번호를 읽어와서
			const inputPwd = document.querySelector("#pwd").value;
			const inputPwd2 = document.querySelector("#pwd2").value;
			//일단 is-valid 와 is-invalid 클래스를 제거를 먼저 하고()
			document.querySelector("#pwd").classList.remove("is-valid","is-invalid");
			document.querySelector("#pwd2").classList.remove("is-valid","is-invalid");
			//첫번째 비밀번호가 ㅈㅇ규식을 통과하지 못했거나
			//두번째 비밀번호가 정규표현식을 통과하지 못한다면 isPwdValid 를 false로 변경하고 checkForm() 호출
			if(!reg_pwd.test(inputPwd)||!reg_pwd.test(inputPwd2)){
				document.querySelector("#pwd").classList.add("is-invalid");
				isPwdValid = false;
				checkForm();
				return;
			}
			
			//양쪽에 입력한 비밀번호가 같은 지 확인해서 같으면 isPwdValid 를 true
			//다르면 false 로변경하고 checkForm() 호출
			if(inputPwd==inputPwd2){
				document.querySelector("#pwd").classList.add("is-valid");
				//비밀번호가 유효하다는 의미
				isPwdValid = true;
			}else{
				document.querySelector("#pwd").classList.add("is-invalid");
				//유효하지 않다는 의미에서 false
				isPwdValid = false;
			}
			checkForm();
		};
		//전달
		document.querySelector("#pwd").addEventListener("input",checkPwd);
		document.querySelector("#pwd2").addEventListener("input",checkPwd);
		
		const reg_email=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i
		
		document.querySelector("#email").addEventListener("input",(event)=>{
			const email = event.target.value;
			document.querySelector("#email").classList.remove("is-valid","is-invalid");
			
			if(reg_email.test(email)){
				isEmailValid=true;
				event.target.classList.add("is-valid");
			}else{
				isEmailValid = false;
				event.target.classList.add("is-invalid");
			}checkForm();
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