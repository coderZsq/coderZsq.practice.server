# 安装C语言编译环境 gcc
yum install -y gcc automake autoconf libtool make

# 下载redis
wget http://download.redis.io/releases/redis-5.0.8.tar.gz

# 解压redis
tar -zxvf redis-5.0.8.tar.gz -C /usr/local

# 切换目录
cd /usr/local/redis-5.0.8/

# 编译redis
make

# 安装redis
make PREFIX=/usr/local/redis-5.0.8 install

# 启动redis
/usr/local/redis-5.0.8/bin/redis-server
/usr/local/redis-5.0.8/bin/redis-server --port 6380

# 查看启动状态
netstat -ntlp

# 创建配置目录
mkdir conf
cp redis.conf conf/
cd conf/
cp redis.conf redis_6379.conf
cd /usr/local/redis-5.0.8/
bin/redis-server conf/redis_6379.conf

# 修改配置
mkdir data
vi /usr/local/redis-5.0.8/conf/redis_6379.conf
# /daemonize yes
# /bind 0.0.0.0 仅测试使用, 生产使用默认
# /dir /usr/local/redis-5.0.8/data
# /logfile "6379.log"
# /requirepass ssh-keygen 添加密码
# /appendfilename "appendonly_6379.aof" 
# /appendonly yes
bin/redis-cli shutdown
bin/redis-server conf/redis_6379.conf
bin/redis-cli

cat data/6379.log

# 关闭redis
/usr/local/redis-5.0.8/bin/redis-cli shutdown
/usr/local/redis-5.0.8/bin/redis-cli -a ssh-keygen shutdown

# 高可用
# 1. 主从复制
# 1.1 ========命令行========
cat redis_6379.conf |grep -v '#' |grep -v '^$'

vi redis_6379.conf
:1,$d

# port 6379
# daemonize yes
# pidfile /var/run/redis-6379.pid
# logfile 6379.log
# dbfilename dump_6379.rdb
# dir /usr/local/redis-5.0.8/data

cp redis_6379.conf redis_6380.conf
vi redis_6380.conf
:%s/6379/6380

bin/redis-server conf/redis_6379.conf
bin/redis-server conf/redis_6380.conf
bin/redis-cli
bin/redis-cli -p 6380

# 127.0.0.1:6379> info replication
# # Replication
# role:master
# connected_slaves:0
# master_replid:02c7e54fd2851069f87ed54771134dece3f40954
# master_replid2:0000000000000000000000000000000000000000
# master_repl_offset:0
# second_repl_offset:-1
# repl_backlog_active:0
# repl_backlog_size:1048576
# repl_backlog_first_byte_offset:0
# repl_backlog_histlen:0

SLAVEOF 127.0.0.1 6379

# 127.0.0.1:6380> info replication
# # Replication
# role:slave
# master_host:127.0.0.1
# master_port:6379
# master_link_status:up
# master_last_io_seconds_ago:4
# master_sync_in_progress:0
# slave_repl_offset:225
# slave_priority:100
# slave_read_only:1
# connected_slaves:0
# master_replid:5553e504b01d2a18ddc55199b577fbd2881c639d
# master_replid2:0000000000000000000000000000000000000000
# master_repl_offset:225
# second_repl_offset:-1
# repl_backlog_active:1
# repl_backlog_size:1048576
# repl_backlog_first_byte_offset:1
# repl_backlog_histlen:225

SLAVEOF NO ONE

# 127.0.0.1:6380> info replication
# # Replication
# role:master
# connected_slaves:0
# master_replid:4971b37aea0ab49052d5973a01d38da9356fc5a3
# master_replid2:5553e504b01d2a18ddc55199b577fbd2881c639d
# master_repl_offset:365
# second_repl_offset:366
# repl_backlog_active:1
# repl_backlog_size:1048576
# repl_backlog_first_byte_offset:1
# repl_backlog_histlen:365

# 1.2 =======配置========
vi redis_6379.conf
# bind 172.16.21.175

