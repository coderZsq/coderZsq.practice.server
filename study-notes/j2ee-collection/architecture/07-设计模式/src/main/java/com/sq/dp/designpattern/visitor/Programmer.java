package com.sq.dp.designpattern.visitor;

import java.util.Random;

/**
 * 具体元素: 程序员
 */
public class Programmer extends Employee {
    private Integer codeNum;
    private Integer bugNum;

    public Programmer(String name) {
        super(name);
        Random random = new Random();
        this.codeNum = random.nextInt(10) * 10000;
        this.bugNum = random.nextInt(50);
        super.setDelayDays(random.nextInt(10));
        random = null;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Integer getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(Integer codeNum) {
        this.codeNum = codeNum;
    }

    public Integer getBugNum() {
        return bugNum;
    }

    public void setBugNum(Integer bugNum) {
        this.bugNum = bugNum;
    }
}
