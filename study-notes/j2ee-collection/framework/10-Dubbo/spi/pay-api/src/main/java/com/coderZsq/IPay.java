package com.coderZsq;

public interface IPay {
    /**
     * 给指定用户支付金额
     * @param name
     * @param amount
     */
    public void pay(String name, Integer amount);
}
