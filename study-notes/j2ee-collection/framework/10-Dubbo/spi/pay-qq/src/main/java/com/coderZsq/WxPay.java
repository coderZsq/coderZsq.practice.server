package com.coderZsq;

public class WxPay implements IPay {
    public void pay(String name, Integer amount) {
        System.out.println("微信支付");
        System.out.println("name = " + name + ", amount = " + amount);
    }
}
