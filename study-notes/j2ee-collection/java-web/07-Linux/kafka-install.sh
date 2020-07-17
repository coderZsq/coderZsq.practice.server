# kafka依赖zookeeper, 所以需要先安装zookeeper

# 下载安装包
wget https://mirror.bit.edu.cn/apache/kafka/2.5.0/kafka_2.13-2.5.0.tgz

# 解压安装包
tar -zxvf kafka_2.13-2.5.0.tgz -C /usr/local/

# 进入文件夹
cd kafka_2.13-2.5.0

# 启动kafka
./bin/kafka-server-start.sh -daemon config/server.properties

# 创建主题
# 现在我们来创建一个名字为'test'的Topic, 这个topic只有一个partition, 并且备份因子也设置为1
./bin/kafka-topics.sh --create --zookeeper 172.16.21.175:2181 --replication-factor 1 --partitions 1 --topic test
# 现在我们可以通过以下命令来查看kafaka中目前存在的topic
./bin/kafka-topics.sh --list --zookeeper 172.16.21.175:2181

# 删除主题
./bin/kafka-topics.sh --delete --topic test --zookeeper 172.16.21.175:2181

# 发送消息
./bin/kafka-console-producer.sh --broker-list 172.16.21.175:9092 --topic test
# > hello
# > kafka

# 消费消息
./bin/kafka-console-consumer.sh --bootstrap-server 172.16.21.175:9092 --consumer-property group.id=testGroup --topic test
# > kafka

# [root@localhost kafka_2.13-2.5.0]# jps
# 14177 QuorumPeerMain
# 23618 ConsoleProducer
# 14778 Kafka
# 4716 QuorumPeerMain
# 24988 ConsoleConsumer
# 14301 QuorumPeerMain
# 25455 Jps

# 消费之前的生产的消息
./bin/kafka-console-consumer.sh --bootstrap-server 172.16.21.175:9092 --from-beginning --topic test
# > hello
# > kafka

# 查看组名
./bin/kafka-consumer-groups.sh --bootstrap-server 172.16.21.175:9092 --list
# testGroup
# console-consumer-63560

# 查看消费者的消费偏移量
# 注意看 CURRENT-OFFSET  LOG-END-OFFSET  LAG 分别为当前消费偏移量, 结束的偏移量(HW), 落后消费的消息数
./bin/kafka-consumer-groups.sh --bootstrap-server 172.16.21.175:9092 --describe --group testGroup
# GROUP           TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID                                               HOST            CLIENT-ID
# testGroup       test            0          2               2               0               consumer-testGroup-1-76b1ee19-363b-4b67-91ba-27dd77da81ed /127.0.0.1      consumer-testGroup-1

# 消费多主题
./bin/kafka-console-consumer.sh --bootstrap-server 172.16.21.175:9092 --whitelist "test|test-2"

# 集群搭建
cd config/
cp server.properties server-1.properties
vi server-1.properties
# broker.id=1
# listeners=PLAINTEXT://172.16.21.175:9093
# log.dirs=/tmp/kafka-logs-1

cp server-1.properties server-2.properties
vi server-2.properties
# broker.id=2
# listeners=PLAINTEXT://172.16.21.175:9094
# log.dirs=/tmp/kafka-logs-2

# 启动集群
./bin/kafka-server-start.sh -daemon config/server-1.properties
./bin/kafka-server-start.sh -daemon config/server-2.properties
# [root@localhost kafka_2.13-2.5.0]# jps
# 14177 QuorumPeerMain
# 23618 ConsoleProducer
# 8082 Kafka
# 7652 Kafka
# 14778 Kafka
# 4716 QuorumPeerMain
# 8108 Jps
# 14301 QuorumPeerMain

# 创建一个新的topic, 副本数设置为3, 分区数设置为2
./bin/kafka-topics.sh --create --zookeeper 172.16.21.175:2181 --replication-factor 3 --partitions 2 --topic my-replicated-topic

