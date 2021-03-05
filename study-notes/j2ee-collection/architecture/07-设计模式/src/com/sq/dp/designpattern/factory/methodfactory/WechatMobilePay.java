package com.sq.dp.designpattern.factory.methodfactory;

public class WechatMobilePay extends Pay {

    @Override
    void build() {
        System.out.println("微信的移动端支付");
    }
}
