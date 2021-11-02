package design_pattern.behaviour.strategy.case6;

import design_pattern.behaviour.strategy.case5.Order;

public class PromotionDiscountStrategy implements DiscountStrategy {
    @Override
    public double calDiscount(Order order) {
        return 0;
    }
}
