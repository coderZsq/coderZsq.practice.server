package com.sq.dp.designpattern.builder;

public class Concrete2Builder extends AbstractBuilder {
    @Override
    public void buildPartA() {
        System.out.println("正在构建部件A");
        product.setAttrA("部件A");
    }

    @Override
    public void buildPartB() {
        System.out.println("正在构建部件B");
        product.setAttrB("部件B");
    }

    @Override
    public void buildPartC() {
        System.out.println("正在构建部件C");
        product.setAttrC("部件C");
    }
}
