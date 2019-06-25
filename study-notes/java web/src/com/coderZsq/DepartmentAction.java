package com.coderZsq;

// 处理Department相关的请求
public class DepartmentAction {

    public void execute() throws Exception {
        System.out.println("Department...");

        ActionContext.getContext().getRequest().getRequestDispatcher("/WEB-INF/views/department/input.jsp").forward(ActionContext.getContext().getRequest(), ActionContext.getContext().getResponse());
    }
}
