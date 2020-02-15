package com.coderZsq._26_randomcode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/randeomLogin")
public class RandomCodeLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String randomCode = req.getParameter("randomCode");
        // -----------------------------------
        // 获取session中存储的随机数
        String randomCodeInSession = (String) req.getSession().getAttribute("RANDOMCODE_IN_SESSION");
        if (!randomCode.equalsIgnoreCase(randomCodeInSession)) {
            req.setAttribute("errorMsg", "亲,验证码不正确或已经过期!");
            req.getRequestDispatcher("/randomcode/login.jsp").forward(req, resp);
            return;
        }
        req.getSession().removeAttribute("RANDOMCODE_IN_SESSION");
        System.out.println("验证码正确");
        // -----------------------------------
        // 登录判断
        // 2. 处理请求
        // 3. 控制界面跳转
    }
}
