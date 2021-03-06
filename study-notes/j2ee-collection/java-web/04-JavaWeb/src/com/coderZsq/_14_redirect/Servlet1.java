package com.coderZsq._14_redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect/s1")
public class Servlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        // ==================================
        System.out.println("Servlet1......before" + req.getParameter("name"));
        out.println("Servlet...before");
        // ----------------------------------
        // URL重定向
        // resp.sendRedirect("/redirect/s2");
        resp.sendRedirect("https://www.baidu.com");
        // ----------------------------------
        System.out.println("Servlet1......after");
        out.println("Servlet1...after");
    }
}
