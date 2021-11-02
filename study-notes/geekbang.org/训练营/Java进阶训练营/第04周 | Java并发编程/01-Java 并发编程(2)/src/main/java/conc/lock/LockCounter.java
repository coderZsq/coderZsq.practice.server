package conc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class LockCounter {
    private int sum = 0;
    // 可重入锁+公平锁
    private Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        // 测试代码
        int loopNum = 100_0000;
        LockCounter counter = new LockCounter();
        IntStream.range(0, loopNum).parallel().forEach(i -> System.out.println(counter.addAndGet()));
    }

    public int addAndGet() {
        try {
            lock.lock();
            return ++sum;
        } finally {
            lock.unlock();
        }
    }

    public int getSum() {
        return sum;
    }
}

