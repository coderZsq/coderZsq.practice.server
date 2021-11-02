package design_pattern.behaviour.strategy.case6;

import design_pattern.behaviour.strategy.case5.Order;

// 策略的定义
public interface DiscountStrategy {
    double calDiscount(Order order);
}
// 省略NormalDiscountStrategy、GrouponDiscountStrategy、PromotionDiscountStrategy类代码...