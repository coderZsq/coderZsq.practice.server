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

<s:property value="#username"/><br>
<s:property value="#password"/><br>
<s:property value="#github"/><br>
<s:property value="#session.USER_IN_SESSION"/><br>

${requestScope.username}<br>
${password}<br>
${sessionScope.USER_IN_SESSION}<br>

</body>
</html>
