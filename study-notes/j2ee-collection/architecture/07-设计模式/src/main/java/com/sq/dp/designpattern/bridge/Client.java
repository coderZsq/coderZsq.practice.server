package com.sq.dp.designpattern.bridge;

import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) {
        Pay pay1 = new WechatPay(new CypherPayMode());
        pay1.pay("001", "1216398710163127312390", new BigDecimal("1000.00"));

        Pay pay2 = new BankPay(new FingerprintPayMode());
        pay2.pay("002", "2163978731737219382678", new BigDecimal("1020.00"));
    }
}
