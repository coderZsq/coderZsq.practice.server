package com.sq.dp.designpattern.visitor;

/**
 * 抽象元素
 */
abstract public class Employee {
    private String name;
    private int delayDays;

    public Employee(String name) {
        this.name = name;
    }

    abstract public void accept(Visitor visitor);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(int delayDays) {
        this.delayDays = delayDays;
    }
}
