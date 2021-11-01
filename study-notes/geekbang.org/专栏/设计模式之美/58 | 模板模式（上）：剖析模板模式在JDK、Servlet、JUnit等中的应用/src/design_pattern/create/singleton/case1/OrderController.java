package design_pattern.create.singleton.case1;

public class OrderController {
    private Logger logger = new Logger();

    public void create(OrderVo order) {
        // ...省略业务逻辑代码...
        logger.log("Created an order: " + order.toString());
    }
}