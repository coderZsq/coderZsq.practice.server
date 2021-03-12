package com.sq.dp.designpattern.bridge;

/**
 * 具体实例化角色: 指纹支付模式
 */
public class FingerprintPayMode implements IPayMode {
    @Override
    public boolean security(String uId) {
        System.out.println("指纹识别成功: " + uId);
        return true;
    }
}
