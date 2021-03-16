package com.sq.dp.designpattern.visitor;

import java.util.Random;

/**
 * 具体元素:  测试
 */
public class Tester extends Employee {
    private Double coverage;

    public Tester(String name) {
        super(name);
        Random random = new Random();
        super.setDelayDays(random.nextInt(10));
        this.coverage = Double.valueOf(String.format("%.2f", random.nextDouble() * 100));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Double getCoverage() {
        return coverage;
    }

    public void setCoverage(Double coverage) {
        this.coverage = coverage;
    }
}
