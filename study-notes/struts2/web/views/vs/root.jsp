<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-27
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:debug/>

<html>
<head>
    <title>Title</title>
</head>

<s:property value="welcome"/><br>

<s:property value="[0].top"/><br>
<s:property value="[1].top"/><br>

<s:property value="username"/><br>
<s:property value="[0].top.username"/><br>

<s:property value="password"/><br>

</body>
</html>
