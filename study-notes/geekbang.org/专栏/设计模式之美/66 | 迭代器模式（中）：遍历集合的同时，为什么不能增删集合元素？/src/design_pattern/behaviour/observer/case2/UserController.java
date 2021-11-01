package design_pattern.behaviour.observer.case2;

public class UserController {
    private UserService userService; // 依赖注入
    private PromotionService promotionService; // 依赖注入

    public UserController(UserService userService, PromotionService promotionService) {
        this.userService = userService;
        this.promotionService = promotionService;
    }

    public Long register(String telephone, String password) {
        //省略输入参数的校验代码
        //省略userService.register()异常的try-catch代码
        long userId = userService.register(telephone, password);
        promotionService.issueNewUserExperienceCash(userId);
        return userId;
    }
}