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

# 主从复制
# 1. ========命令行========
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

# 2. =======配置========
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

# Sentinel
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