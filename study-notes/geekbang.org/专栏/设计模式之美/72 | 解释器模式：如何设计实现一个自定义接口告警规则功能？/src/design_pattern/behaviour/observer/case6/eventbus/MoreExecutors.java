package design_pattern.behaviour.observer.case6.eventbus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MoreExecutors {
    public static Executor directExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
