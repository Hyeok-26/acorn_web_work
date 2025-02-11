<%@page import="test.post.dao.CommentDao"%>
<%@page import="test.post.dto.CommentDto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	long num = Long.parseLong(request.getParameter("num"));
	
	//CommentDto 에 담은 다음
	CommentDto dto = new CommentDto();
	dto.setNum(num);
	//dao 객체를 이용해서 수정 반영 후 성공 여부를 리턴 받는다
	boolean isSuccess = CommentDao.getInstance().delete(num);
	//json응답
%>
{"isSuccess":<%=isSuccess%>}