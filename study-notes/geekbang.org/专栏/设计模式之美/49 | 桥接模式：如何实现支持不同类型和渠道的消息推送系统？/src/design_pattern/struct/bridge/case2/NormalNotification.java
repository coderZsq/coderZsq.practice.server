package design_pattern.struct.bridge.case2;

public class NormalNotification extends Notification {
    // 与SevereNotification代码结构类似，所以省略...
    public NormalNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {

    }
}