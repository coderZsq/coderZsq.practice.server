package com.coderZsq.rpc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import static com.coderZsq.rpc.Constant.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RpcRegistry {
    // zkServer的地址信息
    private String registryAddress;
    // zk客户端程序
    private ZooKeeper zooKeeper;

    public RpcRegistry(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public void createNode(String data) throws Exception {
        // 创建一个客户端程序, 对于注册可以不用监听事件
        zooKeeper = new ZooKeeper(registryAddress, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
        if (zooKeeper != null) {
            try {
                // 判断注册的目录是否存在
                Stat stat = zooKeeper.exists(REGISTRY_PATH, false);
                if (stat == null) {
                    // 如果不存在, 创建一个持久的节点目录
                    zooKeeper.create(REGISTRY_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
                // 创建一个临时的序列节点, 并且保存数据信息
                zooKeeper.create(DATA_PATH, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 测试程序
    public static void main(String[] args) throws Exception {
        RpcRegistry rpcRegistry = new RpcRegistry();
        rpcRegistry.setRegistryAddress("172.16.21.175:2181");
        rpcRegistry.createNode("testdata");
        // 让程序等待输入, 程序一直处于运行状态
        System.in.read();
    }
}
