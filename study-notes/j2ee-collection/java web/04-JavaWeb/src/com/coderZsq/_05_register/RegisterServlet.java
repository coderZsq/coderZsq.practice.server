package com.coderZsq._05_register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

// 处理注册的请求
@WebServlet("/reg")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String city = req.getParameter("city");
        String intro = req.getParameter("intro");
        String[] hobbys = req.getParameterValues("hobby");

        System.out.println(username);
        System.out.println(password);
        System.out.println(gender);
        System.out.println(city);
        System.out.println(intro);
        System.out.println(Arrays.toString(hobbys));
    }
}
