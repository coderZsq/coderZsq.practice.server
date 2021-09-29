// Strategy for Happy hour (50% discount)
class HappyHourStrategy implements BillingStrategy {
    @Override
    public double GetActPrice(double rawPrice) {
        return rawPrice * 0.5;
    }
}