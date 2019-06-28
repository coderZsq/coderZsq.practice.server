package com.coderZsq.tag;

import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

// 把多个数据存放在root区域
public class ListAction1 extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String execute() throws Exception {
        students = Student.listAll();
        return SUCCESS;
    }
}
