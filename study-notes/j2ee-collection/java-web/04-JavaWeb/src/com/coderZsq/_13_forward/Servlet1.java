package com.coderZsq._13_forward;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/forward/s1")
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
        // 请求转发
        // 没有加:/,使用当前资源的相对路径
        // 资源路径:/forward/s1,相对路径, 找到最后一个/,之前的部分就是相对路径: /forward
        // req.getRequestDispatcher("s2").forward(req, resp);
        // req.getRequestDispatcher("/forward/s2").forward(req, resp);
        // 访问WEB-INF中的资源
        req.getRequestDispatcher("/WEB-INF/in.html").forward(req, resp);
        // ----------------------------------
        System.out.println("Servlet1......after");
        out.println("Servlet1...after");
    }
}