# 查看topic的情况
# 第一行是所有分区的概要信息, 之后的每一行表示每一个partition的信息
# leader几点负责给定partition的所有读写请求
# replicas表示某个partition在哪几个broker上存在备份, 不管这个节点是不是'leader', 设置这个节点挂了, 也会列出
# isr是replicas的子集, 它只列出当前还存活着的, 并且已同步备份了该partition的节点
# Topic: my-replicated-topic	PartitionCount: 2	ReplicationFactor: 3	Configs:
# 	Topic: my-replicated-topic	Partition: 0	Leader: 2	Replicas: 2,0,1	Isr: 2,0,1
# 	Topic: my-replicated-topic	Partition: 1	Leader: 0	Replicas: 0,1,2	Isr: 0,1,2

./bin/kafka-topics.sh --describe --zookeeper 172.16.21.175:2181 --topic test
# Topic: test	PartitionCount: 1	ReplicationFactor: 1	Configs:
# 	Topic: test	Partition: 0	Leader: 0	Replicas: 0	Isr: 0

# 集群消息发布
./bin/kafka-console-producer.sh --broker-list 172.16.21.175:9092,172.16.21.175:9093,172.16.21.175:9094 --topic my-replicated-topic

# 集群消息消费
./bin/kafka-console-consumer.sh --bootstrap-server 172.16.21.175:9092,172.16.21.175:9093,172.16.21.175:9094 --from-beginning --topic my-replicated-topic

# zookeeper查看信息
# [zk: localhost:2181(CONNECTED) 0] ls /
# [admin, brokers, cluster, config, consumers, controller, controller_epoch, dubbo, isr_change_notification, latest_producer_id_block, locks, locks02, log_dir_event_notification, rpc, servers, zookeeper]
# [zk: localhost:2181(CONNECTED) 1] ls /brokers
# [ids, seqid, topics]
# [zk: localhost:2181(CONNECTED) 2] ls /brokers/ids
# [0, 1, 2]
# [zk: localhost:2181(CONNECTED) 3] ls /brokers/topics
# [__consumer_offsets, my-replicated-topic, test]
# [zk: localhost:2181(CONNECTED) 4] ls /brokers/topics/my-replicated-topic
# [partitions]
# [zk: localhost:2181(CONNECTED) 5] ls /brokers/topics/my-replicated-topic/partitions
# [0, 1]
# [zk: localhost:2181(CONNECTED) 6] ls /brokers/topics/my-replicated-topic/partitions/0
# [state]
# [zk: localhost:2181(CONNECTED) 7] ls /brokers/topics/my-replicated-topic/partitions/0/state
# []
# [zk: localhost:2181(CONNECTED) 8] get /brokers/topics/my-replicated-topic/partitions/0/state
# {"controller_epoch":1,"leader":2,"version":1,"leader_epoch":0,"isr":[2,0,1]}

# 增加topic的分区数量(目前kafka不支持减少分区)
./bin/kafka-topics.sh -alter --partitions 3 --zookeeper 127.0.0.1:2181 --topic test

# kafka manager
# 下载安装包
wget https://github.com/yahoo/kafka-manager/archive/1.3.3.18.zip

# 解压安装包
unzip 1.3.3.18.zip -d /usr/local/src/

# sbt编译
# yum安装sbt(因为kafka-manager需要sbt编译)
curl https://bintray.com/sbt/rpm/rpm > bintray-sbt-rpm.repo
sudo mv bintray-sbt-rpm.repo /etc/yum.repos.d/
sudo yum install sbt
sbt-version

# 编译 kafka manager
./sbt clean dist

# 安装
cd /usr/local/src/CMAK-1.3.3.18/target/universal/
unzip kafka-manager-1.3.3.18.zip -d /usr/local/

# 修改配置文件
cd conf/
vi application.conf
kafka-manager.zkhosts="172.16.21.175:2181"

# 启动服务
nohup bin/kafka-manager -Dconfig.file=conf/application.conf -Dhttp.port=9000 &
# [root@localhost ~]# jps
# 14177 QuorumPeerMain
# 8082 Kafka
# 10482 ProdServerStart
# 7652 Kafka
# 11061 Jps
# 4716 QuorumPeerMain
# 14301 QuorumPeerMain