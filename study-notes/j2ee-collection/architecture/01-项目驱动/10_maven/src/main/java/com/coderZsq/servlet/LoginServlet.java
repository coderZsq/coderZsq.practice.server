package com.coderZsq.servlet;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解决: POST请求中文参数乱码
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);

        response.setContentType("text/plain;charset=UTF-8");
        if ("123".equals(username) && "123".equals(password)) {
            response.getWriter().write("登录成功");
        } else {
            response.getWriter().write("登录失败");
        }
        // System.out.println(DruidDataSourceFactory.class);
    }
}
