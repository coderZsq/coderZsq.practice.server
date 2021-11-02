package design_pattern.create.singleton.case15;

import java.util.concurrent.atomic.AtomicLong;

// 静态方法实现方式
public class IdGenerator {
    private static AtomicLong id = new AtomicLong(0);

    public static long getId() {
        return id.incrementAndGet();
    }

    public static void main(String[] args) {
        // 使用举例
        long id = IdGenerator.getId();
    }
}