package com.coderZsq;

public class AliPay implements IPay {
    public void pay(String name, Integer amount) {
        System.out.println("支付宝支付平台");
        System.out.println("name = " + name + ", amount = " + amount);
    }
}
