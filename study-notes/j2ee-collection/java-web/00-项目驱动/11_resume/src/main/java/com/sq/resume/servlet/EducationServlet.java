package com.sq.resume.servlet;

import com.sq.resume.bean.Education;
import com.sq.resume.dao.EducationDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/education/*")
public class EducationServlet extends BaseServlet {
    private EducationDao dao = new EducationDao();

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("educations", dao.list());
        request.getRequestDispatcher("/page/admin/education.jsp").forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Education education = new Education();
        BeanUtils.populate(education, request.getParameterMap());
        if (dao.save(education)) { // 保存成功
            // 重定向到admin
            response.sendRedirect(request.getContextPath() + "/education/admin");
        } else { // 保存失败
            request.setAttribute("error", "教育信息保存失败");
            request.getRequestDispatcher("/page/error.jsp").forward(request, response);
        }
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] idStrs = request.getParameterValues("id");
        List<Integer> ids = new ArrayList<>();
        for (String idStr : idStrs) {
            ids.add(Integer.valueOf(idStr));
        }
        System.out.println(ids);
        // 删除
        if (dao.remove(ids)) {
            response.sendRedirect(request.getContextPath() + "/education/admin");
        } else {
            request.setAttribute("error", "教育信息删除失败");
            request.getRequestDispatcher("/page/error.jsp").forward(request, response);
        }
    }
}