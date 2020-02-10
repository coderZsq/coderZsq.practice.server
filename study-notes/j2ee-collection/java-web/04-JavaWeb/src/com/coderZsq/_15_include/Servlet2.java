package com.coderZsq._15_include;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/include/s2")
public class Servlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        // =================================
        System.out.println("Servlet2......" + req.getParameter("name"));
        out.println("Servlet2......");
    }
}
