<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>员工列表,当前登录用户:${user_in_session.username}</h3>
	<a href="/login.jsp">注销</a>
	<hr/>
	<a href="/employee/input">新增</a>
	
	<table border="1" cellspacing="0" cellpadding="0" width="500">
		<tr>
			<th>ID</th>
			<th>用户名</th>
			<th>密码</th>
			<th>入职日期</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${employees}" var="e">
		<tr>
			<td>${e.id}</td>
			<td>${e.username}</td>
			<td>${e.password}</td>
			<td>${e.hiredate}</td>
			<td>
				<a href="/employee/delete?id=${e.id}">删除</a> 
				<a href="/employee/input?id=${e.id}">编辑</a>
			</td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>