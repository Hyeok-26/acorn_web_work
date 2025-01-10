<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크</title>
</head>
<body>
	<div class="container">
		<h1>아이디 중복 체크</h1>
		<label for="id">아이디</label>
		<input type="text" name="id" id="id" />
		<button id="IdBtn">아이디 중복 확인</button>
	    <p id="result"></p>
    </div>
    <script>
    	document.querySelector("#IdBtn").addEventListener("click",()=>{
    		const inputId = document.querySelector("#id").value;
    		//".jsp?파라미터 명="+담아서 보낼 값)
    		fetch("checkId.jsp?id="+inputId)
            .then(res => res.text())
            .then(data => {
          
                document.querySelector("#result").textContent = data;
            })
    	});
    </script>
	
</body>
</html>