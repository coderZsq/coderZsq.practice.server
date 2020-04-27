# 将tar包上传到云系统
scp -P 22 -r apache-zookeeper-3.6.0-bin.tar.gz Aliyun:/root/software

# 解压tar包
tar -zxvf apache-zookeeper-3.6.0-bin.tar.gz -C /usr/local/

# 进入conf目录
cd /usr/local/apache-zookeeper-3.6.0-bin/conf

# 重定向配置信息
cat zoo_sample.cfg | grep -v "#" | grep -v "^$" > zoo.cfg

# 修改配置信息
vi zoo.cfg

# tickTime=2000 # 心跳时间单位, 2s
# initLimit=10 # 集群启动的时候时间限制: 10 * 2000 = 20s
# syncLimit=5 # 同步数据的时间数量 10s 同步数据的时间
# dataDir=/usr/local/apache-zookeeper-3.6.0-bin/data # 数据存放的目录
# clientPort=2181 # 客户端连接端口
# 集群中的服务列表 1 2 3 代表的是一个服务的id, 需要和myid(dataDir目录)文件中的内容一致, 而且在 1-254之间
# server.1=172.19.189.121:2888:3888 # 2888: 数据同步端口, 3888: 选举端口
# server.2=172.19.189.121:2887:3887
# server.3=172.19.189.121:2889:3889

# 创建data目录
cd /usr/local/apache-zookeeper-3.6.0-bin/
mkdir data

# 重定向到myid
cd data
echo 1 > myid

# 配置三台 zookeeper
mv apache-zookeeper-3.6.0-bin zookeeper-server-01
cp -R zookeeper-server-01/ zookeeper-server-02
cp -R zookeeper-server-01/ zookeeper-server-03

cd zookeeper-server-01/conf
vi zoo.cfg
# dataDir=/usr/local/zookeeper-server-01/data

cd zookeeper-server-02/data/
echo 2 > myid

cd zookeeper-server-02/conf
vi zoo.cfg
# dataDir=/usr/local/zookeeper-server-02/data
# clientPort=2182

cd zookeeper-server-03/data/
echo 3 > myid

cd zookeeper-server-03/conf
vi zoo.cfg
# dataDir=/usr/local/zookeeper-server-03/data
# clientPort=2183

# 启动 zookeeper 
/usr/local/zookeeper-server-01/bin/zkServer.sh start
/usr/local/zookeeper-server-02/bin/zkServer.sh start
/usr/local/zookeeper-server-03/bin/zkServer.sh start

# 关闭 zookeeper 
/usr/local/zookeeper-server-01/bin/zkServer.sh stop
/usr/local/zookeeper-server-02/bin/zkServer.sh stop
/usr/local/zookeeper-server-03/bin/zkServer.sh stop

# 查看 zookeeper 状态
/usr/local/zookeeper-server-01/bin/zkServer.sh status
# ZooKeeper JMX enabled by default
# Using config: /usr/local/zookeeper-server-01/bin/../conf/zoo.cfg
# Client port found: 2181. Client address: localhost.
# Mode: follower
/usr/local/zookeeper-server-02/bin/zkServer.sh status
# ZooKeeper JMX enabled by default
# Using config: /usr/local/zookeeper-server-02/bin/../conf/zoo.cfg
# Client port found: 2182. Client address: localhost.
# Mode: leader
/usr/local/zookeeper-server-03/bin/zkServer.sh status
# ZooKeeper JMX enabled by default
# Using config: /usr/local/zookeeper-server-03/bin/../conf/zoo.cfg
# Client port found: 2183. Client address: localhost.
# Mode: follower
jps
# 27634 QuorumPeerMain
# 27682 QuorumPeerMain
# 27749 QuorumPeerMain
# 27902 Jps
netstat -natp
# Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
# tcp        0      0 172.19.189.121:3887     0.0.0.0:*               LISTEN      27682/java
# tcp        0      0 172.19.189.121:3888     0.0.0.0:*               LISTEN      27634/java
# tcp        0      0 172.19.189.121:3889     0.0.0.0:*               LISTEN      27749/java

# 连接 CLI 客户端
/usr/local/zookeeper-server-01/bin/zkCli.sh # ctrl + l 清屏

[zk: localhost:2181(CONNECTED) 0] help
# ZooKeeper -server host:port cmd args
# 	addWatch [-m mode] path # optional mode is one of [PERSISTENT, PERSISTENT_RECURSIVE] - default is PERSISTENT_RECURSIVE
# 	addauth scheme auth
# 	close
# 	config [-c] [-w] [-s]
# 	connect host:port
# 	create [-s] [-e] [-c] [-t ttl] path [data] [acl]
# 	delete [-v version] path
# 	deleteall path [-b batch size]
# 	delquota [-n|-b] path
# 	get [-s] [-w] path
# 	getAcl [-s] path
# 	getAllChildrenNumber path
# 	getEphemerals path
# 	history
# 	listquota path
# 	ls [-s] [-w] [-R] path
# 	printwatches on|off
# 	quit
# 	reconfig [-s] [-v version] [[-file path] | [-members serverID=host:port1:port2;port3[,...]*]] | [-add serverId=host:port1:port2;port3[,...]]* [-remove serverId[,...]*]
# 	redo cmdno
# 	removewatches path [-c|-d|-a] [-l]
# 	set [-s] [-v version] path data
# 	setAcl [-s] [-v version] [-R] path acl
# 	setquota -n|-b val path
# 	stat [-w] path
# 	sync path
# 	version

