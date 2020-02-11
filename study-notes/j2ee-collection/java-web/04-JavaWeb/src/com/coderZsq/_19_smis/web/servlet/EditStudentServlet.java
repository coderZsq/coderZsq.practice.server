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

// 进入编辑界面
@WebServlet("/student/edit")
public class EditStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IStudentDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new StudentDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收请求参数, 封装成对象
        String sid = req.getParameter("id");
        // 2. 调用业务方法处理请求
        if (hasLength(sid)) {
            Student stu = dao.get(Long.valueOf(sid));
            req.setAttribute("student", stu); // 传递edit.jsp, 用户回显被编辑的学生
        }
        // 3. 控制界面跳转
        req.getRequestDispatcher("/WEB-INF/views/student/edit.jsp").forward(req, resp);
    }

    private boolean hasLength(String str) {
        return str != null && !"".equals(str.trim());
    }
}
