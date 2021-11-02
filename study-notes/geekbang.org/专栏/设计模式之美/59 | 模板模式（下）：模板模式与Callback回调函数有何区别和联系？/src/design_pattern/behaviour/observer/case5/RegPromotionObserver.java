package design_pattern.behaviour.observer.case5;

public class RegPromotionObserver implements RegObserver {
    private PromotionService promotionService; // 依赖注入

    public RegPromotionObserver(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Override
    public void handleRegSuccess(long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }
}

