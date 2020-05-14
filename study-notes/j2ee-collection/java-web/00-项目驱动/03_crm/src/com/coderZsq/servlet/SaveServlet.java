package com.coderZsq.servlet;

import com.coderZsq.Data;
import com.coderZsq.bean.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/save")
public class SaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 获取请求参数
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String height = request.getParameter("height");

        // 转成Java Bean对象
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAge(Integer.valueOf(age));
        customer.setHeight(Double.valueOf(height));
        Data.add(customer);

        // 重定向
        response.sendRedirect("/crm/list");

        // request.getRequestDispatcher("/list").forward(request, response);
    }
}
