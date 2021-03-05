package com.sq.dp.designpattern.factory.simplefactory;

/**
 * 产品类: 抽象支付对象
 */
public abstract class Pay {
    protected String name;

    abstract void build();

    public String signature() {
        System.out.println(name + " 正在签名");

        return "签名";
    }

    public boolean verifySignature() {
        System.out.println(name + " 在验证签名");
        return true;
    }

    public void payment() {
        System.out.println(name + " 开始支付了");
    }

    public void setName(String name) {
        this.name = name;
    }
}
