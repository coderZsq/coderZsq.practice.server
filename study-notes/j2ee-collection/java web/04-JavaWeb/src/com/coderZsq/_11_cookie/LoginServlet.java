package com.coderZsq._11_cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 处理登录请求并输出欢迎界面
@WebServlet("/cookie/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        // ===================================
        // 创建Cookie, 存储数据
        Cookie c = new Cookie("currentName", username);
        // 把Cookie响应给浏览器
        resp.addCookie(c);
        // ===================================
        out.println("欢迎: " + username + "<br>");
        out.println("<a href='/cookie/list'>收件箱</a>");
    }
}
