package com.sq.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test4")
public class Test4Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getSession().getAttribute("name"));
        // 前提: 客户端请求到服务器时, 有调用getSession()

        // getSession内部的原理:
        // 1. 检查客户端是否有发送一个叫做JSESSIONID的Cookie
        // 1> 如果没有?
        // 创建一个新的Session对象, 并且这个Session对象会有一个id
        // 这个Session对象会保留在服务器的内存中

        // 在响应的时候, 会添加一个Cookie(JESSIONID=Session对象的id)给客户端

        // 2> 如果有?
        // 返回id为JESSIONID的Session对象
    }
}
