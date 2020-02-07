package com.coderZsq._03_extends;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class Servlet1 extends MyGenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String encoding = super.getInitParameter("encoding");
        System.out.println(encoding);
    }
}
