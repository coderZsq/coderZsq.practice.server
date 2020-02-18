<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/10
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>货品显示列表</title>
    <script type="text/javascript">
        function go(pageNo) {
            // 吧需要跳转的页面设置到<input type="number" name=currentPage/>上
            document.getElementById("currentPage").value = pageNo;
            // 提交表单
            document.forms[0].submit();
        }
    </script>
</head>
<body>
<form action="/product" method="post">
    商品名称: <input type="text" name="name" value="${qo.name}">
    商品价格: <input type="text" name="minSalePrice" style="width: 100px" value="${qo.minSalePrice}">
    到<input type="text" name="maxSalePrice" style="width: 100px" value="${qo.maxSalePrice}">
    商品分类:
    <select name="dirId">
        <option value="-1">所有分类</option>
        <c:forEach items="${dirs}" var="d">
            <option value="${d.id}" ${d.id == qo.dirId ? "selected" : ""}>${d.dirName}</option>
        </c:forEach>
    </select>
    关键字: <input type="text" name="keyword" placeholder="商品名称或商品品牌" value="${qo.keyword}">
    <input type="submit" value="查询" style="background: orange">
    <table border="1" width="90%" cellpadding="0" cellspacing="0">
        <tr style="background-color: orange">
            <th>货品编号</th>
            <th>货品名</th>
            <th>货品品牌</th>
            <th>货品分类</th>
            <th>供&nbsp;应&nbsp;商</th>
            <th>零&nbsp;售&nbsp;价</th>
            <th>成&nbsp;本&nbsp;价</th>
            <th>折&nbsp;&nbsp;扣</th>
        </tr>
        <c:forEach items="${pageResult.listData}" var="p" varStatus="vs">
            <tr style='background-color: ${vs.count % 2 == 0 ? "gray" : ""}'>
                <td>${p.id}</td>
                <td>${p.productName}</td>
                <td>${p.brand}</td>
                <td>
                    <c:choose>
                        <c:when test="${p.dir_id == 1}">鼠标</c:when>
                        <c:when test="${p.dir_id == 2}">无线鼠标</c:when>
                        <c:when test="${p.dir_id == 3}">有线鼠标</c:when>
                        <c:when test="${p.dir_id == 4}">游戏鼠标</c:when>
                    </c:choose>
                </td>
                <td>${p.supplier}</td>
                <td>${p.salePrice}</td>
                <td>${p.costPrice}</td>
                <td>${p.cutoff}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="8" align="center">
                <a href="javascript:go(1)">首页</a>
                <a href="javascript:go(${pageResult.prevPage})">上页</a>
                <c:forEach begin="${pageResult.beginIndex}" end="${pageResult.endIndex}" var="pageNumber">
                    <c:if test="${pageNumber != pageResult.currentPage}">
                        <a href="javascript:go(${pageNumber})">${pageNumber}</a>
                    </c:if>
                    <c:if test="${pageNumber == pageResult.currentPage}">
                    <span style="color: red;font: bold">
                            ${pageNumber}
                    </span>
                    </c:if>
                </c:forEach>
                <a href="javascript:go(${pageResult.nextPage});">下页</a>
                <a href="javascript:go(${pageResult.totalPage})">末页</a>
                当前第${pageResult.currentPage}/${pageResult.totalPage}页,
                一共${pageResult.totalCount}条数据
                跳转到 <input type="number" min="1" max="${pageResult.totalPage}" id="currentPage"
                           value="${pageResult.currentPage}" style="width: 50px" name="currentPage"> 页
                <input type="button" value="GO" onclick="go();">
                每页
                <select name="pageSize" onchange="go();">
                    <c:forEach items="${pageResult.pageItems}" var="item">
                        <option ${item == pageResult.pageSize ? "selected" : ""}>${item}</option>
                    </c:forEach>
                </select>
                条数据
            </td>
        </tr>
    </table>
</form>
</body>
</html>
