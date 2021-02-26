# 安装
$ rpm -ivh elasticsearch-6.5.4.rpm
### NOT starting on installation, please execute the following statements to configure elasticsearch service to start automatically using systemd
 sudo systemctl daemon-reload
 sudo systemctl enable elasticsearch.service
### You can start elasticsearch service by executing
 sudo systemctl start elasticsearch.service
Created elasticsearch keystore in /etc/elasticsearch

$ cd /etc/elasticsearch/
elasticsearch.keystore  elasticsearch.yml  jvm.options  log4j2.properties  role_mapping.yml  roles.yml  users  users_roles

# 查看安装文件的位置(/usr/share/elasticsearch)
$ rpm -ql elasticsearch

$ cd /usr/share/elasticsearch
bin  lib  LICENSE.txt  modules  NOTICE.txt  plugins  README.textile

# es配置JAVA_HOME路径(开始的地方)
$ cd /usr/share/elasticsearch/bin/
$ vi elasticsearch-env
export JAVA_HOME=/usr/local/jdk1.8.0_161

# 启动
$ systemctl start elasticsearch
$ systemctl status elasticsearch

# 查看端口
$ netstat -ntlp
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
tcp        0      0 127.0.0.1:9200          0.0.0.0:*               LISTEN      748/java
tcp        0      0 127.0.0.1:9300          0.0.0.0:*               LISTEN      748/java
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      1218/sshd
tcp6       0      0 :::33060                :::*                    LISTEN      1004/mysqld
tcp6       0      0 :::3306                 :::*                    LISTEN      1004/mysqld

$ cd /etc/elasticsearch/
elasticsearch.keystore  elasticsearch.yml  jvm.options  log4j2.properties  role_mapping.yml  roles.yml  users  users_roles

$ vi elasticsearch.yml
network.host: 0.0.0.0

$ systemctl restart elasticsearch

$ netstat -ntlp
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name
tcp        0      0 0.0.0.0:9200            0.0.0.0:*               LISTEN      1393/java
tcp        0      0 0.0.0.0:9300            0.0.0.0:*               LISTEN      1393/java
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      1218/sshd
tcp6       0      0 :::33060                :::*                    LISTEN      1004/mysqld
tcp6       0      0 :::3306                 :::*                    LISTEN      1004/mysqld

http://47.101.146.105:9200/

# 配置参数
$ cd /etc/elasticsearch/
$ vi elasticsearch.yml
cluster.name: my-es # 集群名字
node.name: lab001 # 节点名字

# SQL
$ cd /usr/share/elasticsearch/bin
$ ./elasticsearch-sql-cli

# 集群环境的安装
# 系统参数配置
$ vi /etc/sysctl.conf
vm.max_map_count=655360

$ vi /etc/security/limits.conf
* soft nofile 65536
* hard nofile 65536

# 创建es用户
$ useradd es
$ passwd es

# 给es对应的权限
$ chown -R es:es /usr/local/software

# 修改系统参数配置
$ cd /usr/local/software/
$ su es
$ tar -zxvf elasticsearch-6.5.4.tar.gz
$ cp -r elasticsearch-analysis-ik-6.5.4/ elasticsearch-6.5.4/plugins/
$ cd elasticsearch-6.5.4/
$ vi config/elasticsearch.yml
# 集群名称
cluster.name: my-es
# 节点名称
node.name: node-1
# 允许访问的网卡地址
network.host: 0.0.0.0
# 设置集群中节点间通信的tcp端口
transport.tcp.port: 9300
# 集群中的节点列表
discovery.zen.ping.unicast.hosts: ["172.16.23.125:9300", "172.16.23.125:9301", "172.16.23.125:9002"]
# 选举master节点的情况: 必须要有2个以上的节点(大多数同意)
discovery.zen.minimum_master_nodes: 2
# 启动es
$ bin/elasticsearch -d
# 查看启动状态
$ tail -100f logs/my-es.log