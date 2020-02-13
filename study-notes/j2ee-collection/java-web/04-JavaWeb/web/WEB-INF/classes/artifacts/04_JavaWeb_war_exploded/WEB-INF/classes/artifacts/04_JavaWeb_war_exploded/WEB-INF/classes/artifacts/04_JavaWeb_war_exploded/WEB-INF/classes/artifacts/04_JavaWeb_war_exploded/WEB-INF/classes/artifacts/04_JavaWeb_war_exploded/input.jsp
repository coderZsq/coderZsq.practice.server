<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/13
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>文件上传</h3>
<form action="/upload" method="post" enctype="multipart/form-data">
    账号: <input type="text" name="username"><br>
    邮箱: <input type="text" name="email"><br>
    头像: <input type="file" name="headImg" accept="image/*"><br>
    <input type="submit" value="朕要注册">
</form>
</body>
</html>
