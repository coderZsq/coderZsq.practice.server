package design_pattern.behaviour.strategy.case7;

import design_pattern.behaviour.strategy.case5.OrderType;
import design_pattern.behaviour.strategy.case6.DiscountStrategy;
import design_pattern.behaviour.strategy.case6.GrouponDiscountStrategy;
import design_pattern.behaviour.strategy.case6.NormalDiscountStrategy;
import design_pattern.behaviour.strategy.case6.PromotionDiscountStrategy;

public class DiscountStrategyFactory {
    public static DiscountStrategy getDiscountStrategy(OrderType type) {
        if (type == null) {
            throw new IllegalArgumentException("Type should not be null.");
        }
        if (type.equals(OrderType.NORMAL)) {
            return new NormalDiscountStrategy();
        } else if (type.equals(OrderType.GROUPON)) {
            return new GrouponDiscountStrategy();
        } else if (type.equals(OrderType.PROMOTION)) {
            return new PromotionDiscountStrategy();
        }
        return null;
    }
}
