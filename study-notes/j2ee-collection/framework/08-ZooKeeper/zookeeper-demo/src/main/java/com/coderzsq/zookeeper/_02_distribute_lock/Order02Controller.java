package com.coderzsq.zookeeper._02_distribute_lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
public class Order02Controller {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ZooKeeper zooKeeper;

    private String path = "/locks02";
    private String node = "/orderIdLock";

    @RequestMapping("createOrder02")
    public String createOrder() throws Exception {
        //创建一个临时顺序节点   /locks02/orderIdLock0000000001
        String currentPath = zooKeeper.create(this.path + this.node,
                null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        // orderIdLock0000000001 字符串处理
        currentPath = currentPath.substring(currentPath.lastIndexOf("/") + 1);
        //获取id
        if (tryLock(currentPath)) {
            //调用业务方法
            String id = restTemplate.getForObject("http://localhost:8080/getId", String.class);
            System.out.println("获取到的id:" + id);
            //释放锁
            unlock(currentPath);
        } else {
            waitLock(currentPath);
        }
        return "success";
    }

    //尝试获取id, 如果获取到了, 返回true, 否则返回false
    //竞争锁资源
    public boolean tryLock(String currentPath) {
        try {
            //1 获取所有的子节点列表
            List<String> children = zooKeeper.getChildren(path, false);
            Collections.sort(children);
            //2 判断当前的currentPath是否是最小的节点
            if (StringUtils.pathEquals(currentPath, children.get(0))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //释放锁资源
    public void unlock(String currentPath) {
        try {
            Stat stat = zooKeeper.exists(path + "/" + currentPath, false);
            if (stat != null) {
                zooKeeper.delete(path + "/" + currentPath, stat.getVersion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //阻塞状态
    public void waitLock(String currentPath) {
        try {
            //1 获取所有的子节点列表
            List<String> children = zooKeeper.getChildren(path, false);
            Collections.sort(children);
            int index = children.indexOf(currentPath);//当前节点的位置
            if (index > 0) {
                String preNode = children.get(index - 1);
                //对前一个节点绑定节点删除事件
                zooKeeper.getData(path + "/" + preNode, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {//前一个节点删除
                        if (event.getType() == Event.EventType.NodeDeleted) {
                            //调用业务方法
                            String id = restTemplate.getForObject("http://localhost:8080/getId", String.class);
                            System.out.println("获取到的id:" + id);
                            //释放锁
                            unlock(currentPath);
                        }
                    }
                }, new Stat());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
