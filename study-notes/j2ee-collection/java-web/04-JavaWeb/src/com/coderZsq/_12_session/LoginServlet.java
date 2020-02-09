package com.coderZsq._12_session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

// 处理登录请求并输出欢迎界面
@WebServlet("/session/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        // ===================================
        // 接收请求参数, 封装成对象
        User user = new User();
        user.setUsername(username);
        // user.setXxx......

        // 1. 创建和获取Seesion对象
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        session.setMaxInactiveInterval(15); // 超时时间为15秒
        // 2. 往session存储数据
        session.setAttribute("USER_IN_SESSION", user);
        // ===================================
        out.println("欢迎: " + username + "<br>");
        // out.println("<a href='/session/list;jsessionid=" + session.getId() + "'>收件箱</a>");
        String url = resp.encodeURL("/session/list");
        out.println("<a href='" + url + "'>收件箱</a>");
    }
}
