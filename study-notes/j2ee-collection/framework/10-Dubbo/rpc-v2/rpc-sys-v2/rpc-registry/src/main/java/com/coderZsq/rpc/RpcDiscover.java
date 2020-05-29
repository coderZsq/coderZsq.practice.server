package com.coderZsq.rpc;

import lombok.Getter;
import lombok.Setter;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Setter
@Getter
//地址发现,用于实时的获取最新的RPC服务信息
public class RpcDiscover {
    //服务端地址 zkServer的地址
    private String registryAddress;
    //获取到的所有提供服务的服务器列表 volatile 保证多线程的可见性
    private volatile List<String> dataList = new ArrayList<>();
    private ZooKeeper zooKeeper = null;

    //初始化zkClient客户端
    public RpcDiscover(String registryAddress) throws Exception {
        this.registryAddress = registryAddress;
        zooKeeper = new ZooKeeper(registryAddress, Constant.SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                    //监听zkServer的服务器列表变化
                    watchNode();
                }
            }
        });
        //获取节点相关数据
        watchNode();
    }

    //测试程序
    public static void main(String[] args) throws Exception {
        //打印获取到的连接地址信息
        System.out.println(new RpcDiscover("172.16.21.175:2181").discover());
        System.in.read();
    }

    // 从dataList列表随机获取一个可用的服务端的地址信息给rpc-web
    public String discover() {
        int size = dataList.size();
        if (size > 0) {
            int index = new Random().nextInt(size);
            return dataList.get(index);
        }
        throw new RuntimeException("没有找到对应的服务器");
    }

    //监听服务端的列表信息
    private void watchNode() {
        try {
            //获取子节点信息
            List<String> nodeList = zooKeeper.getChildren(Constant.REGISTRY_PATH, true);
            List<String> dataList = new ArrayList<>();
            for (String node : nodeList) {
                byte[] bytes = zooKeeper.getData(Constant.REGISTRY_PATH + "/" + node, false, null);
                dataList.add(new String(bytes));
            }
            this.dataList = dataList;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
