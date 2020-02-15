package com.coderZsq._25_shoppingcart.servlet;

import com.coderZsq._25_shoppingcart.domain.CartItem;
import com.coderZsq._25_shoppingcart.domain.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

// 处理购物车的添加/删除
@WebServlet("/shoppingcart")
public class ShoppingCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String cmd = req.getParameter("cmd");
        if ("save".equals(cmd)) {
            this.save(req, resp);
        } else if ("delete".equals(cmd)) {
            this.delete(req, resp);
        }
        resp.sendRedirect("/shoppingcart/cart_list.jsp");
    }

    // 添加进购物车
    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接受请求参数
        String name = req.getParameter("name");
        String num = req.getParameter("num");
        String id = "";
        BigDecimal price = BigDecimal.ZERO;
        if ("iphone".equals(name)) {
            id = "123";
            price = new BigDecimal("5000");
        } else if ("ipad".equals(name)) {
            id = "456";
            price = new BigDecimal("3000");
        } else if ("iWatch".equals(name)) {
            id = "789";
            price = new BigDecimal("10000");
        }
        CartItem item = new CartItem(id, name, price, Integer.valueOf(num));
        // 2. 调用业务方法处理请求
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("SHOPPINGCART_IN_SESSION");
        if (cart == null) {
            cart = new ShoppingCart();
            req.getSession().setAttribute("SHOPPINGCART_IN_SESSION", cart);
        }
        cart.save(item);
        // 3. 控制界面跳转
    }

    // 从购物车中移除某个商品
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接受请求参数
        String id = req.getParameter("id");
        // 2. 调用业务方法处理请求
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("SHOPPINGCART_IN_SESSION");
        cart.delete(id);
        // 3. 控制界面跳转
    }
}
