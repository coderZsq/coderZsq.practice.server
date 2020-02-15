<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/14
  Time: 11:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${u}
注册名称: ${u.username}<br>
注册邮箱: ${u.email}<br>
头像原始名称: ${u.imageName}<br>
头像: <br>
<img src="${u.imageUrl}" alt="">
</body>
</html>
