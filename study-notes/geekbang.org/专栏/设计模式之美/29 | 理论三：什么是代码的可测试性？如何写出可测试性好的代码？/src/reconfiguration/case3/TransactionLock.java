package reconfiguration.case3;

public class TransactionLock {
    private String id;

    public boolean lock(String id) {
        return RedisDistributedLock.getSingletonIntance().lockTransction(id);
    }

    public void unlock() {
        RedisDistributedLock.getSingletonIntance().unlockTransction(id);
    }
}
