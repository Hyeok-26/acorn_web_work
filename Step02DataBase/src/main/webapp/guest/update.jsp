<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//폼 전송되는 수정할 회원의 정보를 읽어온다
	int num=Integer.parseInt(request.getParameter("num"));
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	String pwd = request.getParameter("pwd");
	//GuestDao 객체의 참조값
	GuestDao dao = GuestDao.getInstance();
	//DB에 저장된 비밃너호
	String savedPwd = dao.getData(num).getPwd();
	//작업의 성공여무를 저장할 변수를 만들고 false 를 초기값으로 부여
	boolean isSuccess = false;
	if(pwd.equals(savedPwd)){
		//만일 비밀번호가 일치한다면
		//수정할 글 정보를 객체에 담고
		GuestDto dto = new GuestDto();
		dto = new GuestDto();
		dto.setNum(num);
		dto.setWriter(writer);
		dto.setContent(content);
		//DB 에 수정 반영한다
		isSuccess = dao.update(dto);
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
		<% if (isSuccess) { %>
		    alert("수정이 완료되었습니다.");
		    location.href = "${pageContext.request.contextPath}/guest/list.jsp";
		<% } else { %>
		    alert("비밀번호가 일치하지 않거나 수정에 실패하였습니다.");
		    location.href = "${pageContext.request.contextPath}/guest/updateform.jsp?num=<%=num%>";
		<% } %>
	</script>
</body>
</html>