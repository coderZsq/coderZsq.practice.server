package com.sq.dp.designpattern.factory.simplefactory;

public class WechatPay extends Pay {
    @Override
    void build() {
        System.out.println("构建微信支付环境");
    }
}
