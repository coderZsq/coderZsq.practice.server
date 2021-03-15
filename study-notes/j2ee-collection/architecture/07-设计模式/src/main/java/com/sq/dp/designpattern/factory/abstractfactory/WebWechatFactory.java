package com.sq.dp.designpattern.factory.abstractfactory;

/**
 * 微信工厂类
 */
public class WebWechatFactory extends AbstractCloudApiFactory {
    @Override
    public Pay createPay() {
        return new WechatWebPay();
    }

    @Override
    public FaceDetect createFaceDetect() {
        return new WechatFaceDetect();
    }
}
