package design_pattern.behaviour.observer.case2;

public class Demo {
    public static void main(String[] args) {
        UserService userService = new UserService();
        PromotionService promotionService = new PromotionService();
        UserController userController = new UserController(userService, promotionService);
        userController.register("137017777868", "123456");
    }
}
