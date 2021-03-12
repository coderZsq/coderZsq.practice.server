package com.sq.dp.designpattern.bridge;

/**
 * 具体实现化角色: 人脸支付模式
 */
public class FacadePayMode implements IPayMode {
    @Override
    public boolean security(String uId) {
        System.out.println("人脸识别成功: " + uId);
        return true;
    }
}
