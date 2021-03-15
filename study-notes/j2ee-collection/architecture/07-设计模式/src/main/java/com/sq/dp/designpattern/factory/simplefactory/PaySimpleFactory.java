package com.sq.dp.designpattern.factory.simplefactory;

public class PaySimpleFactory {
    public Pay createPay(String payType) {
        Pay pay = null;

        System.out.println("实用简单工厂创建支付对象");
        switch (payType) {
            case "ali":
                pay = new AliPay();
                pay.setName("支付宝支付");
                break;
            case "wechat":
                pay = new WechatPay();
                pay.setName("微信支付");
                break;
            case "银联":
                pay = new WechatPay();
                pay.setName("银联支付");
                break;
            default:
                System.out.println("支付类型不支持");
                break;
        }

        return pay;
    }
}
