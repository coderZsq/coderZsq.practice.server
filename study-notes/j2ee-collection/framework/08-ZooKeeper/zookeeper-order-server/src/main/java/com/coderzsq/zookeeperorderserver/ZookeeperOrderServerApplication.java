package com.coderzsq.zookeeperorderserver;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ZookeeperOrderServerApplication {

    private static String ZK_SERVER_ADDR = "172.16.21.175:2181,172.16.21.175:2182,172.16.21.175:2183";
    private static int SESSION_TIMEOUT = 30000;
    private static String PATH = "/servers";

    public static List<String> addrList;

    // volatile: 保证在多线程之间的变量的可见性
    private volatile ZooKeeper zooKeeper;

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperOrderServerApplication.class, args);
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
                    // 1. 获取对应的地址列表
                    try {
                        getData();
                        // 2. 绑定永久的事件监听
                        zooKeeper.addWatch(PATH, new Watcher() {
                            @Override
                            public void process(WatchedEvent event) { // 开启另外的线程处理
                                try {
                                    getData();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, AddWatchMode.PERSISTENT);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            // 获取数据
            private void getData() throws KeeperException, InterruptedException {
                List<String> serverAddr = zooKeeper.getChildren(PATH, null);
                List<String> tempList = new ArrayList<>();
                for (String path : serverAddr) {
                    // 获取节点路径数据
                    byte[] data = zooKeeper.getData(PATH + "/" + path, null, new Stat());
                    String addrInfo = new String(data);
                    // 把数据添加到临时列表
                    tempList.add(addrInfo);
                }
                addrList = tempList;
                System.out.println("获取到秒杀服务的最新地址\n" + addrList);
            }
        });
        return zooKeeper;
    }
}
