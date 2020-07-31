# 下载源码
wget https://archive.apache.org/dist/rocketmq/4.7.0/rocketmq-all-4.7.0-source-release.zip

# 解压源码至源码目录
unzip rocketmq-all-4.7.0-source-release.zip -d /usr/local/src

# -P 激活指定的环境 -U 强制更新本地仓库的文件
mvn -Prelease-all -Dmaven.test.skip=true -U clean install

# 复制软件
cd distribution/target/rocketmq-4.7.0/
cp -R rocketmq-4.7.0/ /usr/local/

# 修改配置 
# 为了保证RocketMQ可以正常启动, 默认会使用比较大的内存, 建议给NameServer和Broker设置1G的内存
cd rocketmq-4.7.0/bin/
vi runbroker.sh
# JAVA_OPT="${JAVA_OPT} -server -Xms1g -Xmx1g -Xmn512m"

vi runserver.sh
# JAVA_OPT="${JAVA_OPT} -server -Xms1g -Xmx1g -Xmn512m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"

# 启动服务
nohup ./mqnamesrv &
nohup ./mqbroker -n 127.0.0.1:9876 &
# [root@localhost bin]# jps
# 27280 Jps
# 20771 NamesrvStartup
# 23336 BrokerStartup

# 关闭服务
./mqshutdown broker
./mqshutdown namesrv

# 下载管理控制台 
https://github.com/apache/rocketmq-externals
scp -P 22 -r rocketmq-console CentOS:/usr/local/src

# 进入到管理控制台项目 进行构建
mvn package -Dmaven.test.skip=true

# 启动rocket服务, 在启动管理控制台之前, 必须先启动NameServer
# 在启动目录创建一个application.properties
vi application.properties
# 配置信息如下:
# server.port=9999
# rocketmq.config.namesrvAddr=127.0.0.1:9876
# 启动管理控制台
nohup java -jar rocketmq-console-ng-1.0.1.jar &

# 查看启动日志
tail -100f nohub.out

# 访问浏览器
# http://172.16.21.175:9999/

# 运行测试程序
# 设置一个NameServer的地址的环境变量
cd /usr/local/rocketmq-4.7.0/
export NAMESRV_ADDR=127.0.0.1:9876
# 生产者
bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
# 消费者
bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer

# 过滤消息配置
vi conf/broker.conf
# brokerClusterName = DefaultCluster
# brokerName = broker-a
# namesrvAddr = 127.0.0.1:9876
# brokerId = 0
# deleteWhen = 04
# fileReservedTime = 48
# brokerRole = ASYNC_MASTER
# flushDiskType = ASYNC_FLUSH
# enablePropertyFilter = true
./mqshutdown broker
nohup ./mqbroker -c ../conf/broker.conf &

# namesrv配置
vi conf/namesrv.conf
# listenPort=9876
# useEpollNativeSelector=true
nohup ./mqnamesrv -c ../conf/namesrv.conf &

# 集群搭建 3台服务器 需要JAVA环境
# 调整运行内存 3台
cd rocketmq-4.7.0/bin
vi runbroker.sh
# JAVA_OPT="${JAVA_OPT} -server -Xms2g -Xmx2g -Xmn1g"
vi runserver.sh
# JAVA_OPT="${JAVA_OPT} -server -Xms512m -Xmx512m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"

# 启动三台节点的namesrv *3
nohup bin/mqnamesrv &

# 修改集群配置 
cd conf/
# 三主六从 异步
mkdir 3m-6s-async
cd 3m-6s-async

# 第一台机器
vi broker-a.properties
# # 集群名字
# brokerClusterName = DefaultCluster
# # broker的组名字
# brokerName = broker-a
# # 0 代表Master, 非0 代表Slave
# brokerId = 0
# # 删除过期文件时间, 凌晨4点
# deleteWhen = 04
# # 文件保留时间48小时
# fileReservedTime = 48
# # broker的角色:  ASYNC_MASTER ,SYNC_MASTER ,SLAVE
# brokerRole = ASYNC_MASTER
# # 数据刷盘模式 异步刷盘: ASYNC_FLUSH 同步刷盘: SYNC_FLUSH
# flushDiskType = ASYNC_FLUSH
# # broker的ip地址
# brokerIP1=47.101.146.105 # 外网地址
# brokerIP2=172.19.189.121 # 内网地址
# # NameServer的地址信息 三台局域网地址
# namesrvAddr=172.19.189.121:9876;172.19.189.121:9876;172.19.189.121:9876
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-a/store
# storePathCommitLog=/usr/local/rocketmq-4.7.0/data/broker-a/store/commitlog
# # 是否自动创建主题, 建议关闭
# autoCreateTopicEnable=false
# # 动运行的监听端口
# listenPort=10911

