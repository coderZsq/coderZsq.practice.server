package com.sq.dp.designpattern.factory.abstractfactory;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        System.out.println("------ 支付测试开始 ------");
        PayController payController = new PayController();
        payController.setApiFactory(new WebAliFactory());
        payController.pay();
        System.out.println("------ 支付测试结束 ------");

        System.out.println();

        System.out.println("------ 人脸识别测试开始 ------");
        FaceDetctController faceDetctController = new FaceDetctController();
        faceDetctController.setApiFactory(new WebAliFactory());
        faceDetctController.faceDetect();
        System.out.println("------ 人脸识别测试结束 ------");
    }
}
