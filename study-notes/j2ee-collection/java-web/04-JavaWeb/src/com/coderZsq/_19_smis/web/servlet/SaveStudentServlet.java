package com.coderZsq._19_smis.web.servlet;

import com.coderZsq._19_smis.domain.Student;
import com.coderZsq._19_smis.dao.IStudentDAO;
import com.coderZsq._19_smis.dao.impl.StudentDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 保存学生信息
@WebServlet("/student/save")
public class SaveStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IStudentDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new StudentDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 1. 接收请求参数, 封装成对象
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        Student stu = new Student(name, Integer.valueOf(age));
        // 2. 调用业务方法处理请求
        dao.save(stu);
        // 3. 控制界面跳转
        resp.sendRedirect("/student/list");
    }
}
