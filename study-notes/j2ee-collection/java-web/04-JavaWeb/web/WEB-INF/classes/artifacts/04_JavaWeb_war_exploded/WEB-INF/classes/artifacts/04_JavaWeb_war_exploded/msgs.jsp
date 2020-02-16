<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/16
  Time: 5:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>留言列表</title>
</head>
<body>
<table border="1" cellspacing="0" cellpadding="0" width="400">
    <tr>
        <th>主题</th>
        <th>内容</th>
    </tr>
    <c:forEach items="${list}" var="msg">
        <tr>
            <td>${msg.title}</td>
            <td>${msg.content}</td>
        </tr>
    </c:forEach>
</table>
<hr color="red" size="10">
<form action="/msg?cmd=save" method="post">
    主题: <input type="text" name="title"><br>
    内容:
    <textarea name="content" cols="20" rows="5"></textarea>
    <br><input type="submit" value="submit">
</form>
</body>
</html>
