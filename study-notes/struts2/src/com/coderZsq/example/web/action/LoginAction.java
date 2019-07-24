package com.coderZsq.example.web.action;

import com.coderZsq.example.dao.IEmployeeDAO;
import com.coderZsq.example.dao.impl.EmployeeDAOImpl;
import com.coderZsq.example.domain.Employee;
import com.coderZsq.example.util.MD5;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Setter;

public class LoginAction extends ActionSupport {

    @Setter
    private String username;

    @Setter
    private String password;

    private IEmployeeDAO dao = new EmployeeDAOImpl();

    @Override
    public String execute() throws Exception {
        Employee current = dao.getEmployeeByName(username);
        if (current != null) {
            if (current.getPassword().equals(MD5.encode(", " + password))) {
                ActionContext.getContext().getSession().put("user_in_session", current);
                return SUCCESS;
            }
        }
        super.addActionError("亲, 账号或密码不正确!");
        return LOGIN;
    }
}
