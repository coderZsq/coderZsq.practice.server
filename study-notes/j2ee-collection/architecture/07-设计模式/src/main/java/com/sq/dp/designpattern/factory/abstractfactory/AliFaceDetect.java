package com.sq.dp.designpattern.factory.abstractfactory;

/**
 * 阿里的人脸识别对象
 */
public class AliFaceDetect extends FaceDetect {
    public AliFaceDetect() {
        setName("阿里人脸识别");
    }

    @Override
    void build() {
        System.out.println("构建支付宝人脸识别环境");
    }
}
