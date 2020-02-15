<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/10
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8"--%>
<%--         language="java"--%>
<%--         errorPage="errorPage.jsp"--%>
<%--         isErrorPage="false"--%>
<%--%>--%>
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
%>
<%--
    page指令: 表示的JSP页面相关的配置信息
        常用属性:
        language: 表示在JSP中编写的脚本语言
        contentType: 表示JSP输出的MIME类型和编码
                     等价于response.setContentType("text/html";charset="UTF-8");
        pageEncoding: 表示JSP输出的编码
                      response.setCharacterEncoding("UTF-8");
        若contentType和pageEncoding共存, 以pageEncoding为主
        import: 用于导入JSP脚本中使用到的类, 等价于java代码中的: import 类的全限定名

        isErrorPage: 是否把当前页面设置专门显示错误的页面
        errorPage: 当前页面出错之后, 需要跳转到哪一个页面上去
--%>
<%
    Set set = null;
    List list = null;
%>

<%
    // int c = 1 / 0;
    String age = request.getParameter("age");
%>
<%=age%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
