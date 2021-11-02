package design_pattern.behaviour.observer.case6;

import design_pattern.behaviour.observer.case6.eventbus.Subscribe;

public class RegNotificationObserver {
    private NotificationService notificationService;

    public RegNotificationObserver(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Subscribe
    public void handleRegSuccess(Long userId) {
        notificationService.sendInboxMessage(userId, "...");
    }
}
