package conc.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCounter {
    private int sum = 0;
    // 可重入-读写锁-公平锁
    private ReadWriteLock lock = new
            ReentrantReadWriteLock(true);

    public int incrAndGet() {
        try {
            lock.writeLock().lock(); // 写锁; 独占锁; 被读锁排斥
            return ++sum;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getSum() {
        try {
            lock.readLock().lock(); // 读锁; //共享锁; 保证可见性
            return ++sum;
        } finally {
            lock.readLock().unlock();
        }
    }
}
