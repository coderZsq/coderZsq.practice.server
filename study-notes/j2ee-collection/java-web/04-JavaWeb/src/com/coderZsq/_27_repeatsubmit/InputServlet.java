package com.coderZsq._27_repeatsubmit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/input")
public class InputServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 生成一个随机数
        String token = UUID.randomUUID().toString();
        // 存放于session中, 将来用来做判断
        req.getSession().setAttribute("TOKEN_IN_SESSION", token);
        // 把口令共享在request中, 在表单中获取
        req.setAttribute("token", token);
        // 跳转到input.jsp
        req.getRequestDispatcher("/repeatsubmit/input.jsp").forward(req, resp);
    }
}
