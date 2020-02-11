<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/11
  Time: 5:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/student/save" method="post">
    姓名: <input type="text" name="name" required><br>
    年龄: <input type="number" name="age" required><br><br>
    <input type="submit" value="保存学生信息">
</form>

</body>
</html>
