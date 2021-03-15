package com.sq.dp.designpattern.factory.methodfactory;

public class AliWebPay extends Pay {

    @Override
    void build() {
        System.out.println("阿里的web支付");
    }
}
