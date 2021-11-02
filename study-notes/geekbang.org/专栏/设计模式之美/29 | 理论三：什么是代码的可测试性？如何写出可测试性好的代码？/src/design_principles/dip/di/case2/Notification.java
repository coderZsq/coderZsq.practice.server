package design_principles.dip.di.case2;

import design_principles.dip.di.case1.MessageSender;

// 依赖注入的实现方式
public class Notification {
    private MessageSender messageSender;

    // 通过构造函数将messageSender传递进来
    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String cellphone, String message) {
        //...省略校验逻辑等...
        this.messageSender.send(cellphone, message);
    }
}