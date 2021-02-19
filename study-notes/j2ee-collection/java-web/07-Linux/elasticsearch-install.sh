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