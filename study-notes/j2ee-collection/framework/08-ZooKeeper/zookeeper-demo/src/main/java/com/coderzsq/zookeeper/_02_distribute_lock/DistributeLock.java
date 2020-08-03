package com.coderzsq.zookeeper._02_distribute_lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistributeLock {

    @Autowired
    private ZooKeeper zooKeeper;

    private String path = "/locks";
    private String node = "/orderIdLock";

    //尝试获取id, 如果获取到了, 返回true, 否则返回false
    public boolean tryLock() {
        try {
            //因为不是顺序节点, 对于同一个路径, 只能创建一次
            String path = zooKeeper.create(this.path + this.node,
                    null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //释放锁资源
    public void unlock() {
        try {
            Stat stat = zooKeeper.exists(this.path + this.node, false);
            zooKeeper.delete(this.path + this.node, stat.getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 等待锁资源
    public synchronized void waitLock() {
        try {
            zooKeeper.getChildren(this.path, new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                    tryLock();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