vi redis_6380.conf
# bind 172.16.21.175
# replicaof 172.16.21.175 6379
bin/redis-cli -p 6380 shutdown
bin/redis-server conf/redis_6380.conf
bin/redis-cli -p 6380

bin/redis-server conf/redis_6379.conf
bin/redis-server conf/redis_6380.conf
bin/redis-cli -p 6380 -h 172.16.21.175

# 2. Sentinel
# 配置开启主从节点
vi redis_7000.conf
# port 7000
# daemonize yes
# pidfile /var/run/redis-7000.pid
# logfile 7000.log
# dir /usr/local/redis-5.0.8/data
# bind 172.16.21.175

vi redis_7001.conf
# port 7001
# daemonize yes
# pidfile /var/run/redis-7001.pid
# logfile 7001.log
# dir /usr/local/redis-5.0.8/data
# bind 172.16.21.175
# replicaof 172.16.21.175 7000

vi redis_7002.conf
# port 7002
# daemonize yes
# pidfile /var/run/redis-7002.pid
# logfile 7002.log
# dir /usr/local/redis-5.0.8/data
# bind 172.16.21.175
# replicaof 172.16.21.175 7000

bin/redis-server conf/redis_7000.conf
bin/redis-server conf/redis_7001.conf
bin/redis-server conf/redis_7002.conf

# 配置开启sentinel监控主节点
vi sentinel_26379.conf
# port 26379
# daemonize yes
# pidfile /var/run/redis-sentinel-26379.pid
# logfile "26379.log"
# dir /usr/local/redis-5.0.8/data/
# sentinel monitor mymaster 172.16.21.175 7000 2
# sentinel down-after-milliseconds mymaster 30000
# sentinel parallel-syncs mymaster 1
# sentinel failover-timeout mymaster 180000
# sentinel deny-scripts-reconfig yes
# protected-mode no
# bind 172.16.21.175

vi sentinel_26380.conf
# port 26380
# daemonize yes
# pidfile /var/run/redis-sentinel-26380.pid
# logfile "26380.log"
# dir /usr/local/redis-5.0.8/data/
# sentinel monitor mymaster 172.16.21.175 7000 2
# sentinel down-after-milliseconds mymaster 30000
# sentinel parallel-syncs mymaster 1
# sentinel failover-timeout mymaster 180000
# sentinel deny-scripts-reconfig yes
# protected-mode no
# bind 172.16.21.175

vi sentinel_26381.conf
# port 26381
# daemonize yes
# pidfile /var/run/redis-sentinel-26381.pid
# logfile "26381.log"
# dir /usr/local/redis-5.0.8/data/
# sentinel monitor mymaster 172.16.21.175 7000 2
# sentinel down-after-milliseconds mymaster 30000
# sentinel parallel-syncs mymaster 1
# sentinel failover-timeout mymaster 180000
# sentinel deny-scripts-reconfig yes
# protected-mode no
# bind 172.16.21.175

# 启动哨兵
bin/redis-sentinel conf/sentinel_26379.conf
bin/redis-sentinel conf/sentinel_26380.conf
bin/redis-sentinel conf/sentinel_26381.conf

# 模拟单点故障
bin/redis-cli -h 172.16.21.175 -p 7000 shutdown

# 3. Cluster 集群配置
# 3.1 原生命令安装
# 集群配置
vi redis_7000.conf
# port 7000
# daemonize yes
# dir /usr/local/redis-5.0.8/data
# dbfilename dump_7000.dbf
# logfile 7000.log
# cluster-enabled yes
# cluster-config-file node_7000.conf
# cluster-node-timeout 15000
# cluster-require-full-coverage no
# bind 172.16.21.175
# protected-mode no

sed 's/7000/7001/g' redis_7000.conf > redis_7001.conf
sed 's/7000/7002/g' redis_7000.conf > redis_7002.conf
sed 's/7000/7003/g' redis_7000.conf > redis_7003.conf
sed 's/7000/7004/g' redis_7000.conf > redis_7004.conf
sed 's/7000/7005/g' redis_7000.conf > redis_7005.conf

