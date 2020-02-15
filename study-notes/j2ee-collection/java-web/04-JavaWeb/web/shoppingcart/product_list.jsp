<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/15
  Time: 7:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="" method="get">
    商品名称:
    <select name="name">
        <option>iphone</option>
        <option>ipad</option>
        <option>iwatch</option>
    </select><br>
    购买数量: <input type="number" name="num" min="1 ">
    <br><input type="submit" value="添加进购物车">
</form>
</body>
</html>
