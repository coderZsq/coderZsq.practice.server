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

# 删除注意
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

