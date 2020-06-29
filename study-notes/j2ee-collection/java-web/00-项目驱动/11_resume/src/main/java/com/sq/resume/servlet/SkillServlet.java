package com.sq.resume.servlet;

import com.sq.resume.bean.Education;
import com.sq.resume.bean.Skill;
import com.sq.resume.service.EducationService;
import com.sq.resume.service.SkillService;
import com.sq.resume.service.impl.EducationServiceImpl;
import com.sq.resume.service.impl.SkillServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/skill/*")
public class SkillServlet extends BaseServlet {
    private SkillService service = new SkillServiceImpl();

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("skills", service.list());
        request.getRequestDispatcher("/WEB-INF/page/admin/skill.jsp").forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Skill skill = new Skill();
        BeanUtils.populate(skill, request.getParameterMap());
        if (service.save(skill)) { // 保存成功
            // 重定向到admin
            response.sendRedirect(request.getContextPath() + "/skill/admin");
        } else { // 保存失败
            request.setAttribute("error", "专业技能保存失败");
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request, response);
        }
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] idStrs = request.getParameterValues("id");
        List<Integer> ids = new ArrayList<>();
        for (String idStr : idStrs) {
            ids.add(Integer.valueOf(idStr));
        }
        // 删除
        if (service.remove(ids)) {
            response.sendRedirect(request.getContextPath() + "/skill/admin");
        } else {
            request.setAttribute("error", "专业技能删除失败");
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request, response);
        }
    }
}
