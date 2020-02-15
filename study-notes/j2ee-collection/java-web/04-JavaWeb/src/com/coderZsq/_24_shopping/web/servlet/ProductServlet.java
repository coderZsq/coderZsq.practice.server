package com.coderZsq._24_shopping.web.servlet;

import com.coderZsq._24_shopping.dao.IProductDAO;
import com.coderZsq._24_shopping.dao.IProductDirDAO;
import com.coderZsq._24_shopping.dao.impl.ProductDAOImpl;
import com.coderZsq._24_shopping.dao.impl.ProductDirDAOImpl;
import com.coderZsq._24_shopping.domain.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

// 处理所有student相关的请求操作
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IProductDAO dao;
    private IProductDirDAO dirDAO;

    @Override
    public void init() throws ServletException {
        dao = new ProductDAOImpl();
        dirDAO = new ProductDirDAOImpl();
    }

    // 分发
    // http://localhost/product进入service方法, 到底是保存, 删除, 查询
    // http://localhost/product?cmd=save // 保存操作
    // http://localhost/product?cmd=delete // 删除操作
    // http://localhost/product?cmd=edit // 编辑操作
    // http://localhost/product
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ----------------------------------------
        // 检查用户是否已经登录, 判断session中是否存在USER_IN_SESSION
        Object user = req.getSession().getAttribute("USER_IN_SESSION");
        if (user == null) {
            // 回到登录页面
            resp.sendRedirect("/login.jsp");
            return;
        }
        // ----------------------------------------
        req.setCharacterEncoding("UTF-8"); // 对POST有效, 必须放在获取第一个参数之前
        String cmd = req.getParameter("cmd");
        if ("save".equals(cmd)) {
            this.saveOrUpdate(req, resp);
        } else if ("edit".equals(cmd)) {
            this.edit(req, resp);
        } else if ("delete".equals(cmd)) {
            this.delete(req, resp);
        } else {
            this.list(req, resp);
        }
    }

    // 显示商品列表
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收请求参数, 封装成对象
        // 2. 调用业务方法处理请求
        List<Product> list = dao.list();
        // 3. 控制界面跳转
        // 共享数据
        req.setAttribute("products", list);
        // 跳转(如果要共享请求中的数据, 只能使用请求转发)
        req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req, resp);
    }

    // 删除指定商品
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收请求参数, 封装成对象
        Long id = Long.valueOf(req.getParameter("id"));
        // 2. 调用业务方法处理请求
        dao.delete(id);
        // 3. 控制界面跳转
        resp.sendRedirect(req.getContextPath() +  "/product");
    }

    // 进入编辑界面
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收请求参数, 封装成对象
        String id = req.getParameter("id");
        // 2. 调用业务方法处理请求
        if (hasLength(id)) {
            Product p = dao.get(Long.valueOf(id));
            req.setAttribute("product", p); // 传递edit.jsp, 用户回显
        }
        // 3. 控制界面跳转
        // 把所有的商品分类查询出来, 用于选择商品的分类
        req.setAttribute("dirs", dirDAO.list());
        req.getRequestDispatcher("/WEB-INF/views/product/edit.jsp").forward(req, resp);
    }

    // 新增或更新操作
    protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收请求参数, 封装成对象
        Product p = new Product();
        this.request2Obj(req, p);
        // 2. 调用业务方法处理请求
        if (p.getId() != null) {
            dao.update(p);
        } else {
            dao.save(p);
        }
        // 3. 控制界面跳转
        resp.sendRedirect("/product");
    }

    // 把请求中的参数封装到Product对象
    private void request2Obj(HttpServletRequest req, Product p) {
        String id = req.getParameter("id");
        String productName = req.getParameter("productName");
        String brand = req.getParameter("brand");
        String supplier = req.getParameter("supplier");
        String salePrice = req.getParameter("salePrice");
        String costPrice = req.getParameter("costPrice");
        String cutoff = req.getParameter("cutoff");
        String dir_id = req.getParameter("dir_id");

        // 省略非空判断
        if (hasLength(id)) {
            p.setId(Long.valueOf(id));
        }
        p.setProductName(productName);
        p.setBrand(brand);
        p.setSupplier(supplier);
        p.setSalePrice(new BigDecimal(salePrice));
        p.setCostPrice(new BigDecimal(costPrice));
        p.setCutoff(Double.valueOf(cutoff));
        p.setDir_id(Long.valueOf(dir_id));
    }

    private boolean hasLength(String str) {
        return str != null && !"".equals(str.trim());
    }
}
