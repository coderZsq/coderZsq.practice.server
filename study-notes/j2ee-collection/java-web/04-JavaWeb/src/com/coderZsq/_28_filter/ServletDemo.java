package com.coderZsq._28_filter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ser")
public class ServletDemo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 重定向到hello.jsp
        // resp.sendRedirect("/hello.jsp");
        // 请求转发
        req.getRequestDispatcher("/hello.jsp").forward(req, resp);
    }
}
