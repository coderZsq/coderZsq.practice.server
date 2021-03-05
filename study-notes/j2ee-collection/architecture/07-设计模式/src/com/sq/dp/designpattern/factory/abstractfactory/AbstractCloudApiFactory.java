package com.sq.dp.designpattern.factory.abstractfactory;

/**
 * 云api抽象工厂
 */
public abstract class AbstractCloudApiFactory {
    abstract public Pay createPay();

    abstract public FaceDetect createFaceDetect();
}