# 启动redis集群
bin/redis-server conf/redis_7000.conf
bin/redis-server conf/redis_7001.conf
bin/redis-server conf/redis_7002.conf
bin/redis-server conf/redis_7003.conf
bin/redis-server conf/redis_7004.conf
bin/redis-server conf/redis_7005.conf

# 查看集群启动进程
ps -ef | grep redis

# 节点通信
bin/redis-cli -c -h 172.16.21.175 -p 7000
# 172.16.21.175:7000> cluster info
# cluster_state:fail
# cluster_slots_assigned:0
# cluster_slots_ok:0
# cluster_slots_pfail:0
# cluster_slots_fail:0
# cluster_known_nodes:1
# cluster_size:0
# cluster_current_epoch:0
# cluster_my_epoch:0
# cluster_stats_messages_sent:0
# cluster_stats_messages_received:0

# 172.16.21.175:7000> cluster nodes
# f0571ecd4569d50dc11805bc47f51e8dfed5a519 :7000@17000 myself,master - 0 0 0 connected

# 172.16.21.175:7001> cluster nodes
# 42d7a3f2a0d1313699afd6b6ef3cf63d1750e28a :7001@17001 myself,master - 0 0 0 connected

cluster meet 172.16.21.175 7000
cluster meet 172.16.21.175 7002
cluster meet 172.16.21.175 7003
cluster meet 172.16.21.175 7004
cluster meet 172.16.21.175 7005
# 172.16.21.175:7001> cluster nodes
# 42d7a3f2a0d1313699afd6b6ef3cf63d1750e28a 172.16.21.175:7001@17001 myself,master - 0 1589341019000 1 connected
# f0571ecd4569d50dc11805bc47f51e8dfed5a519 172.16.21.175:7000@17000 master - 0 1589341017000 0 connected
# 24990c20b06fb7d6dfd9863c4721b30f0357b825 172.16.21.175:7003@17003 master - 0 1589341017537 3 connected
# 3b6559ccdf99e39a62113719590f43e562051db4 172.16.21.175:7005@17005 master - 0 1589341018544 4 connected
# 270f437d17c6ab49bc0b16021155d1e065a840ff 172.16.21.175:7004@17004 master - 0 1589341020560 5 connected
# 9d535ea844750400d4b23acfd8408e1c9eb03553 172.16.21.175:7002@17002 master - 0 1589341019552 2 connected

bin/redis-cli -c -h 172.16.21.175 -p 7000
# 172.16.21.175:7000> cluster nodes
# 270f437d17c6ab49bc0b16021155d1e065a840ff 172.16.21.175:7004@17004 master - 0 1589341070675 5 connected
# f0571ecd4569d50dc11805bc47f51e8dfed5a519 172.16.21.175:7000@17000 myself,master - 0 1589341069000 0 connected
# 9d535ea844750400d4b23acfd8408e1c9eb03553 172.16.21.175:7002@17002 master - 0 1589341073694 2 connected
# 24990c20b06fb7d6dfd9863c4721b30f0357b825 172.16.21.175:7003@17003 master - 0 1589341071680 3 connected
# 42d7a3f2a0d1313699afd6b6ef3cf63d1750e28a 172.16.21.175:7001@17001 master - 0 1589341072688 1 connected
# 3b6559ccdf99e39a62113719590f43e562051db4 172.16.21.175:7005@17005 master - 0 1589341070000 4 connected

# 172.16.21.175:7000> cluster info
# cluster_state:fail
# cluster_slots_assigned:0
# cluster_slots_ok:0
# cluster_slots_pfail:0
# cluster_slots_fail:0
# cluster_known_nodes:6
# cluster_size:0
# cluster_current_epoch:5
# cluster_my_epoch:0
# cluster_stats_messages_ping_sent:152
# cluster_stats_messages_pong_sent:155
# cluster_stats_messages_meet_sent:2
# cluster_stats_messages_sent:309
# cluster_stats_messages_ping_received:151
# cluster_stats_messages_pong_received:154
# cluster_stats_messages_meet_received:4
# cluster_stats_messages_received:309

