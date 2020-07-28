package com.sq.resume.servlet;

import com.sq.resume.bean.Contact;
import com.sq.resume.bean.ContactListParam;
import com.sq.resume.service.ContactService;
import com.sq.resume.service.UserService;
import com.sq.resume.service.WebsiteService;
import com.sq.resume.service.impl.UserServiceImpl;
import com.sq.resume.service.impl.WebsiteServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contact/*")
public class ContactServlet extends BaseServlet<Contact> {
    private UserService userService = new UserServiceImpl();
    private WebsiteService websiteService = new WebsiteServiceImpl();

    public void front(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("user", userService.list().get(0));
        request.setAttribute("footer", websiteService.list().get(0).getFooter());
        // 转发
        forward(request, response, "front/contact.jsp");
    }

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ContactListParam param = new ContactListParam();
        BeanUtils.populate(param, request.getParameterMap());
        request.setAttribute("result", ((ContactService) service).list(param));
        forward(request, response, "admin/contact.jsp");
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 检查验证码
        String code = (String) request.getSession().getAttribute("code");
        String captcha = request.getParameter("captcha");
        if (!code.equals(captcha)) {
            forwardError(request, response, "验证码错误");
            return;
        }

        Contact contact = new Contact();
        BeanUtils.populate(contact, request.getParameterMap());
        if (service.save(contact)) { // 保存成功
            // 重定向到admin
            redirect(request, response, "contact/front");
        } else { // 保存失败
            forwardError(request, response, "留言信息保存失败");
        }
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}
