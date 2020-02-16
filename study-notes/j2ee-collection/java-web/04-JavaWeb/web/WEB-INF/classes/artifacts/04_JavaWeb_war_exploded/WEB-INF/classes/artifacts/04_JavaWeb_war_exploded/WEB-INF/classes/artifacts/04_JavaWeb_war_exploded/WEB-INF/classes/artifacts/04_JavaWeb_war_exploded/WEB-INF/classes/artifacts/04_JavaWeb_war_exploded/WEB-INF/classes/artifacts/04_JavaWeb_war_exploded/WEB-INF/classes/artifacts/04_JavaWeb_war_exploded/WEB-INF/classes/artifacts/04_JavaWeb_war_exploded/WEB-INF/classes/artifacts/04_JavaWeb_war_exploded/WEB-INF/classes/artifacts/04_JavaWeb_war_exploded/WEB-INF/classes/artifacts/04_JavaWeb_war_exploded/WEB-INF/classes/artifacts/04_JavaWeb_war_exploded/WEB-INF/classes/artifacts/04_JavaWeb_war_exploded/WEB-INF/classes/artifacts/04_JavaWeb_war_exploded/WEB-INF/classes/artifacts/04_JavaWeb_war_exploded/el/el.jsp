<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/11
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--表示获取当前请求中参数名为username的参数值--%>
<%--username=${param.username}--%>

<h3>Person对象的信息</h3>
<br>username: ${p.username} --> ${p["username"]} --> ${p.getUsername()}
<br>age: ${p.age}
<br>hobbys: ${p.hobbys[0]}
<br>list: ${p.list[1]}
<br>map: ${p.map}
<br>map: ${p.map.company}
<br>map: ${p.map["coderZsq.github.io"]}

</body>
</html>
