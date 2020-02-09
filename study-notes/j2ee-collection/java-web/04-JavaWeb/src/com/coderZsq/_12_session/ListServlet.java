package com.coderZsq._12_session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

// 输出收件箱界面
@WebServlet("/session/list")
public class ListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        // String username = "";
        // ===================================
        // 获取Session对象
        HttpSession session = req.getSession();
        // 获取Session中存储的数据
        // username = (String) session.getAttribute("currentName");
        User user = (User) session.getAttribute("USER_IN_SESSION");
        // ===================================
        out.println("欢迎: " + user.getUsername() + "<br>");
        for (int i = 1; i < 6; i++) {
            out.println("<a href='/session/get'>一份邮件</a><br>");
        }
    }
}
