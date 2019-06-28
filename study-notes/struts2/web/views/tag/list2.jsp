<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-28
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<s:debug/>
<%--取出context区域的集合数据并迭代--%>
<s:iterator value="#students">
    <s:property value="id"/>
    <s:property value="name"/>
    <s:property value="age"/>
    <br>
</s:iterator>
</body>
</html>
