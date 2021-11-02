package design_pattern.behaviour.observer.case6.eventbus;

import java.lang.reflect.Method;

public class Preconditions {
    public static Object checkNotNull(Object target) {
        return target;
    }

    public static void checkArgument(boolean b, String s, Method method, int length) {
    }
}
