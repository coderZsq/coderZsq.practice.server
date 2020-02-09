package com.coderZsq._11_cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

// 输出收件箱界面
@WebServlet("/cookie/get")
public class GetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String username = "";
        // ===================================
        // 获取多个Cookie
        Cookie[] cs = req.getCookies();
        for (Cookie cookie : cs) {
            String name = cookie.getName();
            String value = cookie.getValue();
            if ("currentName".equals(name)) {
                username = URLDecoder.decode(value, "UTF-8");
            }
        }
        // ===================================
        out.println("欢迎: " + username + "<br>");
        out.println("泉哥, 我也要请你吃饭!");
    }
}
