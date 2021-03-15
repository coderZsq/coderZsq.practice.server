package com.sq.dp.designpattern.factory.methodfactory;

/**
 * 创建微信移动端支付的工厂类
 */
public class WechatMobilePayFactory extends AbstractPayFactory {
    @Override
    public Pay createPay() {
        WechatMobilePay pay = new WechatMobilePay();
        pay.setName("微信移动端支付");
        return pay;
    }
}
