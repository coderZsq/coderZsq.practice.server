<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-28
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<%--从1迭代到10--%>
<s:iterator begin="1" end="10" var="num">
    <%--${num}--%>
    <s:property value="num"/>
</s:iterator>
<hr>

<%--迭代集合中的数据 --%>
<%
    request.setAttribute("listData", java.util.Arrays.asList("A", "B", "C", "D", "E"));
%>

<s:iterator value="#request.listData" var="item">
    <s:property value="item"/><br>
</s:iterator>

</body>
</html>
