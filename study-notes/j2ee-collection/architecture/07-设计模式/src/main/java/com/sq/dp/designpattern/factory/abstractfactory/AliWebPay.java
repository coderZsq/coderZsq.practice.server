package com.sq.dp.designpattern.factory.abstractfactory;

/**
 * 阿里web支付对象
 */
public class AliWebPay extends Pay {
    public AliWebPay() {
        setName("阿里web支付");
    }

    @Override
    void build() {
        System.out.println("构建支付宝WEB支付环境");
    }
}
