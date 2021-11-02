package design_pattern.behaviour.strategy.case6;

import design_pattern.behaviour.strategy.case5.Order;
import design_pattern.behaviour.strategy.case5.OrderType;

// 策略的使用
public class OrderService {
    public double discount(Order order) {
        OrderType type = order.getType();
        DiscountStrategy discountStrategy = DiscountStrategyFactory.getDiscountStrategy(type);
        return discountStrategy.calDiscount(order);
    }
}