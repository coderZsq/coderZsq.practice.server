// Normal billing strategy (unchanged price)
class NormalStrategy implements BillingStrategy {
    @Override
    public double GetActPrice(double rawPrice) {
        return rawPrice;
    }
}