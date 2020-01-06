<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>编辑员工</h3>
	<form action="/employee/saveOrUpdate" method="post">
	  	<input type="hidden" name="id" value="${employee.id}"/>
		  账号<input type="text" name="username" value="${employee.username}"/><br/>
		  密码<input type="text" name="password"  value="${employee.password}"/><br/>
	   	 入职<input type="text" name="hiredate"  value="${employee.hiredate}"/><br/>
	  <input type="submit" value="保存"/>
  </form>
</body>
</html>