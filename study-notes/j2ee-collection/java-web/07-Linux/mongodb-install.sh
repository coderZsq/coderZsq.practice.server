# 解压mongodb
tar -zxvf mongodb-linux-x86_64-rhel70-4.4.0-rc8.tgz -C /usr/local/

# 修改文件夹名称
cd /usr/local/
mv mongodb-linux-x86_64-rhel70-4.4.0-rc8/ mongodb-4.4

# 配置文件夹
cd mongodb-4.4
mkdir -p data/db
mkdir log
cd log
touch mongodb.log
cd ..
cd bin
vi mongodb.cfg
# dbpath=/usr/local/mongodb-4.4/data/db
# logpath=/usr/local/mongodb-4.4/log/mongodb.log
# port=27017
# fork=true
# auth=false
# logappend=true
# bind_ip=0.0.0.0

# 启动mongodb
./mongod -f /usr/local/mongodb-4.4/bin/mongodb.cfig

# 基本命令
# > show dbs
# admin   0.000GB
# config  0.000GB
# local   0.000GB

# 高可用
# 1. 复制集 master为主节点, rs1 rs2为从节点, 用于存放数据
# 创建目录结构
mkdir -p repset/data
cd repset/data/
mkdir master
mkdir rs1
mkdir rs2

# 用于存放节点运行日志
cd ..
mkdir log

# 执行启动命令
cd ../bin/
./mongod --port 27017 --dbpath=/usr/local/mongodb-4.4/repset/data/master --logpath=/usr/local/mongodb-4.4/repset/log/master.log --logappend --replSet=rs --bind_ip=0.0.0.0
./mongod --port 27018 --dbpath=/usr/local/mongodb-4.4/repset/data/rs1 --logpath=/usr/local/mongodb-4.4/repset/log/master.log --logappend --replSet=rs --bind_ip=0.0.0.0
./mongod --port 27019 --dbpath=/usr/local/mongodb-4.4/repset/data/rs2 --logpath=/usr/local/mongodb-4.4/repset/log/master.log --logappend --replSet=rs --bind_ip=0.0.0.0

# 复制集初始化
./mongo --port 27017
# 进入27017 ./mongo cli
config = {
  _id: "rs",
  members: [
    {_id: 0, host: "127.0.0.1:27017", "priority": 2},
    {_id: 1, host: "127.0.0.1:27018"},
    {_id: 2, host: "127.0.0.1:27019"}
  ]
}
rs.initiate(config)

rs.status()

# 2. 分片集
# 创建目录结构
mkdir shard
cd shard
mkdir cfg
mkdir data
mkdir log
cd cfg
touch config.cfg
touch route.cfg
touch shard1.cfg
touch shard2.cfg
touch shard3.cfg
vi config.cfg
# dbpath=/usr/local/mongodb-4.4/shard/data/config
# logpath=/usr/local/mongodb-4.4/shard/log/config.log
# port=27027
# logappend=true
# configsvr=true
# replSet=configRs
# bind_ip=0.0.0.0
vi route.cfg
# logpath=/usr/local/mongodb-4.4/shard/log/route.log
# logappend=true
# port=40000
# configdb=configRs/127.0.0.1:27027
# bind_ip=0.0.0.0
vi shard1.cfg
# dbpath=/usr/local/mongodb-4.4/shard/data/shard1
# logpath=/usr/local/mongodb-4.4/shard/log/shard1.log
# port=27017
# logappend=true
# shardsvr=true
# replSet=shardRs
# bind_ip=0.0.0.0
vi shard2.cfg
# dbpath=/usr/local/mongodb-4.4/shard/data/shard2
# logpath=/usr/local/mongodb-4.4/shard/log/shard2.log
# port=27018
# logappend=true
# shardsvr=true
# replSet=shardRs
# bind_ip=0.0.0.0
vi shard3.cfg
# dbpath=/usr/local/mongodb-4.4/shard/data/shard3
# logpath=/usr/local/mongodb-4.4/shard/log/shard3.log
# port=27019
# logappend=true
# shardsvr=true
# replSet=shardRs
# bind_ip=0.0.0.0
cd ../data
mkdir config
mkdir shard1
mkdir shard2
mkdir shard3

# 分片服务器启动
./mongod --config /usr/local/mongodb-4.4/shard/cfg/shard1.cfg
./mongod --config /usr/local/mongodb-4.4/shard/cfg/shard2.cfg
./mongod --config /usr/local/mongodb-4.4/shard/cfg/shard3.cfg

# 分片服务器复制集初始化
./mongo --port 27017

rs.initiate({
    _id: "shardRs",
  members: [
    {_id: 0, host: "127.0.0.1:27017", "priority": 2},
    {_id: 1, host: "127.0.0.1:27018"},
    {_id: 2, host: "127.0.0.1:27019"}
  ]
})

# 配置服务器启动
./mongod --config /usr/local/mongodb-4.4/shard/cfg/config.cfg

# 配置服务器初始化
./mongo --port 27027

rs.initiate({
  _id: "configRs",
  members: [
    {_id: 0, host: "127.0.0.1:27027", "priority": 2}
  ]
})

# 路由服务器启动
./mongos --config /usr/local/mongodb-4.4/shard/cfg/route.cfg

# 链接路由服务器
./mongo --port 40000

# 初始化并关联分片服务器 [注意后面的配置必须在admin路由中配置]
use admin
sh.addShard("shardRs/127.0.0.1:27017,127.0.0.1:27018,127.0.0.1:27019")

# 重置分片数据块大小
# [默认是64M, 这里测试简单弄小一点]
use config
db.settings.save({"_id": "chunksize", "value": 1})

# 准备数据, 尽可能多
use mongodemo
for (i = 1; i <= 100; i++) { db.users.insert({"id": 1, "name": "coderZsq" + i, age: i, money: i}) }
db.users.find()

# 对指定库指定集合开启分片功能支持
sh.enableSharding("mongodemo")
db.users.createIndex({"id": 1})
sh.shardCollection("mongodemo.users", {"id": 1})

# 分片情况查看
db.users.stats()
sh.status()
db.users.getShardDistribution()