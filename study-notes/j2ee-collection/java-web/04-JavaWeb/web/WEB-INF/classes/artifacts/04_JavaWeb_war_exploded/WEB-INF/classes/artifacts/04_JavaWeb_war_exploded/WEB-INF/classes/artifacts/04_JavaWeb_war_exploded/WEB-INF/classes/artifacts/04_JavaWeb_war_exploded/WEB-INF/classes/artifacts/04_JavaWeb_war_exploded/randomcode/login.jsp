<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/15
  Time: 2:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function change() {
            // 重新设置<img>元素的src属性
            document.getElementById("randomCodeImg").src = "/randomCode?" + new Date().getTime();
        }
    </script>
</head>
<body>
<h3>用户登录</h3>
<span style="color: red">${errorMsg}</span>
<form action="/randeomLogin" method="post">
    账&emsp;户: <input type="text" name="username" required><br>
    密&emsp;码: <input type="password" name="password" required><br>
    验证码: <input type="text" name="randomCode" size="5" maxlength="5" required><img src="/randomCode" id="randomCodeImg" title="看不清,换一张" style="cursor: pointer" onclick="change();"><br>
    <input type="submit" value="朕要登录">
</form>
</body>
</html>
