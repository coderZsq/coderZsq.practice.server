package com.sq.dp.designpattern.factory.methodfactory;

/**
 * 创建银联支付的工厂类
 */
public class BankPayFactory extends AbstractPayFactory {
    @Override
    public Pay createPay() {
        return new BankPay();
    }
}
