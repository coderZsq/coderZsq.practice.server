package com.sq.dp.designpattern.bridge;

/**
 * 实现化接口: 支付模式
 */
public interface IPayMode {
    boolean security(String uId);
}
