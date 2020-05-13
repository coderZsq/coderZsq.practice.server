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

# 连上集群
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

# 分配槽位
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