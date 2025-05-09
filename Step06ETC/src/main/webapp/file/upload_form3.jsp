<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h3>이미지 업로드 폼</h3>
		<form action="${pageContext.request.contextPath }/file/upload3" 
			method="post" enctype="multipart/form-data" id="myForm">
			<input type="text" name="title" placeholder="설명 입력"/><br />
			이미지 <input type="file" name="myImage" accept="image/*"/><br />
			<button type="submit">업로드</button>
		</form>
		<img id="image" width="300" alt="업로된 이미지"/>
	</div>
	<script>
		//발생한 이벤트에 대한 정보를 담고 있는 ...
		document.querySelector("#myForm").addEventListener("submit",(event)=>{
			//기본 동작(폼 제출)를 막기
			event.preventDefault();
			//event.target = > 해당 이벤트가 발생한 바로 그 요소의 참조값(form 의 참조값)
			//document.querySelector("#myForm")==event.target
			const data = new FormData(event.target);//--------------------------------
			// fetch 함수를 이용해서 FormData 전송하기
			fetch("${pageContext.request.contextPath }/file/upload3",{
				method:"post",
				body:data
			})
			.then(res=>res.json())
			.then(data=>{
				console.log(data);
				//data.saveFileName 은 upload 폴더에 저장된 파일명이다
				const requestPath = "${pageContext.request.contextPath }/upload/"+data.saveFileName;
			 	//img 요소에 src 속성 추가하기
				document.querySelector("#image").setAttribute("src",requestPath);
				
			});
		});
	</script>
</body>
</html>