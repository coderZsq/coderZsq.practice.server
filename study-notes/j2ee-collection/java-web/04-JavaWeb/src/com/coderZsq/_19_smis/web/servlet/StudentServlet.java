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

// 处理所有student相关的请求操作
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IStudentDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new StudentDAOImpl();
    }

    // 分发
    // http://localhost/student进入service方法, 到底是保存, 删除, 查询
    // http://localhost/student?cmd=save // 保存操作
    // http://localhost/student?cmd=delete // 删除操作
    // http://localhost/student?cmd=edit // 编辑操作
    // http://localhost/student // 列表操作
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
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

    // 列表操作
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收请求参数, 封装成对象
        // 2. 调用业务方法处理请求
        List<Student> list = dao.listAll();
        // 3. 控制界面跳转
        // 共享数据
        req.setAttribute("students", list);
        // 跳转(如果要共享请求中的数据, 只能使用请求转发)
        req.getRequestDispatcher("/WEB-INF/views/student/list.jsp").forward(req, resp);
    }

    // 编辑操作
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    // 删除操作
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收请求参数, 封装成对象
        Long id = Long.valueOf(req.getParameter("id"));
        // 2. 调用业务方法处理请求
        dao.delete(id);
        // 3. 控制界面跳转
        resp.sendRedirect(req.getContextPath() +  "/student");
    }

    // 新增或更新操作
    protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收请求参数, 封装成对象
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        Student stu = new Student(name, Integer.valueOf(age));
        // 2. 调用业务方法处理请求
        String id = req.getParameter("id");
        if (hasLength(id)) { // 更新
            Long sid = Long.valueOf(id);
            stu.setId(sid);
            dao.update(sid, stu);
        } else {
            dao.save(stu);
        }
        // 3. 控制界面跳转
        resp.sendRedirect(req.getContextPath() + "/student");
    }

    private boolean hasLength(String str) {
        return str != null && !"".equals(str.trim());
    }
}
