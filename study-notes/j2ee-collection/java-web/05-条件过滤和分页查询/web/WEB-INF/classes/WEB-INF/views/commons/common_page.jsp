<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/18
  Time: 10:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
    function go(pageNo) {
        // 吧需要跳转的页面设置到<input type="number" name=currentPage/>上
        document.getElementById("currentPage").value = pageNo;
        // 提交表单
        document.forms[0].submit();
    }
</script>

<%--通用的分页代码--%>
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
