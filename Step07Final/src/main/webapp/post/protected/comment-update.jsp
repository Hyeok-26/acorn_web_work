<%@page import="test.post.dao.CommentDao"%>
<%@page import="test.post.dto.CommentDto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//fetch() 로 전솔되는 수정할 글 번호의 내용을 읽어와서
	long num = Long.parseLong(request.getParameter("num"));
	String content = request.getParameter("content");
	//CommentDto 에 담은 다음
	CommentDto dto = new CommentDto();
	dto.setNum(num);
	dto.setContent(content);
	//dao 객체를 이용해서 수정 반영 후 성공 여부를 리턴 받는다
	boolean isSuccess = CommentDao.getInstance().update(dto);
	//json응답
%>
<%--{"isSuccess":<%=isSuccess %>, "content":"<%=content %>"} --%>
{"isSuccess":<%=isSuccess %>}