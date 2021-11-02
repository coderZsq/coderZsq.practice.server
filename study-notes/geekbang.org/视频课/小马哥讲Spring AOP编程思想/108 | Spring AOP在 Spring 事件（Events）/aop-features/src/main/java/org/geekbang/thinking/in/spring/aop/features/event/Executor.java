package org.geekbang.thinking.in.spring.aop.features.event;

public class Executor { // ClassFilter

    public void execute() { // MethodMatcher: Join Point 方法 (需要 Pointcut 来匹配)
        System.out.println("Executing...");
    }
}
