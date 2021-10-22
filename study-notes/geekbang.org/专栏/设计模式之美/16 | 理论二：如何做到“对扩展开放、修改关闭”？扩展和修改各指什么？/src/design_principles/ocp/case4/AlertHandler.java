package design_principles.ocp.case4;

import design_principles.ocp.case1.AlertRule;
import design_principles.ocp.case1.Notification;

public abstract class AlertHandler {
    protected AlertRule rule;
    protected Notification notification;
    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }
    public abstract void check(ApiStatInfo apiStatInfo);
}