# 创建持久化节点 (磁盘)
[zk: localhost:2181(CONNECTED) 1] create /pNode
# Created /pNode
[zk: localhost:2181(CONNECTED) 2] create /pNode/node01 data01
# Created /pNode/node01

# 创建临时节点 不能创建子节点, 关闭后会删除 (内存)
[zk: localhost:2181(CONNECTED) 3] create -e /tempNode
# Created /tempNode
[zk: localhost:2181(CONNECTED) 4] ls /
[pNode, tempNode, zookeeper]

# 创建顺序节点
[zk: localhost:2181(CONNECTED) 5] create -s /pNode/node
Created /pNode/node0000000001
[zk: localhost:2181(CONNECTED) 6] create -s /pNode/node
Created /pNode/node0000000002
[zk: localhost:2181(CONNECTED) 7] ls /pNode
[node0000000001, node0000000002, node01]

# 创建容器节点
[zk: localhost:2181(CONNECTED) 0] create -c /cnode cnode
# Created /cnode
[zk: localhost:2181(CONNECTED) 1] create -c /cnode/child01 child01
# Created /cnode/child01
[zk: localhost:2181(CONNECTED) 2] create -c /cnode/child02 child02
# Created /cnode/child02
[zk: localhost:2181(CONNECTED) 3] ls /cnode
# [child01, child02]
[zk: localhost:2181(CONNECTED) 4] delete /cnode/child01
[zk: localhost:2181(CONNECTED) 5] delete /cnode/child02
[zk: localhost:2181(CONNECTED) 6] ls /cnode
# []
[zk: localhost:2181(CONNECTED) 7] get /cnode
# cnode
[zk: localhost:2181(CONNECTED) 8] get /cnode # 1分钟后如果容器节点中没有子节点, 自动删除该节点, znode.container.checkIntervalMs
# org.apache.zookeeper.KeeperException$NoNodeException: KeeperErrorCode = NoNode for /cnode

# 查看节点
[zk: localhost:2181(CONNECTED) 9] ls -R / # -R 递归查看
# /
# /pNode
# /zookeeper
# /pNode/node0000000001
# /pNode/node0000000002
# /pNode/node01
# /zookeeper/config
# /zookeeper/quota
[zk: localhost:2181(CONNECTED) 10] ls -s /pNode # -s 查看状态信息
# [node0000000001, node0000000002, node01]
# cZxid = 0x100000002
# ctime = Wed Apr 22 13:41:53 CST 2020
# mZxid = 0x100000002
# mtime = Wed Apr 22 13:41:53 CST 2020
# pZxid = 0x100000006
# cversion = 3
# dataVersion = 0
# aclVersion = 0
# ephemeralOwner = 0x0
# dataLength = 0
# numChildren = 3

# 删除节点
[zk: localhost:2181(CONNECTED) 12] delete -v 1 /pNode
# version No is not valid : /pNode
[zk: localhost:2181(CONNECTED) 13] delete -v 3 /pNode
# version No is not valid : /pNode
[zk: localhost:2181(CONNECTED) 14] deleteall /pNode
[zk: localhost:2181(CONNECTED) 15] ls /
# [zookeeper]

# 绑定事件
[zk: localhost:2181(CONNECTED) 17] printwatches on
[zk: localhost:2181(CONNECTED) 18] create /vNode
# Created /vNode
[zk: localhost:2181(CONNECTED) 19] ls -w /vNode
# []
[zk: localhost:2181(CONNECTED) 20] set /vNode abc
[zk: localhost:2181(CONNECTED) 21] create /vNode/node1
# WATCHER::
# WatchedEvent state:SyncConnected type:NodeChildrenChanged path:/vNode
# Created /vNode/node1
[zk: localhost:2181(CONNECTED) 22] create /vNode/abc
# Node already exists: /vNode/abc  
[zk: localhost:2181(CONNECTED) 23] get -w /vNode
# abc
[zk: localhost:2181(CONNECTED) 24] set /vNode bbc
# WATCHER::
# WatchedEvent state:SyncConnected type:NodeDataChanged path:/vNode

[zk: localhost:2181(CONNECTED) 25] addWatch /vNode
[zk: localhost:2181(CONNECTED) 26] create /vNode/node4
# WATCHER::
# WatchedEvent state:SyncConnected type:NodeCreated path:/vNode/node4
# Created /vNode/node4
[zk: localhost:2181(CONNECTED) 27] create /vNode/node5
# WATCHER::
# WatchedEvent state:SyncConnected type:NodeCreated path:/vNode/node5
# Created /vNode/node5
[zk: localhost:2181(CONNECTED) 28] set /vNode/node5 node5
# WATCHER::
# WatchedEvent state:SyncConnected type:NodeDataChanged path:/vNode/node5

[zk: localhost:2181(CONNECTED) 29] create /eNode
# Created /eNode
[zk: localhost:2181(CONNECTED) 31] addWatch -m PERSISTENT /eNode
[zk: localhost:2181(CONNECTED) 32] set /eNode enode
# WATCHER::
# WatchedEvent state:SyncConnected type:NodeDataChanged path:/eNode
[zk: localhost:2181(CONNECTED) 33] create /eNode/node1
# WATCHER::
# WatchedEvent state:SyncConnected type:NodeChildrenChanged path:/eNode
# Created /eNode/node1