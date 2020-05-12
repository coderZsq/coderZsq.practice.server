<%@ page import="com.coderZsq.bean.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    private List<Customer> getCustomer() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customers.add(new Customer("张三" + i, "432423" + i, ((i & 1) == 1) ? "男" : "女"));
        }
        return customers;
    }
%>
<%
    // 1. 设置请求数据的编码
    request.setCharacterEncoding("UTF-8");

    // 2. 取出客户端发送的数据
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // 3. 先设置响应的内容类型 (MIMEType + 数据编码)
    response.setContentType("text/html;charset=UTF-8");

    // 4. 拿到输出流

    // 5. 判断
    if ("123".equals(username) && "123".equals(password)) {
        List<Customer> customers = getCustomer();

    } else {

    }
%>

</body>
</html>
