package com.sq.dp.designpattern.chainofresponsebility;

public class Client {
    public static void main(String[] args) {
        Handler director = new Director("小明主管");
        Handler manager = new Manager("小小明经理");
        Handler generalManager = new GeneralManager("小小小明总经理");

        // 简历责任链
        director.setNext(manager);
        manager.setNext(generalManager);

        Request req = new Request("大明", "事假", 9);
        boolean ret = director.handle(req);
        System.out.println("请假结果: " + ret);
    }
}
