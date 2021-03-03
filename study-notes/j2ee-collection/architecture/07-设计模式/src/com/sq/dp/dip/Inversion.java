package com.sq.dp.dip;

interface IMessager {
    String buildMsg();
}

/**
 * 依赖倒置原则
 * 在调用方由原来的依赖某一个类, 变成了依赖接口
 * 面向接口编程
 */
public class Inversion {
    public static void main(String[] args) {
        // 需求: 利用qq发送消息
        Person p = new Person();
        p.sendMsg(new QQMsg());
        p.sendMsg(new WechatMsg());
    }
}

// 服务提供方: 实现细节
class QQMsg implements IMessager {
    @Override
    public String buildMsg() {
        return "qq----msg...................";
    }
}

class WechatMsg implements IMessager {
    @Override
    public String buildMsg() {
        return "wechat----msg...................";
    }
}

// 消费方
class Person {
    void sendMsg(IMessager messager) {
        System.out.println("发送消息: " + messager.buildMsg());
    }
}
