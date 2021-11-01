package design_pattern.behaviour.observer.case6;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        UserService userService = new UserService();
        UserController userController = new UserController(userService);
        List<Object> regObservers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                PromotionService promotionService = new PromotionService();
                RegPromotionObserver regPromotionObserver = new RegPromotionObserver(promotionService);
                regObservers.add(regPromotionObserver);
            } else {
                NotificationService notificationService = new NotificationService();
                RegNotificationObserver notificationObserver = new RegNotificationObserver(notificationService);
                regObservers.add(notificationObserver);
            }
        }
        userController.setRegObservers(regObservers);
        userController.register("13701777868", "123456");
    }
}
