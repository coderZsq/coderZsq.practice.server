package com.sq.dp.principle.ocp;

interface IAutomobile {

}

/**
 * 开闭原则
 */
public class OpenClose2 {
    private static IAutomobileFactory factory = new BENZFactory();

    public static void main(String[] args) {
        // TODO
        // 消费方的业务代码
        factory.createAuto();
        // TODO
    }
}

class Audi implements IAutomobile {
    public Audi() {
        System.out.println("h1 我是奥迪");
    }
}

class BMW implements IAutomobile {
    public BMW() {
        System.out.println("hello 我是宝马");
    }
}

class BENZ implements IAutomobile {
    public BENZ() {
        System.out.println("hello 我是奔驰");
    }
}

interface IAutomobileFactory {
    IAutomobile createAuto();
}

class AudiFactory implements IAutomobileFactory {
    @Override
    public IAutomobile createAuto() {
        return new Audi();
    }
}

class BMWFactory implements IAutomobileFactory {
    @Override
    public IAutomobile createAuto() {
        return new BMW();
    }
}

class BENZFactory implements IAutomobileFactory {
    @Override
    public IAutomobile createAuto() {
        return new BENZ();
    }
}