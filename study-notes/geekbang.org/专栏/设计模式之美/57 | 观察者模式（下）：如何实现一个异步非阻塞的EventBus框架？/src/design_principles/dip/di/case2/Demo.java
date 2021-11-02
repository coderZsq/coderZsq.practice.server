package design_principles.dip.di.case2;

import design_principles.dip.di.case1.MessageSender;

public class Demo {
    public static void main(String[] args) {
        //使用Notification
        MessageSender messageSender = new MessageSender();
        Notification notification = new Notification(messageSender);
    }
}
