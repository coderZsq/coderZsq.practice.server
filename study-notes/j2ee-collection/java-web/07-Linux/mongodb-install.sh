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