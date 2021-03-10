package com.sq.dp.designpattern.builder;

/**
 * 建造者模式: 抽象建造者
 */
abstract public class AbstractBuilder {
    protected Product product = null;

    public AbstractBuilder() {
        product = new Product();
    }

    public abstract void buildPartA();

    public abstract void buildPartB();

    public abstract void buildPartC();

    public Product getProduct() {
        return this.product;
    }

    public Product build() {
        buildPartA();
        buildPartB();
        buildPartC();
        return this.product;
    }
}
