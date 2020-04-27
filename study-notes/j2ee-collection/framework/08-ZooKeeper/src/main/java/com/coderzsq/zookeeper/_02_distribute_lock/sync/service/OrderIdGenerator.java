package com.coderzsq.zookeeper._02_distribute_lock.sync.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * id 生成方法
 */
public class OrderIdGenerator {

    private int count = 0; //共享资源

    //生成id
    //synchronized --> jvm 如果是跨jvm, 分布式程序, 多进程之间进行数据的共享--> 分布式锁
    public synchronized String getId() {
        String id = null;
        try {
            TimeUnit.MILLISECONDS.sleep(10);//模拟网络延迟
            //SimpleDateFormat 用来把日期和字符串进行相互转换
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            count = count + 1;//并发的原子性, 有序性, 可见性
            //format可视化日期, parse: 解析字符
            id = sdf.format(new Date()) + "-" + count;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }
}
