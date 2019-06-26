package com.coderZsq.multimethod;

import com.opensymphony.xwork2.ActionSupport;

public class EmpolyeeAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    public String execute() {
        System.out.println("员工列表");
        return NONE;
    }

    public void edit() {
        System.out.println("编辑员工");
    }

    public void save() {
        System.out.println("保存员工");
    }

    public void delete() {
        System.out.println("删除员工");
    }

}
