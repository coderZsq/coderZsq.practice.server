package com.coderZsq._24_shopping.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 方式1: 删除session中key为USER_IN_SESSION的属性
        req.getSession().removeAttribute("USER_IN_SESSION");
        // 方式2: 销毁session对象
        req.getSession().invalidate();
        // 重新回到登录页面
        resp.sendRedirect("/login.jsp");
    }
}
