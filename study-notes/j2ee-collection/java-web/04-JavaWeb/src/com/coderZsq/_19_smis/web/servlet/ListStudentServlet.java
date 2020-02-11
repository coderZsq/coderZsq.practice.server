package com.coderZsq._19_smis.web.servlet;

import com.coderZsq._19_smis.dao.IStudentDAO;
import com.coderZsq._19_smis.dao.impl.StudentDAOImpl;
import com.coderZsq._19_smis.domain.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 处理学生列表的请求
@WebServlet("/student/list")
public class ListStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IStudentDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new StudentDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收请求参数, 封装成对象
        // 2. 调用业务方法处理请求
        List<Student> list = dao.listAll();
        // 3. 控制界面跳转
        // 共享数据
        req.setAttribute("students", list);
        // 跳转(如果要共享请求中的数据, 只能使用请求转发)
        req.getRequestDispatcher("/WEB-INF/views/student/list.jsp").forward(req, resp);
    }
}
