package design_pattern.struct.bridge.case2;

public class UrgencyNotification extends Notification {
    // 与SevereNotification代码结构类似，所以省略...
    public UrgencyNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {

    }
}