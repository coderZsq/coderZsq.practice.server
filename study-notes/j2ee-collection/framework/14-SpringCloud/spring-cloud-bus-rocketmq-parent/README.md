# 概述

Spring cloud bus 整合 java的事件处理机制和消息中间件消息的发送和接受，通过消息中间件，将java事件转化为消息，再将消息转化为java事件，从而达到广播事件的目的，主要由发送端、接收端和事件组成。针对不同的业务需求，可以设置不同的事件，发送端发送事件，接收端接受相应的事件，并进行相应的处理。

Spring cloud bus 通过Spring cloud stream 来实现与具体消息中间件解耦的目的，目前Sping cloud stream官方支持rabbitmq和kafaka两个binder，本次实现了rocketmq的binder。

# 使用方法

```
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-bus-rocketmq</artifactId>
        <version>2.0.2.RELEASE</version>
    </dependency>
```
# 演示
可参考https://github.com/zhipingzhang/spring-cloud-stream-bus-rocketmq/tree/master/spring-cloud-rocketmq-sample 。

该sample模拟了网关系统根据商户ID调用用户中心接口获取商户秘钥，并在商户秘钥发生变化后，广播通知到网关系统的场景。整个场景应由sample对应的发送端、接收端和事件三部分构成，分别如下：

## 事件
user-center-event项目代表事件SDK，需引入spring-cloud-starter-bus-rocketmq：
```
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-bus-rocketmq</artifactId>
        <version>2.0.2.RELEASE</version>
    </dependency>
```

项目中定义了事件类，需继承自RemoteApplicationEvent，可添加需要的字段用于传递数据：

```

public class MerchantChangeRemoteApplicationEvent extends RemoteApplicationEvent {
 
    @SuppressWarnings("unused")
    private MerchantChangeRemoteApplicationEvent() {
        // for serializers
    }
 
    public MerchantChangeRemoteApplicationEvent(Object source, String originService,
                                                String destinationService) {
        super(source, originService, destinationService);
    }
}
```

## 发送端
user-center项目代表发送端，需依赖上面的事件SDK，启动类加上@RemoteApplicationEventScan注解，配置文件加入如下配置：


```
spring:
  cloud:
    #bus:
      # 下面是默认对应的topic，可自定义使用的topic ,rocketmq上需要有对应的topic,但发布方需和监听方一致
      #destination: springCloudBus
    stream:
      rocket:
        binder:
          #rocketmq name server地址
          name-server: 10.241.0.43:9876
        bindings:
          springCloudBusInput:
            consumer:
              #广播模式
              broadcasting: true
      bindings:
        springCloudBusInput:
          #consumer 组
          group: test-group
```

发送端发布事件代码，可参考user-center项目的io.github.tesla.springcloud.controller.MerchantController ：

```

@Autowired
private ApplicationContext context;
@Autowired
private BusProperties bu;
 
private String appId;
 
@PostConstruct
public void init() {
    // appId用于区分不同的消费者，一定要在应用启动阶段就保存下来，因为bu.getId()在应用运行期间可能会变
    appId = bu.getId();
}
 
 
@PostMapping("modifyKey/{merchantId}/{merchantKey}")
@ResponseBody
public Map modifyKey(@PathVariable("merchantId") String merchantId,
                     @PathVariable("merchantKey") String merchantKey) {
    //发布事件,第三个参数是是否指定应用，不指定则会发全部，填的是spring.application.name
    context.publishEvent(new MerchantChangeRemoteApplicationEvent(this, appId, null));
    return map;
}
```

## 消费端
osg-gateway项目代表消费端，配置和注解参考sample，与user-center一致，监听事件如下：

```

@EventListener(MerchantChangeRemoteApplicationEvent.class)
public void refreshMerchantKey() {
    log.info("receive merchantChangeEvent ,begin refresh merchantKey cache");
    merchantKeyMap.clear();
}
```

## 演示流程
1. 启动user-center，默认端口为8902。
2. 启动osg-gateway，默认端口为8904。
3. 访问osg-gateway的接口，可得到商户秘钥：http://localhost:8904/osg-gateway/queryKey/merchant_2 。
4. 访问user-center的接口，修改秘钥：http://localhost:8902/user-center/modifyKey/merchant_2/22222。
5. 再次访问osg-gateway的接口，就可得到最新的秘钥：http://localhost:8904/osg-gateway/queryKey/merchant_2 。

# 注意事项
## 日志
默认rocketmqclient是输出INFO级别的日志，如果想改日志级别或输出，请在日志配置文件中指定：

```
<logger name="RocketmqClient" level="ERROR">
   <appender-ref ref="logfile" />
   <appender-ref ref="stdout" />
</logger>
```

## 同步/异步
默认是使用的同步方法发送rocketmq消息，如果需要异步发送，请在发送方加入如下配置：

```
spring:
  cloud:
    stream:
      rocket:
        bindings:
          springCloudBusOutput:
            producer:
              sync: false
```













