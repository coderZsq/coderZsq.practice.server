package com.coderZsq.example.web.action;

import com.coderZsq.example.dao.IEmployeeDAO;
import com.coderZsq.example.dao.impl.EmployeeDAOImpl;
import com.coderZsq.example.domain.Employee;
import com.coderZsq.example.query.EmployeeQueryObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;

public class EmployeeAction extends ActionSupport {

    private static final String LIST = "list";

    @Getter
    private Employee employee = new Employee();

    private IEmployeeDAO dao = new EmployeeDAOImpl();

    @Getter
    private EmployeeQueryObject queryObject = new EmployeeQueryObject();

    @Override
    public String execute() throws Exception {
        System.out.println("EmployeeAction.execute");
        // 把数据共享到context区域, JSP获取: #employees
        ActionContext.getContext().put("employees", dao.query(queryObject));
        return LIST;
    }

    @Override
    public String input() throws Exception {
        System.out.println("EmployeeAction.input");
        if (employee.getId() != null) {
            employee = dao.get(employee.getId());
        }
        return INPUT;
    }

    public String saveOrUpdate() throws Exception {
        System.out.println("EmployeeAction.saveOrUpdate");
        if (employee.getId() == null) {
            dao.save(employee);
        } else {
            dao.update(employee);
        }
        return SUCCESS;
    }

    public String delete() throws Exception {
        System.out.println("EmployeeAction.delete");
        if (employee.getId() != null) {
            dao.delete(employee);
        }
        return SUCCESS;
    }
}
