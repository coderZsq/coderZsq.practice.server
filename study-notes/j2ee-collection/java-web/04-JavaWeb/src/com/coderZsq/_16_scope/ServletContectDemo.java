package com.coderZsq._16_scope;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 演示ServletContext接口中的方法
@WebServlet("/app")
public class ServletContectDemo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext app1 = super.getServletContext();
        ServletContext app2 = req.getServletContext();
        ServletContext app3 = req.getSession().getServletContext();
        System.out.println(app1 == app2);
        System.out.println(app1 == app3);
        System.out.println(app2 == app3);
        // ---------------------------------------
        // String getRealPath(String path); 根据一个资源的相对Web根的路径, 获取它的绝对路径
        String realPath = super.getServletContext().getRealPath("/WEB-INF/in.html");
        System.out.println(realPath);
        System.out.println("-->" + req.getContextPath());
        System.out.println("-->" + super.getServletContext().getContextPath());

        System.out.println(super.getServletContext().getInitParameter("encoding"));
        System.out.println(super.getInitParameter("encoding"));
    }
}