# 分配slot槽位
cd bin/

# 编写脚本
vi addslots.sh
# # /bin/bash
# start=$1
# end=$2
# port=$3
# for slot in `seq ${start} ${end}`
# do 
#   /usr/local/redis-5.0.8/bin/redis-cli -h 172.16.21.175 -p ${port} cluster addslots ${slot}
# done

# 添加权限
chmod 775 addslots.sh

# 执行脚本
sh addslots.sh 0 5460 7000
sh addslots.sh 5461 10922 7001
sh addslots.sh 10923 16383 7002

# 查看 cluster
bin/redis-cli -c -h 172.16.21.175 -p 7000
172.16.21.175:7000> cluster info
# cluster_state:ok
# cluster_slots_assigned:16384
# cluster_slots_ok:16384
# cluster_slots_pfail:0
# cluster_slots_fail:0
# cluster_known_nodes:6
# cluster_size:3
# cluster_current_epoch:5
# cluster_my_epoch:0
# cluster_stats_messages_ping_sent:1230
# cluster_stats_messages_pong_sent:1301
# cluster_stats_messages_meet_sent:2
# cluster_stats_messages_sent:2533
# cluster_stats_messages_ping_received:1297
# cluster_stats_messages_pong_received:1232
# cluster_stats_messages_meet_received:4
# cluster_stats_messages_received:2533

# 172.16.21.175:7000> cluster nodes
# 270f437d17c6ab49bc0b16021155d1e065a840ff 172.16.21.175:7004@17004 master - 0 1589342251000 5 connected
# f0571ecd4569d50dc11805bc47f51e8dfed5a519 172.16.21.175:7000@17000 myself,master - 0 1589342251000 0 connected 0-5460
# 9d535ea844750400d4b23acfd8408e1c9eb03553 172.16.21.175:7002@17002 master - 0 1589342250000 2 connected 10923-16383
# 24990c20b06fb7d6dfd9863c4721b30f0357b825 172.16.21.175:7003@17003 master - 0 1589342252667 3 connected
# 42d7a3f2a0d1313699afd6b6ef3cf63d1750e28a 172.16.21.175:7001@17001 master - 0 1589342253672 1 connected 5461-10922
# 3b6559ccdf99e39a62113719590f43e562051db4 172.16.21.175:7005@17005 master - 0 1589342251661 4 connected

# 配置从节点
bin/redis-cli -h 172.16.21.175 -p 7003 cluster replicate f0571ecd4569d50dc11805bc47f51e8dfed5a519
bin/redis-cli -h 172.16.21.175 -p 7004 cluster replicate 42d7a3f2a0d1313699afd6b6ef3cf63d1750e28a
bin/redis-cli -h 172.16.21.175 -p 7005 cluster replicate 9d535ea844750400d4b23acfd8408e1c9eb03553
# [root@localhost redis-5.0.8]# bin/redis-cli -c -h 172.16.21.175 -p 7000
# 172.16.21.175:7000> cluster nodes
# 270f437d17c6ab49bc0b16021155d1e065a840ff 172.16.21.175:7004@17004 slave 42d7a3f2a0d1313699afd6b6ef3cf63d1750e28a 0 1589346095517 5 connected
# f0571ecd4569d50dc11805bc47f51e8dfed5a519 172.16.21.175:7000@17000 myself,master - 0 1589346096000 0 connected 0-5460
# 9d535ea844750400d4b23acfd8408e1c9eb03553 172.16.21.175:7002@17002 master - 0 1589346096524 2 connected 10923-16383
# 24990c20b06fb7d6dfd9863c4721b30f0357b825 172.16.21.175:7003@17003 slave f0571ecd4569d50dc11805bc47f51e8dfed5a519 0 1589346094000 3 connected
# 42d7a3f2a0d1313699afd6b6ef3cf63d1750e28a 172.16.21.175:7001@17001 master - 0 1589346097531 1 connected 5461-10922
# 3b6559ccdf99e39a62113719590f43e562051db4 172.16.21.175:7005@17005 slave 9d535ea844750400d4b23acfd8408e1c9eb03553 0 1589346094511 4 connected

