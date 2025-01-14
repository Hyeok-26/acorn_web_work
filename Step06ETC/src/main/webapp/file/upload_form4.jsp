<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#image{
		display: none;
	}
	#profileImage{
		width:200px;
		height: 200px;
		border: 1px solid #cecece;
		border-radius : 50%;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>이미지 단독 업로드 테스트</h3>
		<a href="javascript:" id="profileLink" >
			<!-- 크기를 마음대로 할 수 있는 벡터 이미지 -->
			<svg xmlns="http://www.w3.org/2000/svg" width="200" height="200" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
				<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
				<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
			</svg>
		</a>
		<br />
		<input type="file" id="image" accept="image/*" />
	</div>
	<script>
		//링크를 클릭했을때
		document.querySelector("#profileLink").addEventListener("click",()=>{
			//input type = "file" 요소를 강제 클릭해서 파일 선택 창을 띄운다
			document.querySelector("#image").click();
		});
		//새로운 이미지를 선택했을떄 input 요소에는 change 이벤트가 발생한다
		document.querySelector("#image").addEventListener("change",(event)=>{
			//여기서 event.target 은 input type="file" 요소이다.
			//선택된 파일 데이터
			const fileData = event.target.files[0];
			//FormData 객체에 myImage 라는 키값으로 파일 데이터 답기--------------------------
			const data  = new FormData();
			data.append("myImage",fileData);
			//fetch 함수를 이용해서 업로드하고 응답받은 데이터를 이용해서 
			fetch("${pageContext.request.contextPath }/file/upload4",{
				method:"post",
				body:data
			})
			.then(res=>res.json())
			.then(data=>{
				//data.saveFileName 은 업로드된 이미지의 저장된 파일의 이름이다
				console.log(data)
				//img 요소를 만등 문자열 구하기
								//jsp 가 환장하는 함수							이 함수가 실행될 때 웹브라우저가 해석을 해야 되는 부분(서버가 해석을 해버리면 안된다)
				const img=`		
					<img id="profileImage" src="${pageContext.request.contextPath }/upload/\${data.saveFileName}">
				`;
				document.querySelector("#profileLink").innerHTML=img;
				
			})
			.catch(error=>{
				console.log("에러 정보:"+error);
			});
		});
	</script>
</body>
</html>