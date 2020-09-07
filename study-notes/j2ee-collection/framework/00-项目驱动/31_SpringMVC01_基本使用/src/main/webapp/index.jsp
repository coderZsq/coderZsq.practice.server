<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/9/7
  Time: 11:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
This is index.jsp
<form action="skill/add" method="get">
    <div>
        名称<input name="name">
    </div>
    <div>
        描述<input name="intro">
    </div>
    <div>
        级别<input name="level">
    </div>
    <div>
        <button type="submit">添加</button>
    </div>
</form>
</body>
</html>
