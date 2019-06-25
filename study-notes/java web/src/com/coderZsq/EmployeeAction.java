package com.coderZsq;

// 处理Employee相关的请求
public class EmployeeAction {

    private String name;
    public EmployeeAction() {
        System.out.println("构造器...");
    }

    // 返回逻辑视图名称
    public String execute() throws Exception {
        name = ActionContext.getContext().getRequest().getParameter("name");
        System.out.println("Employee..." + name);

//        ActionContext.getContext().getRequest().getRequestDispatcher("/WEB-INF/views/employee/list.jsp").forward(ActionContext.getContext().getRequest(), ActionContext.getContext().getResponse());

        return "list";
    }
}
