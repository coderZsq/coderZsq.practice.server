package com.sq.dp.designpattern.factory.abstractfactory;

/**
 * 阿里工厂类
 */
public class WebAliFactory extends AbstractCloudApiFactory {
    @Override
    public Pay createPay() {
        return new AliWebPay();
    }

    @Override
    public FaceDetect createFaceDetect() {
        return new AliFaceDetect();
    }
}
