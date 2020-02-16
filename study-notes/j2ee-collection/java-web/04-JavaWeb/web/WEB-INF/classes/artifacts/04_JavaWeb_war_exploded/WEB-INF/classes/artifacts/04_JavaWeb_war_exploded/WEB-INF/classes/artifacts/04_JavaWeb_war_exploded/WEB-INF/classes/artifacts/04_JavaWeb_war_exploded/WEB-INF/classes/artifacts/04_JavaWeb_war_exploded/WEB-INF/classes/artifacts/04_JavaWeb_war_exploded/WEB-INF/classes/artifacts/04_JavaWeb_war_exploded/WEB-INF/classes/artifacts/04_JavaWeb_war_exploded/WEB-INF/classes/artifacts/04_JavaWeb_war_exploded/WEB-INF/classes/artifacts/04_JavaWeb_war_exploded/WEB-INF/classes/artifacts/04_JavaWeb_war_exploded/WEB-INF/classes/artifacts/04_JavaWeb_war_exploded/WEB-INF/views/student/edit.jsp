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

<form action="${pageContext.request.contextPath}/student?cmd=save" method="post">
    <input type="hidden" name="id" value="${student.id}">
    姓名: <input type="text" name="name" required value="${student. name}"><br>
    年龄: <input type="number" name="age" required value="${student.age}"><br><br>
    <input type="submit" value='${student == null ? "保存学生信息" : "更新学生信息"}'>
</form>

</body>
</html>
