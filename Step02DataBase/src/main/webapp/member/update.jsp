<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	
	//1.폼 전송되는 수장할 회원의 번호, 이름 , 주소를 추출한다
	int num = Integer.parseInt(request.getParameter("num"));
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
	//db에 수정을 반영ㅇ한다
	MemberDto dto = new MemberDto(num, name, addr);
	//응답하기
	MemberDao dao = new MemberDao();
	boolean isSuccess = dao.update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/update.jsp</title>
</head>
<body>
	<script>
		<%if(isSuccess){ %>
			//알림창을 띄우고
			alert("수정했어요~~~");
			//list.jsp 페이지로 돌아간다
			location.href = "list.jsp";//목록 페이지로 이동,특정 페이지로 리다이렉트한다
		<%}else{ %>
			//알림창을 띄우고
			alert("수정 실패..ㅠㅠ");
			//updateform.jsp 페이지로 이동하면서 num이라는 파라미터 명으로 수정할 회원의 번호를 가지고 간다
			window.location.href = "updateform.jsp?num=<%=num%>";//window 생략 가능
		<%} %>
	</script>
</body>
</html>