# 第二台机器
vi broker-b.properties
# # 集群名字
# brokerClusterName = DefaultCluster
# # broker的组名字
# brokerName = broker-b
# # 0 代表Master, 非0 代表Slave
# brokerId = 0
# # 删除过期文件时间, 凌晨4点
# deleteWhen = 04
# # 文件保留时间48小时
# fileReservedTime = 48
# # broker的角色:  ASYNC_MASTER ,SYNC_MASTER ,SLAVE
# brokerRole = ASYNC_MASTER
# # 数据刷盘模式 异步刷盘: ASYNC_FLUSH 同步刷盘: SYNC_FLUSH
# flushDiskType = ASYNC_FLUSH
# # broker的ip地址
# brokerIP1=47.101.146.105 # 外网地址
# brokerIP2=172.19.189.121 # 内网地址
# # NameServer的地址信息 三台局域网地址
# namesrvAddr=172.19.189.121:9876;172.19.189.121:9876;172.19.189.121:9876
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-b/store
# storePathCommitLog=/usr/local/rocketmq-4.7.0/data/broker-b/store/commitlog
# # 是否自动创建主题, 建议关闭
# autoCreateTopicEnable=false
# # 动运行的监听端口
# listenPort=10911

# 第三台机器
vi broker-c.properties
# # 集群名字
# brokerClusterName = DefaultCluster
# # broker的组名字
# brokerName = broker-c
# # 0 代表Master, 非0 代表Slave
# brokerId = 0
# # 删除过期文件时间, 凌晨4点
# deleteWhen = 04
# # 文件保留时间48小时
# fileReservedTime = 48
# # broker的角色:  ASYNC_MASTER ,SYNC_MASTER ,SLAVE
# brokerRole = ASYNC_MASTER
# # 数据刷盘模式 异步刷盘: ASYNC_FLUSH 同步刷盘: SYNC_FLUSH
# flushDiskType = ASYNC_FLUSH
# # broker的ip地址
# brokerIP1=47.101.146.105 # 外网地址
# brokerIP2=172.19.189.121 # 内网地址
# # NameServer的地址信息 三台局域网地址
# namesrvAddr=172.19.189.121:9876;172.19.189.121:9876;172.19.189.121:9876
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-c/store
# storePathCommitLog=/usr/local/rocketmq-4.7.0/data/broker-c/store/commitlog
# # 是否自动创建主题, 建议关闭
# autoCreateTopicEnable=false
# # 动运行的监听端口
# listenPort=10911

# 启动三台 master broker
nohup bin/mqbroker -c conf/3m-6s-async/broker-a.properties &
nohup bin/mqbroker -c conf/3m-6s-async/broker-b.properties &
nohup bin/mqbroker -c conf/3m-6s-async/broker-c.properties &

# 管理控制台
scp application.properties rocketmq-console-ng-1.0.1.jar 47.101.146.105 :/root/
vi application.properties
# server.port=9999
# rocketmq.config.namesrvAddr=172.19.189.121:9876;172.19.189.121:9876;172.19.189.121:9876
nohup java -jar rocketmq-console-ng-1.0.1.jar &

