package design_pattern.behaviour.observer.case6;

public class NotificationService {
    public void sendInboxMessage(long userId, String s) {
        System.out.println("userId = " + userId +  ", s = " + s);
    }
}
