package com.coderZsq;

import java.util.ServiceLoader;

public class App {
    public static void main(String[] args) {
        // 获取到支付方式 jdk的spi的api接口
        ServiceLoader<IPay> loader = ServiceLoader.load(IPay.class);
        // 直接使用迭代器进行操作
        for (IPay pay : loader) {
            pay.pay("9527", 200);
        }
    }
}
