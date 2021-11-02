package design_pattern.behaviour.observer.case3;

public class RegNotificationObserver implements RegObserver {
    private NotificationService notificationService;

    public RegNotificationObserver(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void handleRegSuccess(long userId) {
        notificationService.sendInboxMessage(userId, "Welcome...");
    }
}