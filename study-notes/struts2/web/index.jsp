<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-25
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
      <h3>登录操作</h3>
      <s:form namespace="/" action="login">
          账号: <s:textfield name="username"/><br>
          密码: <s:password name="password"/><br>
          <s:submit value="登录"/>
      </s:form>
  </body>
</html>
