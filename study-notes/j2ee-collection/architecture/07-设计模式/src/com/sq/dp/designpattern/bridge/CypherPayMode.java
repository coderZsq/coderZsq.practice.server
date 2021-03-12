package com.sq.dp.designpattern.bridge;

/**
 * 具体实现化角色: 密码支付模式
 */
public class CypherPayMode implements IPayMode {
    @Override
    public boolean security(String uId) {
        System.out.println("密码验证功能: " + uId);
        return true;
    }
}
