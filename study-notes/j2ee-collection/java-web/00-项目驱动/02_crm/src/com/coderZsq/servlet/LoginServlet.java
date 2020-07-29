package com.coderZsq.servlet;

import com.coderZsq.bean.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 设置请求数据的编码
        request.setCharacterEncoding("UTF-8");

        // 2. 取出客户端发送的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 3. 先设置响应的内容类型 (MIMEType + 数据编码)
        response.setContentType("text/html;charset=UTF-8");

        // 4. 拿到输出流
        PrintWriter out = response.getWriter();

        // 5. 判断
        if ("123".equals(username) && "123".equals(password)) {
            success(out);
        } else {
            failed(out);
        }
    }

    private List<Customer> getCustomer() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customers.add(new Customer("张三" + i, "432423" + i, ((i & 1) == 1) ? "男" : "女"));
        }
        return customers;
    }

    private void success(PrintWriter out) {
        out.write("<html>");
        out.write("<head>");
        out.write("<link rel=\"stylesheet\" href=\"http://localhost:8080/crm/login.css\">");
        out.write("</head>");
        out.write("<body>");
        out.write("<h1 style=\"color: blue; border: 1px solid black;\">登录成功</h1>");
        out.write("<table>");
        out.write("<thead>");
        out.write("<tr>");
        out.write("<th>姓名</th>");
        out.write("<th>电话</th>");
        out.write("<th>性别</th>");
        out.write("</tr>");
        out.write("</thead>");
        out.write("<tbody>");

        List<Customer> customers = getCustomer();
        for (Customer customer : customers) {
            out.write("<tr>");
            out.write("<td>" + customer.getName() +"</td>");
            out.write("<td>" + customer.getPhone() +"</td>");
            out.write("<td>" + customer.getSex() +"</td>");
            out.write("</tr>");
        }

        out.write("</tbody>");
        out.write("</table>");
        out.write("</body>");
        out.write("</html>");
    }

    private void failed(PrintWriter out) {
        out.write("<h1 style=\"color: red; border: 1px solid black;\">登录失败</h1>");
        out.write("<ul>");
        out.write("<a href=\"http://localhost:8080/crm/login.html\">重新登录</a>");
        out.write("</ul>");
    }
}