# 3.2 官方工具安装
# 杀死所有redis进程
ps -ef | grep redis | grep -v grep | awk '{print $2}' | xargs kill -9
# 删除所有数据
cd data
rm -fr *

# 启动集群
bin/redis-server conf/redis_7000.conf
bin/redis-server conf/redis_7001.conf
bin/redis-server conf/redis_7002.conf
bin/redis-server conf/redis_7003.conf
bin/redis-server conf/redis_7004.conf
bin/redis-server conf/redis_7005.conf

# --cluster-replicas 表示主从比例 1:1 / 1:2
bin/redis-cli --cluster create 172.16.21.175:7000 172.16.21.175:7001 172.16.21.175:7002 172.16.21.175:7003 172.16.21.175:7004 172.16.21.175:7005 --cluster-replicas 1
# [root@localhost redis-5.0.8]# bin/redis-cli --cluster create 172.16.21.175:7000 172.16.21.175:7001 172.16.21.175:7002 172.16.21.175:7003 172.16.21.175:7004 172.16.21.175:7005 --cluster-replicas 1
# >>> Performing hash slots allocation on 6 nodes...
# Master[0] -> Slots 0 - 5460
# Master[1] -> Slots 5461 - 10922
# Master[2] -> Slots 10923 - 16383
# Adding replica 172.16.21.175:7004 to 172.16.21.175:7000
# Adding replica 172.16.21.175:7005 to 172.16.21.175:7001
# Adding replica 172.16.21.175:7003 to 172.16.21.175:7002
# >>> Trying to optimize slaves allocation for anti-affinity
# [WARNING] Some slaves are in the same host as their master
# M: 6b969f42305156a482fb9d6dc3821442793cd147 172.16.21.175:7000
#    slots:[0-5460] (5461 slots) master
# M: 26386e84fadb3cfe417ce24a4472ec749035bfb6 172.16.21.175:7001
#    slots:[5461-10922] (5462 slots) master
# M: b1e97ffddc507c6170e6e62d96ad886c992f8a76 172.16.21.175:7002
#    slots:[10923-16383] (5461 slots) master
# S: 2f3548559219875ff560830726cb6e9eeca50f79 172.16.21.175:7003
#    replicates b1e97ffddc507c6170e6e62d96ad886c992f8a76
# S: f723a411c0854216c16cacb0c6543164e9e9088a 172.16.21.175:7004
#    replicates 6b969f42305156a482fb9d6dc3821442793cd147
# S: 2455ff602100d4cbed7e3428aa866f18b8055ea4 172.16.21.175:7005
#    replicates 26386e84fadb3cfe417ce24a4472ec749035bfb6
# Can I set the above configuration? (type 'yes' to accept): yes
# >>> Nodes configuration updated
# >>> Assign a different config epoch to each node
# >>> Sending CLUSTER MEET messages to join the cluster
# Waiting for the cluster to join
# ....
# >>> Performing Cluster Check (using node 172.16.21.175:7000)
# M: 6b969f42305156a482fb9d6dc3821442793cd147 172.16.21.175:7000
#    slots:[0-5460] (5461 slots) master
#    1 additional replica(s)
# S: 2f3548559219875ff560830726cb6e9eeca50f79 172.16.21.175:7003
#    slots: (0 slots) slave
#    replicates b1e97ffddc507c6170e6e62d96ad886c992f8a76
# M: b1e97ffddc507c6170e6e62d96ad886c992f8a76 172.16.21.175:7002
#    slots:[10923-16383] (5461 slots) master
#    1 additional replica(s)
# S: 2455ff602100d4cbed7e3428aa866f18b8055ea4 172.16.21.175:7005
#    slots: (0 slots) slave
#    replicates 26386e84fadb3cfe417ce24a4472ec749035bfb6
# S: f723a411c0854216c16cacb0c6543164e9e9088a 172.16.21.175:7004
#    slots: (0 slots) slave
#    replicates 6b969f42305156a482fb9d6dc3821442793cd147
# M: 26386e84fadb3cfe417ce24a4472ec749035bfb6 172.16.21.175:7001
#    slots:[5461-10922] (5462 slots) master
#    1 additional replica(s)
# [OK] All nodes agree about slots configuration.
# >>> Check for open slots...
# >>> Check slots coverage...
# [OK] All 16384 slots covered.

