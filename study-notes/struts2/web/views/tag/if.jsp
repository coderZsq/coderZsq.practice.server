<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-28
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    request.setAttribute("num", 10);
%>

<s:property value="#request.num"/>

<s:if test="#request.num > 5">
    大于5
</s:if>
<s:elseif test="#request.num < 5">
    小于5
</s:elseif>
<s:else>
    等于5
</s:else>

</body>
</html>
