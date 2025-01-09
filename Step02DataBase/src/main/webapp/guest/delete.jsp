<%@page import="test.guest.dto.GuestDto"%>
<%@page import="test.guest.dao.GuestDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//폼에 전송되는 삭제할 글의 비밀번호와 비밀번호 추출
	int num = Integer.parseInt(request.getParameter("num"));
	String pwd = request.getParameter("pwd");
	
	//비밀번호 일치하는지 확인해서 일치하면 삭제
	GuestDto dto = GuestDao.getInstance().getData(num);
	//비밀번호 일치하는지 확인
	if(pwd.equals(dto.getPwd())){
		//db에서 삭제하고
		GuestDao.getInstance().delete(num);
		//"/guest/list.jsp" 페이지로 리다일렉트 이동하라는 응답하기
		String cPath=request.getContextPath();
		response.sendRedirect(cPath+"/guest/list.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		alert("비밀번호가 일치하지 않습니다");
		location.href="${pageContext.request.contextPath }/guest/list.jsp";
	</script>
</body>
</html>