# 配置第一台机器的2个从节点
cd conf/3m-6s-async
vi broker-b-s.properties
# # 集群名字
# brokerClusterName = DefaultCluster
# # broker的组名字
# brokerName = broker-b
# # 0 代表Master, 非0 代表Slave
# brokerId = 1
# # 删除过期文件时间, 凌晨4点
# deleteWhen = 04
# # 文件保留时间48小时
# fileReservedTime = 48
# # broker的角色:  ASYNC_MASTER ,SYNC_MASTER ,SLAVE
# brokerRole = SLAVE
# # 数据刷盘模式 异步刷盘: ASYNC_FLUSH 同步刷盘: SYNC_FLUSH
# flushDiskType = ASYNC_FLUSH
# # broker的ip地址
# brokerIP1=47.101.146.105 # 外网地址
# brokerIP2=172.19.189.121 # 内网地址
# # NameServer的地址信息 三台局域网地址
# namesrvAddr=172.19.189.121:9876;172.19.189.121:9876;172.19.189.121:9876
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-b/store
# storePathCommitLog=/usr/local/rocketmq-4.7.0/data/broker-b/store/commitlog
# # 是否自动创建主题, 建议关闭
# autoCreateTopicEnable=false
# # 动运行的监听端口
# listenPort=10811
vi broker-c-s.properties
# # 集群名字
# brokerClusterName = DefaultCluster
# # broker的组名字
# brokerName = broker-c
# # 0 代表Master, 非0 代表Slave
# brokerId = 1
# # 删除过期文件时间, 凌晨4点
# deleteWhen = 04
# # 文件保留时间48小时
# fileReservedTime = 48
# # broker的角色:  ASYNC_MASTER ,SYNC_MASTER ,SLAVE
# brokerRole = SLAVE
# # 数据刷盘模式 异步刷盘: ASYNC_FLUSH 同步刷盘: SYNC_FLUSH
# flushDiskType = ASYNC_FLUSH
# # broker的ip地址
# brokerIP1=47.101.146.105 # 外网地址
# brokerIP2=172.19.189.121 # 内网地址
# # NameServer的地址信息 三台局域网地址
# namesrvAddr=172.19.189.121:9876;172.19.189.121:9876;172.19.189.121:9876
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-c/store
# storePathCommitLog=/usr/local/rocketmq-4.7.0/data/broker-c/store/commitlog
# # 是否自动创建主题, 建议关闭
# autoCreateTopicEnable=false
# # 动运行的监听端口
# listenPort=10711

# 配置第二台机器的2个从节点
cd conf/3m-6s-async
vi broker-a-s.properties
# # 集群名字
# brokerClusterName = DefaultCluster
# # broker的组名字
# brokerName = broker-a
# # 0 代表Master, 非0 代表Slave
# brokerId = 1
# # 删除过期文件时间, 凌晨4点
# deleteWhen = 04
# # 文件保留时间48小时
# fileReservedTime = 48
# # broker的角色:  ASYNC_MASTER ,SYNC_MASTER ,SLAVE
# brokerRole = SLAVE
# # 数据刷盘模式 异步刷盘: ASYNC_FLUSH 同步刷盘: SYNC_FLUSH
# flushDiskType = ASYNC_FLUSH
# # broker的ip地址
# brokerIP1=47.101.146.105 # 外网地址
# brokerIP2=172.19.189.121 # 内网地址
# # NameServer的地址信息 三台局域网地址
# namesrvAddr=172.19.189.121:9876;172.19.189.121:9876;172.19.189.121:9876
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-a/store
# storePathCommitLog=/usr/local/rocketmq-4.7.0/data/broker-a/store/commitlog
# # 是否自动创建主题, 建议关闭
# autoCreateTopicEnable=false
# # 动运行的监听端口
# listenPort=10811
vi broker-c-s.properties
# # 集群名字
# brokerClusterName = DefaultCluster
# # broker的组名字
# brokerName = broker-c
# # 0 代表Master, 非0 代表Slave
# brokerId = 2
# # 删除过期文件时间, 凌晨4点
# deleteWhen = 04
# # 文件保留时间48小时
# fileReservedTime = 48
# # broker的角色:  ASYNC_MASTER ,SYNC_MASTER ,SLAVE
# brokerRole = SLAVE
# # 数据刷盘模式 异步刷盘: ASYNC_FLUSH 同步刷盘: SYNC_FLUSH
# flushDiskType = ASYNC_FLUSH
# # broker的ip地址
# brokerIP1=47.101.146.105 # 外网地址
# brokerIP2=172.19.189.121 # 内网地址
# # NameServer的地址信息 三台局域网地址
# namesrvAddr=172.19.189.121:9876;172.19.189.121:9876;172.19.189.121:9876
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-c/store
# storePathCommitLog=/usr/local/rocketmq-4.7.0/data/broker-c/store/commitlog
# # 是否自动创建主题, 建议关闭
# autoCreateTopicEnable=false
# # 动运行的监听端口
# listenPort=10711

