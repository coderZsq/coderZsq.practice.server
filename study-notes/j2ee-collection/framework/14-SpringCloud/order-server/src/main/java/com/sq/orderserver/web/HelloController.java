package com.sq.orderserver.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
// 发送post请求
// http://localhost:8090/actuator/bus-refresh // (广播)
// http://localhost:8090/actuator/env // 环境信息
// http://localhost:8090/actuator/beans // IoC容器的bean对象集合
// http://localhost:8090/actuator/refresh // 重新加载配置(单体)
// http://localhost:9100/monitor?path=* Content-Type:application/x-www-form-urlencoded // (需要表单提交)
// 删除原来的缓存实例对象
// 在访问的时候重新根据配置文件创建一个实例对象
@RefreshScope // 标记的类是一个延迟加载对象, 在访问的时候创建, 并且刷新env环境的时候会清除该对象
public class HelloController {

    @Value("${limitNum}")
    private String limitNum;

    @RequestMapping("hello")
    public String hello() {
        System.out.println("用户的限购数量: " + limitNum);
        return limitNum;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("test1")
    // 现在这个情况 还没有熔断, 如果要开启熔断, 不调用业务方法, 那么需要开启熔断开关, 但是开启熔断开关有条件
    // 满足三个条件
    /**
     * 1 在指定的事件的事件窗口 10s
     * 2 需要达到一定的请求量 100个请求
     * 3 需要请求的错误率达到50%
     */
    @HystrixCommand(fallbackMethod = "test1Fallback",
            // HystrixCommandProperties
            commandProperties = {
                    // 前三个参数是熔断开关
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "20"),

                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "15"), // 信号量隔离的计数器
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100"), // 信号量隔离的计数器
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE") // 默认是使用线程池, 可以通过该参数配置为 信号量
            },
            // HystrixThreadPoolProperties
            threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "20")}
    )
    public String test1() {
        System.out.println("调用业务方法");
        // 模拟延时
        // try {
        //     TimeUnit.SECONDS.sleep(2);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // 模拟异常
        // int i = 1 / 0;

        try {
            Thread.sleep(1800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public String test1Fallback() {
        System.out.println("调用降级服务");
        return "default";
    }

    // public String test1Fallback() {
    //     // 通过短信或者email的方式通知运维人员
    //     // 每隔5分钟给运维人员发送短信 延迟消息: 消息队列
    //     // redis 过期时间设置 5分钟
    //     /*
    //        0 先去redis中判断发送过短信 如果没有发送短信, 就发送消息
    //        1 发送短信给运维人员
    //        2 在redis中记录对应的信息, key order-save "通知运维处理关注", 300s
    //      */
    //    ValueOperations ops = redisTemplate.opsForValue();
    //     Object obj = ops.get("order-save");
    //     if (obj != null) {
    //         System.out.println("在5分钟内已经通知运维了");
    //     } else {
    //         ops.set("order-save", "info", 5, TimeUnit.MINUTES);
    //         //使用消息中间件
    //         System.out.println("订单处理故障, 请及时处理系统");
    //     }
    //     return "default";
    // }
}
