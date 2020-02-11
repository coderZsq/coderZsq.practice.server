package com.coderZsq._19_smis.web.servlet;

import com.coderZsq._19_smis.dao.IStudentDAO;
import com.coderZsq._19_smis.dao.impl.StudentDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 处理删除指定ID学生的请求
@WebServlet("/student/delete")
public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IStudentDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new StudentDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收请求参数, 封装成对象
        Long id = Long.valueOf(req.getParameter("id"));
        // 2. 调用业务方法处理请求
        dao.delete(id);
        // 3. 控制界面跳转
        resp.sendRedirect("/student/list");
    }
}