# 模拟7000端口挂掉
ps -ef | grep redis
# root      4442  6032  0 13:15 pts/0    00:00:00 grep --color=auto redis
# root     16938     1  0 13:09 ?        00:00:00 bin/redis-server 172.16.21.175:7000 [cluster]
# root     16940     1  0 13:09 ?        00:00:00 bin/redis-server 172.16.21.175:7001 [cluster]
# root     16942     1  0 13:09 ?        00:00:00 bin/redis-server 172.16.21.175:7002 [cluster]
# root     16947     1  0 13:09 ?        00:00:00 bin/redis-server 172.16.21.175:7003 [cluster]
# root     16955     1  0 13:09 ?        00:00:00 bin/redis-server 172.16.21.175:7004 [cluster]
# root     17038     1  0 13:09 ?        00:00:00 bin/redis-server 172.16.21.175:7005 [cluster]
# [root@localhost redis-5.0.8]# kill -9 16938
# [root@localhost redis-5.0.8]# bin/redis-cli -c -h 172.16.21.175 -p 7001
# 172.16.21.175:7001> cluster nodes
# 2f3548559219875ff560830726cb6e9eeca50f79 172.16.21.175:7003@17003 slave b1e97ffddc507c6170e6e62d96ad886c992f8a76 0 1589346962000 4 connected
# 6b969f42305156a482fb9d6dc3821442793cd147 172.16.21.175:7000@17000 master,fail - 1589346941458 1589346939000 1 disconnected
# 2455ff602100d4cbed7e3428aa866f18b8055ea4 172.16.21.175:7005@17005 slave 26386e84fadb3cfe417ce24a4472ec749035bfb6 0 1589346963759 6 connected
# f723a411c0854216c16cacb0c6543164e9e9088a 172.16.21.175:7004@17004 master - 0 1589346961745 7 connected 0-5460
# 26386e84fadb3cfe417ce24a4472ec749035bfb6 172.16.21.175:7001@17001 myself,master - 0 1589346959000 2 connected 5461-10922
# b1e97ffddc507c6170e6e62d96ad886c992f8a76 172.16.21.175:7002@17002 master - 0 1589346962753 3 connected 10923-16383
bin/redis-server conf/redis_7000.conf
# [root@localhost redis-5.0.8]# bin/redis-cli -c -h 172.16.21.175 -p 7001
# 172.16.21.175:7001> cluster nodes
# 2f3548559219875ff560830726cb6e9eeca50f79 172.16.21.175:7003@17003 slave b1e97ffddc507c6170e6e62d96ad886c992f8a76 0 1589347184667 4 connected
# 6b969f42305156a482fb9d6dc3821442793cd147 172.16.21.175:7000@17000 slave f723a411c0854216c16cacb0c6543164e9e9088a 0 1589347185677 7 connected
# 2455ff602100d4cbed7e3428aa866f18b8055ea4 172.16.21.175:7005@17005 slave 26386e84fadb3cfe417ce24a4472ec749035bfb6 0 1589347187000 6 connected
# f723a411c0854216c16cacb0c6543164e9e9088a 172.16.21.175:7004@17004 master - 0 1589347185000 7 connected 0-5460
# 26386e84fadb3cfe417ce24a4472ec749035bfb6 172.16.21.175:7001@17001 myself,master - 0 1589347184000 2 connected 5461-10922
# b1e97ffddc507c6170e6e62d96ad886c992f8a76 172.16.21.175:7002@17002 master - 0 1589347187694 3 connected 10923-16383