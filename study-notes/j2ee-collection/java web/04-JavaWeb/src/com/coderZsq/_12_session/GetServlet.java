package com.coderZsq._12_session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

// 输出收件箱界面
@WebServlet("/session/get")
public class GetServlet extends HttpServlet {
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
        out.println("泉哥, 我也要请你吃饭!");
    }
}
