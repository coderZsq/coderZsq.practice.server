package reconfiguration.case2;

public class RedisDistributedLock {
    public static RedisDistributedLock getSingletonIntance() {
        return new RedisDistributedLock();
    }

    public boolean lockTransction(String id) {
        return false;
    }

    public void unlockTransction(String id) {
    }
}
