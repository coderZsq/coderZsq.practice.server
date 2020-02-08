package com.coderZsq._07_response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 演示HttpServletResponse接口方法
@WebServlet("/resp")
public class HttpServletResponseDemo extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置输出数据的MIME类型
        // resp.setContentType("text/html");
        // 设置输出数据的编码方式
        // resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        // 在浏览器中输出
        // OutputStream out = resp.getOutputStream();
        // out.write("Hello World!".getBytes());
        PrintWriter out = resp.getWriter();
        out.println("Hello World!");
        out.println("你好世界!");
    }
}
