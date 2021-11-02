package design_pattern.behaviour.observer.case6;

import design_pattern.behaviour.observer.case6.eventbus.Subscribe;

public class RegPromotionObserver {
    private PromotionService promotionService; // 依赖注入

    public RegPromotionObserver(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Subscribe
    public void handleRegSuccess(Long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }
}