# 1.再谈谈什么是高性能

      什么是高性能?
      大家思考一下，什么是高性能?

      高并发用户(Concurrent Users)

      高吞吐量(Throughput)

      低延迟(Latency)

---

      如果实现了高性能，有什么副作用呢?

      系统复杂度 x10 以上

      建设与维护成本++++

      故障或 BUG 导致的破坏性 x10 以上

---

      应对策略

      稳定性建设(混沌工程):

      1. 容量预估
      2. 爆炸半径
      3. 工程方面积累与改进

# 2. Netty 如何实现高性能

## Netty 概览

      网络应用开发框架

      1. 异步
      2. 事件驱动
      3. 基于 NIO

      适用于:

      - 服务端
      - 客户端
      - TCP/UDP

## 从事件处理机制到 Reactor 模型

      Reactor 模式首先是事件驱动 的，有一个或者多个并发输入源，有一个 Service Handler 和多个 EventHandlers。

      这个 Service Handler 会同步 的将输入的请求多路复用的分发给相应的 Event Handler。

## 从 Reactor 模型到 Netty NIO

## Netty 运行原理

## 关键对象

      - Bootstrap: 启动线程，开启 socket
      - EventLoopGroup: 线程池
      - EventLoop: 线程
      - SocketChannel: 连接
      - ChannelInitializer: 初始化
      - ChannelPipeline: 处理器链
      - ChannelHandler: 处理器

## ChannelPipeline

## Event & Handler

      入站事件:

      - 通道激活和停用
      - 读操作事件
      - 异常事件
      - 用户事件

      出站事件:

      - 打开连接
      - 关闭连接
      - 写入数据
      - 刷新数据

      事件处理程序接口:

      - ChannelHandler
      - ChannelOutboundHandler
      - ChannelInboundHandler

      适配器(空实现，需要继承使用):

      - ChannelInboundHandlerAdapter
      - ChannelOutboundHandlerAdapter

      Netty 应用组成:

      - 网络事件
      - 应用程序逻辑事件
      - 事件处理程序

# 3. Netty 网络程序优化

## 粘包与拆包

      都是人为问题
      ByteToMessageDecoder 提供的一些常见的实现 类:

      1. FixedLengthFrameDecoder:定长协议解码器，我们可以指定固定的字节数算一个完整的 报文
      2. LineBasedFrameDecoder:行分隔符解码器， 遇到\n 或者\r\n，则认为是一个完整的报文
      3. DelimiterBasedFrameDecoder:分隔符解码 器，分隔符可以自己指定
      4. LengthFieldBasedFrameDecoder:长度编码 解码器，将报文划分为报文头/报文体
      5. JsonObjectDecoder:json 格式解码器，当检 测到匹配数量的“{” . ”}”或”[””]”时，则认为是 一个完整的 json 对象或者 json 数组

## Nagle 与 TCP_NODELAY

      网络拥堵与 Nagle 算法优化
      TCP_NODELAY

      Nagle 算法优化触发条件:

      - 缓冲区满
      - 达到超时

      MTU: Maxitum Transmission Unit 最大传输单元 1500 Byte
      MSS: Maxitum Segment Size 最大 分段大小 1460 Byte

## 连接优化

      注意 TCP 与 UDP 区别

## Netty 优化

      1. 不要阻塞 EventLoop
      2. 系统参数优化
         ulimit -a /proc/sys/net/ipv4/tcp_fin_timeout, TcpTimedWaitDelay
      3. 缓冲区优化 SO_RCVBUF/SO_SNDBUF/SO_BACKLOG/ REUSEXXX
      4. 心跳频率周期优化 心跳机制与断线重连
      5. 内存与 ByteBuffer 优化 DirectBuffer 与 HeapBuffer
      6. 其他优化

      - ioRatio:
      - Watermark
      - TrafficShaping

# 4. 典型应用:API 网关

      网关的结构和功能?

      网关的分类

      流量网关 关注稳定与安全

      - 全局性流控
      - 日志统计
      - 防止 SQL 注入
      - 防止 Web 攻击
      - 屏蔽工具扫描
      - 黑白 IP 名单
      - 证书/加解密处理

      业务网关 提供更好的服务

      - 服务级别流控
      - 服务降级与熔断
      - 路由与负载均衡、灰度策略
      - 服务过滤、聚合与发现
      - 权限验证与用户等级策略
      - 业务规则与参数校验
      - 多级缓存策略

---

      Zuul

      Zuul 是 Netflix 开源的 API 网关系统，它 的主要设计目标是动态路由、监控、弹性 和安全。
      Zuul 的内部原理可以简单看做是很多不同 功能 filter 的集合，最主要的就是 pre、 routing、post 这三种过滤器，分别作用 于调用业务服务 API 之前的请求处理、直 接响应、调用业务服务 API 之后的响应处理。

---

      Zuul2

      Zuul 2.x 是基于 Netty 内核重构的版本。
      核心功能:

      1. Service Discovery
      2. Load Balancing
      3. Connection Pooling
      4. Status Categories
      5. Retries
      6. Request Passport
      7. Request Attempts
      8. Origin Concurrency Protection
      9. HTTP/2
      10. Mutual TLS
      11. Proxy Protocol
      12. GZip
      13. WebSockets

---

      Spring Cloud Gateway

# 5. 自己动手实现 API 网关

      最简单的网关--gateway 1.0
      最简单的网关--gateway 2.0
      最简单的网关--gateway 3.0

# 6. 总结回顾与作业实践

      第 5 节课作业实践
      1、按今天的课程要求，实现一个网关，
      基础代码可以 fork:https://github.com/kimmking/JavaCourseCodes 02nio/nio02 文件夹下
      实现以后，代码提交到 Github。

      1. 周四作业:整合你上次作业的 httpclient/okhttp;
      2. 周四作业(可选):使用 netty 实现后端 http 访问(代替上一步骤);
      3. 周六作业:实现过滤器
      4. 周六作业(可选):实现路由
