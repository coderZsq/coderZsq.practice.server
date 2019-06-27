<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-27
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<s:form namespace="/validation" action="login" method="post">
    <s:textfield name="username" label="账号"/>
    <s:password name="password" label="密码"/>
    <s:submit value="登录"/>
</s:form>

</body>
</html>
