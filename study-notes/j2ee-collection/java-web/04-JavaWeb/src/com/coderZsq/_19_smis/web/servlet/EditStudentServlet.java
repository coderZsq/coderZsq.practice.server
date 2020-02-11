package com.coderZsq._19_smis.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 进入编辑界面
@WebServlet("/student/edit")
public class EditStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收请求参数, 封装成对象
        // 2. 调用业务方法处理请求
        // 3. 控制界面跳转
        req.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(req, resp);
    }
}
