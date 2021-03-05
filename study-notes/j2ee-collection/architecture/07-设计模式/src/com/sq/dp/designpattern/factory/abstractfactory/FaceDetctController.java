package com.sq.dp.designpattern.factory.abstractfactory;

/**
 * 人脸识别的消费方
 */
public class FaceDetctController {
    private AbstractCloudApiFactory apiFactory;

    public void setApiFactory(AbstractCloudApiFactory apiFactory) {
        this.apiFactory = apiFactory;
    }

    public void faceDetect() {
        FaceDetect fd = apiFactory.createFaceDetect();

        fd.build();
        fd.signature();
        fd.verifySignature();
        fd.faceDetect();
    }
}
