package design_pattern.create.singleton.case4;

public class OrderController {
    public void create(OrderVo order) {
        // ...省略业务逻辑代码...
        Logger.getInstance().log("Created an order: " + order.toString());
    }
}