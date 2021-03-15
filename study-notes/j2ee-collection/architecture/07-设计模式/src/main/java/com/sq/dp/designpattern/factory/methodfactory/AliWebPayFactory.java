package com.sq.dp.designpattern.factory.methodfactory;

/**
 * 创建阿里web支付的工厂类
 */
public class AliWebPayFactory extends AbstractPayFactory {
    @Override
    public Pay createPay() {
        AliWebPay aliWebPay = new AliWebPay();
        aliWebPay.setName("阿里web支付");
        return aliWebPay;
    }
}
