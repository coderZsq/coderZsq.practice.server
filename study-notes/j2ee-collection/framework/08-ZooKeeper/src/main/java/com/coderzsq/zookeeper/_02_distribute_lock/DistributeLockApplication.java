package com.coderzsq.zookeeper._02_distribute_lock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class DistributeLockApplication {

    private static String ZK_SERVER_ADDR = "172.16.21.175:2181,172.16.21.175:2182,172.16.21.175:2183";
    private static int SESSION_TIMEOUT = 30000;

    public static void main(String[] args) {
        SpringApplication.run(DistributeLockApplication.class, args);
    }

    //创建一个zookeeper的连接
    @Bean
    public ZooKeeper zooKeeper() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);//并发等待
        // 第一个参数: 连接地址和端口 第二个参数: 会话超时时间, 第三个参数: 事件监听程序
        ZooKeeper zooKeeper = new ZooKeeper(ZK_SERVER_ADDR, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("event = " + event);
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();//等待连接成功在返回对象
                }
            }
        });
//        zooKeeper.getState(); connecting --> connected

        countDownLatch.await();
        return zooKeeper;
    }


}
