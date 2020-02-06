<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<span style="color: red;">${errorMsg}</span>
	<%
		session.invalidate();
	%>
	<h3>登录界面</h3>
	<form action="/login" method="post">
		  账号<input type="text" name="username"/><br/>
		  密码<input type="text" name="password"/><br/>
	  <input type="submit" value="登录"/>
  </form>
</body>
</html>