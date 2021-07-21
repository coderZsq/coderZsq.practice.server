# 1. 系统间通信方式

## 系统间通信方式

    基于文件
    基于共享内存
    基于 IPC
    基于 Socket
    基于数据库
    基于 RPC

    还有没有其他方式?

## 系统间通信方式

    各个模式的缺点:

    - 文件: 明显不方便，不及时
    - Socket:使用麻烦，多数情况下不如 RPC
    - 数据库:不实时，但是经常有人拿数据库来模拟消息队列
    - RPC:调用关系复杂，同步处理，压力大的时候无法缓冲

## 系统间通信方式

    我们期望有一种通信方式:

    - 可以实现异步的消息通信
    - 可以简化参与各方的复杂依赖关系
    - 可以在请求量很大的时候，缓冲一下 > 类比线程池里的 Queue
    - 某些情况下能保障消息的可靠性，甚至顺序

## 系统间通信方式

    这就是 MQ，Message Queue/Messaging System/Message Middlewire
    ~ 可以类比快递服务

# 2. 从队列到消息服务

## 从队列到消息服务

    - 内存里的Queue

---

    - Message Queue/Messaging System

     什么是消息?

## MQ 的四大作用

    对比其他通信模式，MQ 的优势在于:

    - 异步通信:异步通信，减少线程等待，特别是处理批量等大事务、耗时操作。
    - 系统解耦:系统不直接调用，降低依赖，特别是不在线也能保持通信最终完成。
    - 削峰平谷:压力大的时候，缓冲部分请求消息，类似于背压处理。
    - 可靠通信:提供多种消息模式、服务质量、顺序保障等。

# 3. 消息模式与消息协议\*

## 消息处理模式

    常见的有两种消息模式:

    - 点对点:PTP，Point-To-Point
    对应于 Queue

    - 发布订阅:PubSub，Publish-Subscribe，
    对应于Topic

## 消息处理的保障

    三种 QoS(注意:这是消息语义的，不是业务语义的):

    - At most once，至多一次，消息可能丢失但是不会重复发送;
    - At least once，至少一次，消息不会丢失，但是可能会重复;
    - Exactly once，精确一次，每条消息肯定会被传输一次且仅一次。

    消息处理的事务性:

    - 通过确认机制实现事务性;
    - 可以被事务管理器管理，甚至可以支持 XA。

## 消息有序性

    同一个 Topic 或 Queue 的消息，保障按顺序投递。
    注意:如果做了消息分区，或者批量预取之类的操作，可能就没有顺序了。

## 集成领域圣经

    《企业集成模式》
    可以认为是 SOA/ESB/MQ等 理论基础

## 消息协议

    STOMP
    JMS*
    AMQP*
    MQTT\*
    XMPP
    Open Messaging

## JMS(Java Message Service)

    关注于应用层的 API 协议( ~ 类似 JDBC)
    Message 结构与 Queue 概念
    • Body\Header\Property, messages types
    • Queue\Topic\TemporaryQueue\TemporaryTopic
    • Connection\Session\Producer\Consumer\DurableSubscription
    Messaging 行为
    • PTP&Pub-Sub
    • 持久化
    • 事务机制
    • 确认机制
    • 临时队列

## 开源消息中间件/消息队列

    三代:
    1、ActiveMQ/RabbitMQ
    2、Kafka/RocketMQ
    3、Apache Pulsar

# 4. ActiveMQ 消息中间件

## ActiveMQ 介绍

    - 高可靠的、事务性的消息队列
    - 当前应用最广泛的开源消息中间件
    - 项目开始与2005年CodeHaus、2006年成为Apache项目

    后来与HornetQ合并，新的消息队列叫:Artemis，目前是ActiveMQ的子项目

    功能最全的开源消息队列
    https://activemq.apache.org/

## 主要功能

    1. 多种语言和协议编写客户端。
      语言: Java, C, C++, C#, Ruby, Perl, Python, PHP 等
      应用协议: OpenWire,Stomp REST,WS Notification,XMPP,AMQP,MQTT
    2. 完全支持 JMS1.1 和 J2EE 1.4 规范 (持久化,XA 消息,事务)
    3. 与 Spring 很好地集成，也支持常见 J2EE 服务器
    4. 支持多种传送协议:in-VM,TCP,SSL,NIO,UDP,JGroups,JXTA
    5. 支持通过 JDBC 和 journal 提供高速的消息持久化
    6. 实现了高性能的集群模式

# 5. ActiveMQ 使用示例\*

## 使用场景

    ActiveMQ 的使用场景:
    1、所有需要使用消息队列的地方;
    2、订单处理、消息通知、服务降级等等;
    3、特别地，纯 java 实现，支持嵌入到应用系统。

## 使用演示

    演示一些功能

    补充材料:

    MQ三个相关以前的PPT请在群里找或者问班主任要。

    JMS介绍:我对JMS的理解和认识: https://kimmking.blog.csdn.net/article/details/6577021
    ActiveMQ官网: https://activemq.apache.org
    ActiveMQ集群-网络集群模式详解: https://kimmking.blog.csdn.net/article/details/8440150
    ActiveMQ的集群与高可用: https://kimmking.blog.csdn.net/article/details/13768367

# 6. 总结回顾与作业实践

## 第 24 课作业实践

    1、(必做)搭建ActiveMQ服务，基于JMS，写代码分别实现对于queue和topic的消息 生产和消费，代码提交到github。
    2、(选做)基于数据库的订单表，模拟消息队列处理订单:
    1)一个程序往表里写新订单，标记状态为未处理(status=0);
    2)另一个程序每隔100ms定时从表里读取所有status=0的订单，打印一下订单数据， 然后改成完成status=1;
    3)(挑战☆)考虑失败重试策略，考虑多个消费程序如何协作。
    3、(选做)将上述订单处理场景，改成使用ActiveMQ发送消息处理模式。
    4、(选做)使用java代码，创建一个ActiveMQ Broker Server，并测试它。

---

    5、(挑战 ☆☆)搭建 ActiveMQ 的 network 集群和 master-slave 主从结构。
    6、(挑战 ☆☆☆)基于 ActiveMQ 的 MQTT 实现简单的聊天功能或者 Android 消息推送。
    7、(挑战 ☆)创建一个 RabbitMQ，用 Java 代码实现简单的 AMQP 协议操作。
    8、(挑战 ☆☆)搭建 RabbitMQ 集群，重新实现前面的订单处理。
    9、(挑战 ☆☆☆)使用 Apache Camel 打通上述 ActiveMQ 集群和 RabbitMQ 集群，实 现所有写入到 ActiveMQ 上的一个队列 q24 的消息，自动转发到 RabbitMQ。
    10、(挑战 ☆☆☆)压测 ActiveMQ 和 RabbitMQ 的性能。
