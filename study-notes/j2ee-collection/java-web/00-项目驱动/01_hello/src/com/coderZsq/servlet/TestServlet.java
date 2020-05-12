package com.coderZsq.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/test1", "/test2"})
public class TestServlet extends HttpServlet {
    public TestServlet() {
        System.out.println(this + "_构造方法");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 打印一个对象的时候, 默认会打印: 类名@哈希值
        System.out.println(this + "_service");
    }
}