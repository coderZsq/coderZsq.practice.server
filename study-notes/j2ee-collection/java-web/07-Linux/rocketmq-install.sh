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
./mqshutdown nameserver

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