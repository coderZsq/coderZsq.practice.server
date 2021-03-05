package com.sq.dp.designpattern.factory.abstractfactory;

/**
 * 微信的人脸识别对象
 */
public class WechatFaceDetect extends FaceDetect {

    @Override
    void build() {
        System.out.println("构建微信人脸识别环境");
    }
}
