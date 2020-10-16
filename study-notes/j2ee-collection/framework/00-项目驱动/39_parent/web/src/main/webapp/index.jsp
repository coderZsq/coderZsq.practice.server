<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/9/17
  Time: 12:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("ctx", request.getContextPath()); %>
<html>
<head>
    <title>Skill</title>
</head>
<body>

<div>
    <a href="${ctx}/skills/get?id=1" target="_blank">单个</a>
</div>

<div>
    <a href="${ctx}/skills/list" target="_blank">列表</a>
</div>

<div>
    保存
    <form action="${ctx}/skills/save" method="post">
        <input name="id" placeholder="id">
        <input name="name" placeholder="name">
        <input name="level" placeholder="level">
        <button type="submit">保存</button>
    </form>
</div>

<div>
    删除
    <form action="${ctx}/skills/remove" method="post">
        <input name="id" placeholder="id">
        <button type="submit">删除</button>
    </form>
</div>

</body>
</html>
