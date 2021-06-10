package conc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

// 使用示例
public class AtomicCounter {
    private AtomicInteger sum = new AtomicInteger(0);
    public int incrAndGet() {
        return sum.incrementAndGet();
    }
    public int getSum() {
        return sum.get();
    }
}