# 配置第三台机器的2个从节点
cd conf/3m-6s-async
vi broker-a-s.properties
# # 集群名字
# brokerClusterName = DefaultCluster
# # broker的组名字
# brokerName = broker-a
# # 0 代表Master, 非0 代表Slave
# brokerId = 2
# # 删除过期文件时间, 凌晨4点
# deleteWhen = 04
# # 文件保留时间48小时
# fileReservedTime = 48
# # broker的角色:  ASYNC_MASTER ,SYNC_MASTER ,SLAVE
# brokerRole = SLAVE
# # 数据刷盘模式 异步刷盘: ASYNC_FLUSH 同步刷盘: SYNC_FLUSH
# flushDiskType = ASYNC_FLUSH
# # broker的ip地址
# brokerIP1=47.101.146.105 # 外网地址
# brokerIP2=172.19.189.121 # 内网地址
# # NameServer的地址信息 三台局域网地址
# namesrvAddr=172.19.189.121:9876;172.19.189.121:9876;172.19.189.121:9876
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-a/store
# storePathCommitLog=/usr/local/rocketmq-4.7.0/data/broker-a/store/commitlog
# # 是否自动创建主题, 建议关闭
# autoCreateTopicEnable=false
# # 动运行的监听端口
# listenPort=10811
vi broker-b-s.properties
# # 集群名字
# brokerClusterName = DefaultCluster
# # broker的组名字
# brokerName = broker-b
# # 0 代表Master, 非0 代表Slave
# brokerId = 2
# # 删除过期文件时间, 凌晨4点
# deleteWhen = 04
# # 文件保留时间48小时
# fileReservedTime = 48
# # broker的角色:  ASYNC_MASTER ,SYNC_MASTER ,SLAVE
# brokerRole = SLAVE
# # 数据刷盘模式 异步刷盘: ASYNC_FLUSH 同步刷盘: SYNC_FLUSH
# flushDiskType = ASYNC_FLUSH
# # broker的ip地址
# brokerIP1=47.101.146.105 # 外网地址
# brokerIP2=172.19.189.121 # 内网地址
# # NameServer的地址信息 三台局域网地址
# namesrvAddr=172.19.189.121:9876;172.19.189.121:9876;172.19.189.121:9876
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-b/store
# storePathCommitLog=/usr/local/rocketmq-4.7.0/data/broker-b/store/commitlog
# # 是否自动创建主题, 建议关闭
# autoCreateTopicEnable=false
# # 动运行的监听端口
# listenPort=10711

# 启动六台 salve broker
nohup bin/mqbroker -c conf/3m-6s-async/broker-b-s.properties &
nohup bin/mqbroker -c conf/3m-6s-async/broker-c-s.properties &

nohup bin/mqbroker -c conf/3m-6s-async/broker-a-s.properties &
nohup bin/mqbroker -c conf/3m-6s-async/broker-c-s.properties &

nohup bin/mqbroker -c conf/3m-6s-async/broker-a-s.properties &
nohup bin/mqbroker -c conf/3m-6s-async/broker-b-s.properties &

# 在启动Broker的时候, 默认情况下我们指定的listenPort=10911, 但是在真正运行的时候, 会启动10911, 10909, 10912三个端口
# haListenPort: 10912 在数据进行主从同步的端口(Master Slave之间通信的端口)
# listenPort=10911 负责生产者和消费者和Broker进行连接通信
# vip_Channel_port=10909 负责生产者 创建Topic, 发送Message的端口

# 高可用集群Dledger
vi broker-a.properties
# # 累加配置
# # 把commitLog交给Dleger高可用管理
# enableDLegerCommitLog=true
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-a/store/dledger_store
# # 对于不同的broker需要添加不不同的组别
# dLegerGroup=broker-a
# # 参与投票选举的通信操作
# dLegerPeers=n0-172.19.189.121:40911;n1-172.19.189.121:40911;n2-172.19.189.121:40911
# # 角色id, 必须唯一
# dLegerSelfId=n0
vi broker-a-s.properties
# # 累加配置
# # 把commitLog交给Dleger高可用管理
# enableDLegerCommitLog=true
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-a/store/dledger_store
# # 对于不同的broker需要添加不不同的组别
# dLegerGroup=broker-a
# # 参与投票选举的通信操作
# dLegerPeers=n0-172.19.189.121:40911;n1-172.19.189.121:40911;n2-172.19.189.121:40911
# # 角色id, 必须唯一
# dLegerSelfId=n1
vi broker-a-s.properties
# # 累加配置
# # 把commitLog交给Dleger高可用管理
# enableDLegerCommitLog=true
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-a/store/dledger_store
# # 对于不同的broker需要添加不不同的组别
# dLegerGroup=broker-a
# # 参与投票选举的通信操作
# dLegerPeers=n0-172.19.189.121:40911;n1-172.19.189.121:40911;n2-172.19.189.121:40911
# # 角色id, 必须唯一
# dLegerSelfId=n2

