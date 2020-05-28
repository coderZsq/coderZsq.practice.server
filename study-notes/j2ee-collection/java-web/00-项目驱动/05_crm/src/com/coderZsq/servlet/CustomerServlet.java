package com.coderZsq.servlet;

import com.coderZsq.bean.Customer;
import com.coderZsq.dao.CustomerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet("/customer/*")
public class CustomerServlet extends HttpServlet {
    private final CustomerDao dao = new CustomerDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 利用方法名调用方法
            String uri = request.getRequestURI();
            String[] cmps = uri.split("/");
            String methodName = cmps[cmps.length - 1];
            Method method = getClass()
                    .getMethod(methodName,
                            HttpServletRequest.class,
                            HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");

        // 获取客户端发送的参数
        Customer customer = new Customer();
        customer.setName(request.getParameter("name"));
        customer.setAge(Integer.parseInt(request.getParameter("age")));
        customer.setHeight(Double.parseDouble(request.getParameter("height")));

        // 存储到数据库
        if (dao.save(customer)) {
            // 重定向
            response.sendRedirect("/crm/list");
        } else {
            request.setAttribute("error", "保存客户信息失败");
            request.getRequestDispatcher("/page/error.jsp").forward(request, response);
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customers", dao.list());
        request.getRequestDispatcher("/page/list.jsp").forward(request, response);
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
