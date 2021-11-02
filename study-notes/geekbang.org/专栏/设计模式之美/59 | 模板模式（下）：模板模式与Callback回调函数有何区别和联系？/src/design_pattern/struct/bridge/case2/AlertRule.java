package design_pattern.struct.bridge.case2;

public class AlertRule {
    public AlertRule getMatchedRule(String api) {
        return null;
    }

    public long getMaxTps() {
        return 0;
    }

    public long getMaxErrorCount() {
        return 0;
    }

    public long getMaxTimeoutTps() {
        return 0;
    }
}