vi broker-b.properties
# # 累加配置
# # 把commitLog交给Dleger高可用管理
# enableDLegerCommitLog=true
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-b/store/dledger_store
# # 对于不同的broker需要添加不不同的组别
# dLegerGroup=broker-b
# # 参与投票选举的通信操作
# dLegerPeers=n4-172.19.189.121:40811;n5-172.19.189.121:40811;n6-172.19.189.121:40811
# # 角色id, 必须唯一
# dLegerSelfId=n4
vi broker-b-s.properties
# # 累加配置
# # 把commitLog交给Dleger高可用管理
# enableDLegerCommitLog=true
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-b/store/dledger_store
# # 对于不同的broker需要添加不不同的组别
# dLegerGroup=broker-b
# # 参与投票选举的通信操作
# dLegerPeers=n4-172.19.189.121:40811;n5-172.19.189.121:40811;n6-172.19.189.121:40811
# # 角色id, 必须唯一
# dLegerSelfId=n5
vi broker-b-s.properties
# # 累加配置
# # 把commitLog交给Dleger高可用管理
# enableDLegerCommitLog=true
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-b/store/dledger_store
# # 对于不同的broker需要添加不不同的组别
# dLegerGroup=broker-b
# # 参与投票选举的通信操作
# dLegerPeers=n4-172.19.189.121:40811;n5-172.19.189.121:40811;n6-172.19.189.121:40811
# # 角色id, 必须唯一
# dLegerSelfId=n6

vi broker-c.properties
# # 累加配置
# # 把commitLog交给Dleger高可用管理
# enableDLegerCommitLog=true
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-c/store/dledger_store
# # 对于不同的broker需要添加不不同的组别
# dLegerGroup=broker-c
# # 参与投票选举的通信操作
# dLegerPeers=n7-172.19.189.121:40711;n8-172.19.189.121:40711;n9-172.19.189.121:40711
# # 角色id, 必须唯一
# dLegerSelfId=n7
vi broker-c-s.properties
# # 累加配置
# # 把commitLog交给Dleger高可用管理
# enableDLegerCommitLog=true
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-c/store/dledger_store
# # 对于不同的broker需要添加不不同的组别
# dLegerGroup=broker-c
# # 参与投票选举的通信操作
# dLegerPeers=n7-172.19.189.121:40711;n8-172.19.189.121:40711;n9-172.19.189.121:40711
# # 角色id, 必须唯一
# dLegerSelfId=n8
vi broker-c-s.properties
# # 累加配置
# # 把commitLog交给Dleger高可用管理
# enableDLegerCommitLog=true
# storePathRootDir=/usr/local/rocketmq-4.7.0/data/broker-c/store/dledger_store
# # 对于不同的broker需要添加不不同的组别
# dLegerGroup=broker-c
# # 参与投票选举的通信操作
# dLegerPeers=n7-172.19.189.121:40711;n8-172.19.189.121:40711;n9-172.19.189.121:40711
# # 角色id, 必须唯一
# dLegerSelfId=n9

# 启动高可用 broker
nohup bin/mqbroker -c conf/3m-6s-async/broker-a.properties &
nohup bin/mqbroker -c conf/3m-6s-async/broker-b-s.properties &
nohup bin/mqbroker -c conf/3m-6s-async/broker-c-s.properties &

nohup bin/mqbroker -c conf/3m-6s-async/broker-b.properties &
nohup bin/mqbroker -c conf/3m-6s-async/broker-a-s.properties &
nohup bin/mqbroker -c conf/3m-6s-async/broker-c-s.properties &

nohup bin/mqbroker -c conf/3m-6s-async/broker-c.properties &
nohup bin/mqbroker -c conf/3m-6s-async/broker-a-s.properties &
nohup bin/mqbroker -c conf/3m-6s-async/broker-b-s.properties &