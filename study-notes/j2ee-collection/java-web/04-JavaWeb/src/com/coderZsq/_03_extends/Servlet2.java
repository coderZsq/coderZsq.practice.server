package com.coderZsq._03_extends;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet2 extends MyGenericServlet {

    @Override
    public void init() {
        System.out.println("子类的初始化操作");
    }

    // 只能处理一般请求
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String encoding = super.getInitParameter("encoding");
        System.out.println(encoding);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        service(request, response); // 调用处理http请求的方法
    }

    // 专门处理HTTP的请求
    public void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        String method = servletRequest.getMethod(); // 获取请求方式, GET/POST
        if ("GET".equals(method)) {
            doGet(servletRequest, servletResponse);
        } else if ("POST".equals(method)) {
            doPost(servletRequest, servletResponse);
        }
    }

    // 专门用于处理POST请求
    private void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        System.out.println("Servlet1.doPost");
    }

    // 专门用于处理GET请求
    private void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        System.out.println("Servlet1.doGet");
    }
}
