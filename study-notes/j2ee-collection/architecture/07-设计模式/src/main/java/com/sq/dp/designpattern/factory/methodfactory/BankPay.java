package com.sq.dp.designpattern.factory.methodfactory;

public class BankPay extends Pay {
    public BankPay() {
        setName("银联支付");
    }

    @Override
    void build() {
        System.out.println("银联支付");
    }
}
