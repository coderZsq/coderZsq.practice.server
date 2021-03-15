package com.sq.dp.designpattern.factory.abstractfactory;

/**
 * 微信的web支付对象
 */
public class WechatWebPay extends Pay {

    @Override
    void build() {
        System.out.println("构建微信WEB支付环境");
    }
}
