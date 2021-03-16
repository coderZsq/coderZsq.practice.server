package com.sq.dp.designpattern.visitor;

/**
 * 具体访问者: 项目经理
 */
public class ProjectManager extends Visitor {
    public ProjectManager(String name) {
        super(name);
    }

    @Override
    public void visit(Programmer programmer) {
        System.out.println("程序员: " + programmer.getName() + "\t延迟天数: " + programmer.getDelayDays());
    }

    @Override
    public void visit(Tester tester) {
        System.out.println("测试员: " + tester.getName() + "\t延迟天数: " + tester.getDelayDays());
    }
}
