package design_principles.ocp.case5;

import com.sun.org.apache.xerces.internal.util.MessageFormatter;
import design_principles.ocp.case1.Notification;

public class Demo {
    private MessageQueue msgQueue; // 基于接口而非实现编程
    public Demo(MessageQueue msgQueue) { // 依赖注入
        this.msgQueue = msgQueue;
    }

    // msgFormatter：多态、依赖注入
    public void sendNotification(Notification notification, MessageFormatter msgFormatter) {
        //...
    }
}
