package com.sq.dp.designpattern.visitor;

/**
 * 抽象访问者
 */
abstract public class Visitor {
    private String name;

    public Visitor(String name) {
        this.name = name;
    }

    abstract public void visit(Programmer programmer);

    abstract public void visit(Tester tester);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
