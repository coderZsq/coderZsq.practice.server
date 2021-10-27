package design_principles.dip.di.case3;

// 站内信发送类
public class InboxSender implements MessageSender {
    @Override
    public void send(String cellphone, String message) {
        //....
    }
}