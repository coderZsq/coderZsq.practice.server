package com.coderZsq.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
     * 当客户端发送的是GET请求, 就会调用HttpServlet的doGet方法
     *
     * @param request  用来接收客户端发送的数据
     * @param response 用来给客户端发送数据 (响应)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 当客户端发送的是POST请求, 就会调用HttpServlet的doPost方法
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        outHTML(request, response);
    }

    private void outHTML(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            out.write("<h1 style=\"color: blue; border: 1px solid black;\">登录成功</h1>");
            out.write("<ul>");
            out.write("<li>个人信息</li>");
            out.write("<li>修改密码</li>");
            out.write("<li>退出登录</li>");
            out.write("</ul>");
        } else {
            out.write("<h1 style=\"color: red; border: 1px solid black;\">登录失败</h1>");
            out.write("<ul>");
            out.write("<li>重新登录</li>");
            out.write("</ul>");
        }
    }

    private void outPlain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 0. 设置请求数据的编码
        request.setCharacterEncoding("UTF-8");

        // 1. 取出客户端发送的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 设计响应的内容类型
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 2. 判断用户名, 密码是否正确 (假设规定用户名密码都是123, 才代表正确)
        if ("123".equals(username) && "123".equals(password)) {
            // 给客户端发送数据 (响应)
            out.write("登录成功");
        } else {
            // 给客户端发送数据 (响应)
            out.write("登录失败");
        }
    }

    /**
     * HTTP请求
     * > GET请求
     *      1> 请求参数拼接在请求路径后面
     *      2> 请求头
     * > POST请求
     *      1> 请求参数放在请求体中
     *      2> 请求头
     */
}
