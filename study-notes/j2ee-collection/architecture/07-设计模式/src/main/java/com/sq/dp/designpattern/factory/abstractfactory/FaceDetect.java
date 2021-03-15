package com.sq.dp.designpattern.factory.abstractfactory;

/**
 * 抽象的人脸识别对象
 */
public abstract class FaceDetect {
    protected String name;

    abstract void build();

    public String signature() {
        System.out.println(name + " 正在创建人脸识别签名");
        return "签名";
    }

    public boolean verifySignature() {
        System.out.println(name + " 在验证人脸识别签名");
        return true;
    }

    public void faceDetect() {
        System.out.println(name + " 正在进行人脸识别");
    }

    public void setName(String name) {
        this.name = name;
    }
}
