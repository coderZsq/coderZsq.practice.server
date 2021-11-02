package design_pattern.struct.bridge.case2;

public abstract class Notification {
    protected MsgSender msgSender;

    public Notification(MsgSender msgSender) {
        this.msgSender = msgSender;
    }

    public abstract void notify(String message);

    public abstract void notify(NotificationEmergencyLevel severe, String s);
}