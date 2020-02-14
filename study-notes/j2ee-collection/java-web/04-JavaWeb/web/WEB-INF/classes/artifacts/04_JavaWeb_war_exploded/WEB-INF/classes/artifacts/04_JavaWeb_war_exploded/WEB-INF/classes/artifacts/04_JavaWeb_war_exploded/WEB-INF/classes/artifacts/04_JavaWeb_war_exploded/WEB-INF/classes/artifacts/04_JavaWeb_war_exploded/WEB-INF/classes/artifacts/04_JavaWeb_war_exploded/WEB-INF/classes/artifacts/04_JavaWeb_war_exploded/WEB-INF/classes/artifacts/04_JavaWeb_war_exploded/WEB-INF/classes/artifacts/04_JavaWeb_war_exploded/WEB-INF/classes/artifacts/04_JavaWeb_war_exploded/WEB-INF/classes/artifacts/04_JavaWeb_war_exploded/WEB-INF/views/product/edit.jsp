<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/11
  Time: 5:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>货品编辑界面</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/product?cmd=save" method="post">
    <input type="hidden" name="id" value="${product.id}">
    <table border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td>货品分类</td>
            <td><input type="text" name="productName" value="${product.productName}" required></td>
        </tr>
        <tr>
            <td>货品品牌</td>
            <td><input type="text" name="brand" value="${product.brand}" required></td>
        </tr>
        <tr>
            <td>供&nbsp;应&nbsp;商</td>
            <td><input type="text" name="supplier" value="${product.supplier}" required></td>
        </tr>
        <tr>
            <td>零&nbsp;售&nbsp;价</td>
            <td><input type="number" name="salePrice" value="${product.salePrice}" required min="0"></td>
        </tr>
        <tr>
            <td>成&nbsp;本&nbsp;价</td>
            <td><input type="number" name="costPrice" value="${product.costPrice}" required min="0"></td>
        </tr>
        <tr>
            <td>折&nbsp;&nbsp;扣</td>
            <td><input type="text" name="cutoff" value="${product.cutoff}" required></td>
        </tr>
        <tr>
            <td>货品分类</td>
            <td>
                <select name="dir_id">
                    <c:forEach items="${dirs}" var="dir">
                        <option value="${dir.id}" ${product.dir_id == dir.id ? "selected" : ""}>${dir.dirName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="保存"></td>
        </tr>
    </table>
</form>

</body>
</html>
