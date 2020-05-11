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

# 分布式
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