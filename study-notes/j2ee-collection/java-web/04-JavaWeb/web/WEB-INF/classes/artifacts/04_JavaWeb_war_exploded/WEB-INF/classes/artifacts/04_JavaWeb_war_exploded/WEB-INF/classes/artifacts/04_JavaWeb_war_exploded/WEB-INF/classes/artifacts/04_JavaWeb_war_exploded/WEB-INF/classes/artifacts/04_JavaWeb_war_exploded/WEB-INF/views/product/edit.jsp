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
                    <option value="1" ${product.dir_id == 1 ? "select" : ""}>鼠标</option>
                    <option value="2" ${product.dir_id == 2 ? "select" : ""}>无线鼠标</option>
                    <option value="3" ${product.dir_id == 3 ? "select" : ""}>有线鼠标</option>
                    <option value="4" ${product.dir_id == 4 ? "select" : ""}>美女鼠标</option>
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
