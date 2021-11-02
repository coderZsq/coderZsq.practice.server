package design_pattern.behaviour.strategy.case4;

public class EvictionStrategyFactory {
    public static EvictionStrategy getEvictionStrategy(String type) {
        return new EvictionStrategy();
    }
}
