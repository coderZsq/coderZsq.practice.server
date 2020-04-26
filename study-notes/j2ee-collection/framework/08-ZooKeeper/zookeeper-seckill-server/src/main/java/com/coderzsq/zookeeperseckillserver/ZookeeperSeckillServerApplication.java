package com.coderzsq.zookeeperseckillserver;

import org.apache.zookeeper.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ZookeeperSeckillServerApplication {

    private static String ZK_SERVER_ADDR = "172.16.21.175:2181,172.16.21.175:2182,172.16.21.175:2183";
    private static int SESSION_TIMEOUT = 30000;
    private static String PATH = "/servers";
    private static String SUB_PATH = "/seckillServer";
    @Value("${server.host}")
    private String host;
    @Value("${server.port}")
    private String port;

    private ZooKeeper zooKeeper;

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperSeckillServerApplication.class, args);
    }

    @Bean
    public ZooKeeper zooKeeper() throws Exception {
        // 第一个参数: 连接地址和端口 第二个参数: 会话超时时间, 第三个参数: 事件监听程序
        zooKeeper = new ZooKeeper(ZK_SERVER_ADDR, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("event = " + event);
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("zookeeper客户端连接成功");
                    // 注册对应的信息
                    try {
                        zooKeeper.create(PATH + SUB_PATH, (host + ":" + port).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return zooKeeper;
    }
}
