package com.sq.dp.designpattern.builder;

/**
 * 建造者模式: 由建造者造出来的产品类
 */
public class Product {
    private String attrA;
    private String attrB;
    private String attrC;

    public void setAttrA(String attrA) {
        this.attrA = attrA;
    }

    public void setAttrB(String attrB) {
        this.attrB = attrB;
    }

    public void setAttrC(String attrC) {
        this.attrC = attrC;
    }

    public void display() {
        System.out.println("商品: 属性A=" + this.attrA + ", 属性B=" + this.attrB + ", 属性C=" + this.attrC);
    }
}
