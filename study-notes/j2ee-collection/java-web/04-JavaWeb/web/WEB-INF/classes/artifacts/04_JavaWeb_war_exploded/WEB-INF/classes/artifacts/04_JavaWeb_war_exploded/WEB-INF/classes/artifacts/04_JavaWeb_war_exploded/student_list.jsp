<%@ page import="com.coderZsq._17_smis.domain.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/10
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
学生列表
<%
    List<Student> students = (List<Student>) request.getAttribute("student");
%>
<table border="1" width="80%" cellpadding="0" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>年龄</th>
    </tr>
    <%
        for (Student s : students) {
    %>
    <tr>
        <td><%=s.getId()%></td>
        <td><%=s.getName()%></td>
        <td><%=s.getAge()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
