package com.sq.dp.designpattern.visitor;

public class Client {
    public static void main(String[] args) {
        // 对象结构: 项目 -> 包含了多个参与项目的员工
        Project project = new Project();

        // 添加在项目的程序员
        project.add(new Programmer("小明"));
        project.add(new Programmer("小红"));
        project.add(new Programmer("小黄"));

        // 添加在项目的测试
        project.add(new Tester("小黑"));
        project.add(new Tester("小蓝"));
        project.add(new Tester("小白"));

        // 创建两个访问者: 技术经理, 项目经理
        Visitor tm = new TechnicalManager("大明");
        Visitor pm = new ProjectManager("二明");

        // 技术经理审查项目
        project.accept(tm);
        // 项目经理审查项目
        project.accept(pm);
    }
}
