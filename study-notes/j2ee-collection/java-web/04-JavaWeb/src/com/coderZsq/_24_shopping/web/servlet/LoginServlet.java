package com.coderZsq._24_shopping.web.servlet;

import com.coderZsq._24_shopping.dao.IUserDAO;
import com.coderZsq._24_shopping.dao.impl.UserDAOImpl;
import com.coderZsq._24_shopping.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 处理登录请求
// @WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new UserDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接受请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 2. 调用业务方法处理请求
        User user = dao.getUserByUsername(username);
        System.out.println(user);
        // 3. 控制界面跳转
        if (user.getUsername() == null) {
            req.setAttribute("errorMsg", "亲, " + username + "该账户不存在或者被禁用, 请联系管理员!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        // 检查当前账户的密码是否正确
        if (!user.getPassword().equals(password)) {
            req.setAttribute("errorMsg", "亲, 该账户或密码不正确");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        // 把当前登录用户存储到Session中
        req.getSession().setAttribute("USER_IN_SESSION", user);
        resp.sendRedirect("/product");
    }
}
