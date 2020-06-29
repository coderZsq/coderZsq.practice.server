package com.sq.resume.servlet;

import com.sq.resume.bean.Website;
import com.sq.resume.service.WebsiteService;
import com.sq.resume.service.impl.WebsiteServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/website/*")
public class WebsiteServlet extends BaseServlet {
    private WebsiteService service = new WebsiteServiceImpl();

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Website> websites = service.list();
        Website website = (websites != null && !websites.isEmpty()) ? websites.get(0) : null;
        request.setAttribute("website", website);

        // 转发
        request.getRequestDispatcher("/WEB-INF/page/admin/website.jsp").forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Website website = new Website();
        BeanUtils.populate(website, request.getParameterMap());
        if (service.save(website)) { // 保存成功
            // 重定向到admin
            response.sendRedirect(request.getContextPath() + "/website/admin");
        } else { // 保存失败
            request.setAttribute("error", "网站信息保存失败");
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request, response);
        }
    }
}
