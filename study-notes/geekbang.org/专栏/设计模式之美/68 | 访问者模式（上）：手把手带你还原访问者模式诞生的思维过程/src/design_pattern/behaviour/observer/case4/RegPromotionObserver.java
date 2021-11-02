package design_pattern.behaviour.observer.case4;

public class RegPromotionObserver implements RegObserver {
    private PromotionService promotionService; // 依赖注入

    public RegPromotionObserver(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Override
    public void handleRegSuccess(long userId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                promotionService.issueNewUserExperienceCash(userId);
            }
        });
        thread.start();
    }
}

