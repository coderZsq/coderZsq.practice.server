package com.sq.dp.designpattern.bridge;

import java.math.BigDecimal;

/**
 * 具体抽象化角色: 银联支付
 */
public class BankPay extends Pay {

    public BankPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public boolean pay(String uId, String tradeId, BigDecimal amount) {
        System.out.println("银联支付: uId=" + uId + ", tradeId=" + tradeId + ", amount=" + amount);
        boolean security = payMode.security(uId);
        System.out.println("银联支付结果: " + security);
        return security;
    }
}
