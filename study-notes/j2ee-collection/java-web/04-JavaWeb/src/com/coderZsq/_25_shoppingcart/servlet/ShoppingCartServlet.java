package com.coderZsq._25_shoppingcart.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 处理购物车的添加/删除
@WebServlet("/shoppingcart")
public class ShoppingCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
