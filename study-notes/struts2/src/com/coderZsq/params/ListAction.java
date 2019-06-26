package com.coderZsq.params;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class ListAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private List<Long> ids;

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    private List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        return userList;
    }

    @Override
    public String execute() throws Exception {
        System.out.println(userList);
        return NONE;
    }
}
