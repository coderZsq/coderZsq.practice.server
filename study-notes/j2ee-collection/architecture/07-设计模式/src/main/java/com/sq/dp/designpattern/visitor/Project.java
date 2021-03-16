package com.sq.dp.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private List<Employee> emps = new ArrayList<>();

    public void add(Employee emp) {
        emps.add(emp);
    }

    public void remove(Employee emp) {
        emps.remove(emp);
    }

    public void accept(Visitor visitor) {
        System.out.println("管理员: " + visitor.getName() + " 开始检查各成员项目状态");
        for (Employee emp : emps) {
            emp.accept(visitor);
        }
    }
}
