import java.util.ArrayList;
import java.util.List;

class Order {
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    private BillingStrategy strategy = new NormalStrategy();

    public void Add(String name, double price, int quantity, BillingStrategy strategy) {
        orderItems.add(new OrderItem(name, price, quantity, strategy));
    }

    // Payment of bill
    public void PayBill() {
        double sum = 0;
        for (OrderItem item : orderItems) {

            double actPrice = item.Strategy.GetActPrice(item.Price * item.Quantity);
            sum += actPrice;

            System.out.printf("%s -- %f(%d) - %f",
                    item.Name, item.Price, item.Quantity, actPrice);
        }
        System.out.println("Total due: " + sum);
    }
}
