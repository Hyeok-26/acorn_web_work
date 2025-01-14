<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cooks = request.getCookies();
	
	//반복문 돌면서
	for(Cookie tmp:cooks){
		//쿠키의 키값을 읽어온다
		String key = tmp.getName();
		//해당 키값으로 저장된 value 값을 읽어온다
		String value = tmp.getValue();
		
		System.out.println("key:"+key+"value: "+value);
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>저장된 쿠키 목록</h1>
	<table>
		<thead>
			<tr>
				<th>key</th>
				<th>value</th>
			</tr>
		</thead>
		<tbody>
			<%for(Cookie tmp:cooks){ %>
				<tr>
					<td><%=tmp.getName() %></td>
					<td><%=tmp.getValue() %></td>
				</tr>
			<%} %>
		</tbody>
		<a href="${pageContext.request.contextPath }/">인덱스로</a>
	</table>
</body>
</html>