package com.coderZsq._01_shopping.web.servlet;

import com.coderZsq._01_shopping.dao.IProductDAO;
import com.coderZsq._01_shopping.dao.impl.ProductDAOImpl;
import com.coderZsq._01_shopping.domain.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 处理所有student相关的请求操作
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IProductDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new ProductDAOImpl();
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = dao.list();
        req.setAttribute("products", list);
        req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req, resp);
    }
}
