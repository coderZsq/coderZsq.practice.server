package design_pattern.struct.bridge.case2;

public class TrivialNotification extends Notification {
    // 与SevereNotification代码结构类似，所以省略...
    public TrivialNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {

    }

    @Override
    public void notify(NotificationEmergencyLevel severe, String s) {

    }
}