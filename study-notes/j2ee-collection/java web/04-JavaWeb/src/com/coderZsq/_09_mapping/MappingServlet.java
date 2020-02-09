package com.coderZsq._09_mapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value ="/m3",
    initParams = {
        @WebInitParam(name = "encoding", value = "UTF-8"),
            @WebInitParam(name = "name", value = "coderZsq")
    },
        loadOnStartup = 1
)
public class MappingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String name;

    public MappingServlet() {
        System.out.println("MappingServlet.MappingServlet()");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("MappingServlet.init()");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("MappingServlet.service()");

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.print("你输入的名字: " + name);
    }
}
