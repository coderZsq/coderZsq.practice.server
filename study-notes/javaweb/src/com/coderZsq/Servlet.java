package com.coderZsq;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ser")
public class Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 重定向到index.jsp
//        resp.sendRedirect("/index.jsp");
        // 请求转发到index.jsp
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
