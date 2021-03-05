package com.sq.dp.designpattern.factory.simplefactory;

public class AliPay extends Pay {
    @Override
    void build() {
        System.out.println("构建支付宝支付环境");
    }
}
