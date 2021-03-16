package com.sq.dp.designpattern.visitor;

/**
 * 具体访问者: 技术经理
 */
public class TechnicalManager extends Visitor {
    public TechnicalManager(String name) {
        super(name);
    }

    @Override
    public void visit(Programmer programmer) {
        System.out.println("程序员: " + programmer.getName() + "\t代码数: " + programmer.getCodeNum() + ", Bug 数: " + programmer.getBugNum());
    }

    @Override
    public void visit(Tester tester) {
        System.out.println("测试员: " + tester.getName() + "\t测试覆盖率: " + tester.getCoverage() + "%");
    }
}
