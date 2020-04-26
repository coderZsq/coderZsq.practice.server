package com.coderzsq.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZKApiController {

    @Autowired
    private ZooKeeper zooKeeper;

    @RequestMapping("createNode")
    public String createNode(String path, String data, String type) throws Exception {
        String result = zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.valueOf(type));
        return result;
    }

    // 获取数据
    @RequestMapping("getData")
    public String getData(String path) throws Exception {
        // 1. 先去查询版本信息
        Stat stat = zooKeeper.exists(path, false);
        // 同步获取数据
        byte[] data = zooKeeper.getData(path, false, stat);
        System.out.println("new String(data) = " + new String(data));
        return new String(data);
    }

    // 异步获取数据
    @RequestMapping("getDataAsync")
    public String getDataAsync(String path) throws Exception {
        // 1. 先去查询版本信息 如果没有, 返回的是一个null
        Stat stat = zooKeeper.exists(path, false);
        // 异步获取数据
        zooKeeper.getData(path, false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("异步处理回调数据");
                System.out.println("收到的数据: " + new String(data));
                System.out.println("ctx = " + ctx);
            }
        }, "测试数据");
        return "异步获取数据";
    }

    @RequestMapping("getChildren")
    public List<String> getChildren(String path) throws Exception {
        List<String> children = zooKeeper.getChildren(path, false);
        return children;
    }

    // 删除节点
    @RequestMapping("delete")
    public String delete(String path) throws Exception {
        Stat stat = zooKeeper.exists(path, false);
        if (stat != null) {
            zooKeeper.delete(path, stat.getVersion());
        }
        return "删除成功";
    }

    // 节点更新
    @RequestMapping("update")
    public String update(String path, String data) throws Exception {
        Stat stat = zooKeeper.exists(path, false);
        if (stat != null) {
            zooKeeper.setData(path, data.getBytes(), stat.getVersion());
        }
        return "更新成功";
    }

    // getData getChildren
    @RequestMapping("addWatch1")
    public String addWatch1(String path) throws Exception {
        Stat stat = zooKeeper.exists(path, false);
        // 定义一个监视器对象
        Watcher watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) { // 数据改变事件, 而且还是一次性
                System.out.println("事件类型:" + event.getType());
                System.out.println("数据发生改变, 请及时更新");
                try {
                    byte[] data = zooKeeper.getData(path, this, stat);
                    System.out.println("更新后的数据:" + new String(data));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        zooKeeper.getData(path, watcher, stat);
        return "success";
    }

    // 绑定永久事件
    @RequestMapping("addWatch2")
    public String addWatch2(String path) throws Exception {
        Stat stat = zooKeeper.exists(path, false);
        // 只是获取数据, 没有绑定事件
        byte[] data = zooKeeper.getData(path, null, stat);
        System.out.println("获取到数据:" + new String(data));
        // 绑定永久的事件 --> 1 数据变化事件 2 子节点改变事件
        zooKeeper.addWatch(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.NodeDataChanged) {
                    try {
                        // 重新获取数据
                        Stat stat = zooKeeper.exists(path, false);
                        // 只是获取数据, 没有绑定事件
                        byte[] data = zooKeeper.getData(path, null, stat);
                        System.out.println("更新的数据:" + new String(data));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                    // 重新获取子节点列表
                    System.out.println("子节点数据发生改变");
                }
            }
        }, AddWatchMode.PERSISTENT);
        return "success";
    }
}
