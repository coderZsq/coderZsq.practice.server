<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3>编辑员工</h3>
  	<form:form action="/employee/saveOrUpdate" modelAttribute="employee">
  		<form:hidden path="id"/>
  		账号<form:input path="username"/><form:errors path="username" /><br/>
  		密码<form:password path="password"  showPassword="true"/><form:errors path="password" /><br/>
  		年龄<form:input path="age"/><form:errors path="age" /><br/>
  		入职<form:input path="hiredate"/><br/>
  		<input type="submit" value="保存"/>
  	</form:form>
</body>
</html>