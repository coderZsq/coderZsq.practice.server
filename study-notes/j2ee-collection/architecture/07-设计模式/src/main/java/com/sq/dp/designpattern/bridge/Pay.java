package com.sq.dp.designpattern.bridge;

import java.math.BigDecimal;

/**
 * 抽象化角色: 支付
 */
abstract public class Pay {
    /**
     * 组合方式: 支付模式
     */
    protected IPayMode payMode;

    public Pay(IPayMode payMode) {
        this.payMode = payMode;
    }

    abstract public boolean pay(String uId, String tradeId, BigDecimal amount);
}
