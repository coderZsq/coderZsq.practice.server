package com.coderZsq.shopping.web.servlet;

import com.coderZsq.shopping.dao.IProductDAO;
import com.coderZsq.shopping.dao.IProductDirDAO;
import com.coderZsq.shopping.dao.impl.ProductDAOImpl;
import com.coderZsq.shopping.dao.impl.ProductDirDAOImpl;
import com.coderZsq.shopping.domain.Product;
import com.coderZsq.shopping.page.PageResult;
import com.coderZsq.shopping.query.ProductQueryObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/page")
public class PageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IProductDAO dao;
    private IProductDirDAO dirDAO;

    @Override
    public void init() throws ServletException {
        dao = new ProductDAOImpl();
        dirDAO = new ProductDirDAOImpl();
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // -----------------------------------
        Integer currentPage = 1;
        // 接受用户传入的当前页
        String sCurrentPage = req.getParameter("currentPage");
        if (StringUtils.isNotBlank(sCurrentPage)) {
            currentPage = Integer.valueOf(sCurrentPage);
        }
        PageResult pageResult = dao.query(currentPage, 5);
        req.setAttribute("pageResult", pageResult);
        // -----------------------------------
        req.setAttribute("dirs", dirDAO.list());
        req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req, resp);
    }
}
