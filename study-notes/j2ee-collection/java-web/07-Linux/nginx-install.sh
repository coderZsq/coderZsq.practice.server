# 下载所需要的依赖库文件
yum install pcre -y
yum install pcre-devel -y
yum install zlib -y
yum install zlib-devel -y

# 进行安装
tar -zxvf nginx-1.18.0.tar.gz -C /usr/local/src

# 进入目录
cd /usr/local/src/nginx-1.18.0

# 编译
./configure --prefix=/usr/local/nginx

# 安装
make && make install

# 启动nginx
/usr/local/nginx/sbin/nginx

# 停止nginx
/usr/local/nginx/sbin/nginx -s stop

# 修改配置文件
cd conf/
vi nginx.conf

# 配置文件修改进程数
worker_processes  2;

# 重新加载配置
sbin/nginx -s reload
# [root@localhost nginx]# ps -ef | grep nginx
# nobody   24716 29992  0 11:39 ?        00:00:00 nginx: worker process
# nobody   24717 29992  0 11:39 ?        00:00:00 nginx: worker process
# root     26022  3561  0 11:39 pts/0    00:00:00 grep --color=auto nginx
# root     29992     1  0 11:10 ?        00:00:00 nginx: master process /usr/local/nginx/sbin/nginx

# 日志分片
cd /usr/local/nginx

# 创建日志文件夹
mkdir datalogs

# 生成时间命令
/bin/date -d yesterday +%Y%m%d%H%M

# 编辑脚本
cd sbin 
vi backuplog.sh
# #!/bin/sh

# BASE_DIR=/usr/local/nginx
# BASE_FILE_NAME=access.log

# CURRENT_PATH=$BASE_DIR/logs
# BAK_PATH=$BASE_DIR/datalogs

# CURRENT_FILE=$CURRENT_PATH/$BASE_FILE_NAME
# BAK_TIME=`/bin/date -d yesterday +%Y%m%d%H%M`
# BAK_FILE=$BAK_PATH/$BAK_TIME-$BASE_FILE_NAME
# mv $CURRENT_FILE $BAK_FILE

# $BASE_DIR/sbin/nginx -s reopen

# 添加可执行权限
chmod +x backuplog.sh

# 执行脚本
sbin/backuplog.sh

# 添加定时任务
crontab -e
# */1 * * * * sh /usr/local/nginx/sbin/backuplog.sh

# 查看定时任务
crontab -l

# 删除定时任务
crontab -r

# 反向代理
# location / {
#   proxy_pass http://172.16.21.175:8080;
# }
sbin/nginx -s reload

events {
  # 每个Worker进程的一个请求连接数
  worker_connections 10240;
}

# 配置上游服务器
upstream crm {
  server 47.101.146.105:8080;
  server 172.19.189.121:8080;
}

server {
  listen 80;
  server_name localhost;

  location / {
    proxy_pass http://crm;
  }
}

# 校验语法是否正常
sbin/nginx -t
# nginx: the configuration file /usr/local/nginx/conf/nginx.conf syntax is ok
# nginx: configuration file /usr/local/nginx/conf/nginx.conf test is successful

# 重启服务
sbin/nginx -s reload

# 负载均衡
upstream crm {
  ip_hash;
  server 47.101.146.105:8080 weight 1;
  server 172.19.189.121:8080 weight 1;
}

# 获取客户端真实地址
location / {
  proxy_set_header x-real-ip $remote_addr;
  proxy_pass http://crm;
}

# 动静分离
# 静态资源访问地址
location ~ .*\.(gif|jpg|jpeg|png|bmp|swf|css|js)$ {
  root /datas/crm/static/;
}
# 动态请求访问地址
location ~ .* {
  proxy_pass http://localhost:8080;
}

# 开启gzip压缩
gzip on;
# 文件达到多大开始压缩
gzip_min_length 1k;
# 压缩基本, 1-9级别越高, 越消耗cpu, 默认值为1
gzip_comp_level 2;
# 压缩文件的类型, 对于jpg, png图片压缩效率不高
gzip_types text/plain application/javascript application/x-javascript text/css application/xml text/javascript;
# 根据客户端的HTTP头来判断, 是否需要压缩
gzip_vary on;

# 热升级流程
# 需要安装https支持的openssl
yum install -y openssl openssl-devel
# 给模块添加https支持
./configure --prefix=/usr/local/nginx --with-http_stub_status_module --with-http_ssl_module
# 执行make, 注意, 不需要执行make install
make
# 把编译后的可执行程序替换nginx
cd /usr/local/nginx/sbin
cp nginx nginx.old
cp -f /usr/local/src/nginx-1.18.0/objs/nginx ./
# 向Master发送USR2信号
ps -ef | grep nginx
# root      2031     1  0 May15 ?        00:00:00 nginx: master process /usr/local/nginx/sbin/nginx
# nobody    6915  2031  0 14:00 ?        00:00:00 nginx: worker process
# root      9630  6870  0 15:33 pts/1    00:00:00 grep --color=auto nginx
kill -s SIGUSR2 2031
ps -ef | grep nginx
# root      2031     1  0 May15 ?        00:00:00 nginx: master process /usr/local/nginx/sbin/nginx
# nobody    6915  2031  0 14:00 ?        00:00:00 nginx: worker process
# root      9631  2031  0 15:33 ?        00:00:00 nginx: master process /usr/local/nginx/sbin/nginx
# nobody    9632  9631  0 15:33 ?        00:00:00 nginx: worker process
# root      9636  6870  0 15:34 pts/1    00:00:00 grep --color=auto nginx
kill -QUIT 2031

# SSL证书
# 免费申请
# 添加依赖库文件
yum install epel-release -y
# 安装用于免费申请证书的工具包 
yum install python2-certbot-nginx
# 开始申请免费证书
certbot --nginx --nginx-server-root=/usr/local/nginx/conf -d 域名
# 配置443模块
server {
  listen 443 ssl;
  server_name 域名;
  ssl_certificate /etc/letsencrypt/live/域名/fullchain.pem;
  ssl_certificate_key /etc/letsencrypt/live/域名/privkey.pem;
  include /etc/letsencrypt/options-ssl-nginx.conf;
  ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem;
  location / {
    root html;
    index index.html index.htm;
  }
}

# 自己生成
# 创建服务器私钥, 命令会让你输入一个口令
openssl genrsa -des3 -out server.key 1024
# 创建签名请求的证书 (CSR)
openssl req -new -key server.key -out server.csr
# 在加载SSL支持的Nginx并使用上述私钥时除去必须的口令
openssl rsa -in server.key -out server_nopass.key
# 最后标记证书使用上述私钥和CSR
openssl x509 -req -days 365 -in server.csr -signkey server_nopass.key -out server.crt
# 配置ssl支持
server {
  listen 443 ssl;
  server_name 127.0.0.1;
  ssl_certificate /usr/local/nginx/sbin/server.crt;
  ssl_certificate_key /usr/local/nginx/sbin/server_nopass.key;
  location / {
    proxy_pass http://crm;
  }
}
# 重启服务
sbin/nginx -s reload

# 跨域请求
location / {
  add_header Access-Control-Allow-Origin *;
  add_header Access-Control-Allow-Methods 'GET, POST, OPTIONS';
  add_header Access-Control-Allow-Headers '*';
  ...
}

# 开启缓存 
http {
  ...
  proxy_cache_path /datas/cache levels=1:2 keys_zone=one:10m;
  ...
  server {
    location / {
      proxy_cache one;
      proxy_cache_valid 200 1m;
      ...
    }
  }
}

# 请求防盗链
location / {
  valid_referers none blocked server_names
                 域名;
  if ($valid_referer) {
    return 403;
  }
  ...
}

# 限流方案
# 限制并发连接数
http {
  ...
  limit_conn_zone $binary_remote_addr zone=addr:10m;
  ...
  server {
    location / {
      limit_conn_status 500;
      limit_conn_log_level error;
      limit_rate 50;
      limit_conn addr 1;
    }
  }
}

# 限制访问频率
http {
  ...
  limit_req_zone $binary_remote_addr zone=one:10m rate=1r/s;
  ...
  server {
    location / {
      limit_req_zone=one burst=5;
    }
  }
}

# http重定向https
# 重新定位到443服务器
http {
  ...
  rewrite \.* https://47.101.146.105:443/ break;
  ...
  server {
    location / {
      ...
      rewrite \.* https://47.101.146.105:443/ break;
      ...
    }
  }
}

# 高可用环境搭建
# 1. 安装keepalived依赖的包
yum install gcc openssl-devel libnl3-devel popt-devel iptables-devel libnfnetlink-devel -y

# 2. 编译安装keepalived
tar -zxvf keepalived-2.0.20.tar.gz -C /usr/local/src
cd /usr/local/src/keepalived-2.0.20/
./configure --prefix=/usr/local/keepalived
make && make install

# 3. 将keepalived安装成Linux系统服务
mkdir /etc/keepalived
cp /usr/local/keepalived/etc/keepalived/keepalived.conf /etc/keepalived/
cp /usr/local/keepalived/sbin/keepalived /usr/sbin/
cp /usr/local/keepalived/etc/sysconfig/keepalived /etc/sysconfig/
cp /usr/local/keepalived/sbin/keepalived /etc/init.d/

# 4.编写nginx检测脚本
vi /etc/keepalived/nginx_check.sh
# #!/bin/bash
# A=`ps -C nginx -- no-header |wc -l`
# if [ $A -eq 0 ]; then
#   /usr/local/nginx/sbin/nginx
#   sleep 2
#   if [ `ps -C nginx --no-header |wc -l` -eq 0 ];then
#     killall keepalived
#   fi
# fi

# 5. 添加执行权限
chmod +x /etc/keepalived/nginx_check.sh

# 6. 修改keepalived的Master配置文件:
vi /etc/keepalived/keepalived.conf
# :1,$d

# !Configuration File for keepalived
# global_defs {
#   router_id coderZsq #路由器标志
# }
# #集群资源监控, 组合track_script进行
# vrrp_script check_haproxy {
#   script "/etc/keepalived/nginx_check.sh" #检测 nginx 状态的脚本路径
#   interval 2 #检测时间间隔
#   weight -20 #条件成立 权重减20
# }
# vrrp_instance PROXY {
#   #设置当前主机为主节点, 如果是备用节点, 则设置为BACKUP
#   state MASTER
#   #指定HA监测网络接口, 可以用ifconfig查看来决定设置哪一个 ens32 / eth0
#   interface eth0
#   #虚拟路由标识, 同一个VRRP实例要使用同一个标识, 主备机
#   virtual_router_id 80
#   #因为当前环境中VRRP组播有问题, 改为使用单播发送VRRP报文 如果VRRP组播没问题, 以下这块对的内容可以注释掉
#   #这个地方需要关注, 之前未做此设置, 结果主备节点互相不能发现, 因此主备节点都升级成了MASTER, 并且绑定了VIP
#   #主节点时, 内容为:
#   #unicast_src_ip xxx.xxx.xxx.xxx
#   #设置优先级, 确保主节点的优先级高过备用节点
#   priority 100
#   #用于设定主备节点间同步检查时间间隔
#   advert_int 2
#   #设置主备节点间的通信验证类型及密码, 同一个VRRP实例中需一致
#   authentication {
#     auth_type PASS
#     auth_pass coderZsq
#   }
#   #集群资源监控, 组合vrrp_script进行
#   track_script {
#     check_haproxy
#   }
#   #设置虚拟IP地址, 当keepalived状态切换为MASTER时, 此IP会自动添加到系统中
#   #当状态切换到BACKUP时, 此IP会自动从系统中删除
#   #可以通过命令 ip addr查看切换后的状态
#   virtual_ipaddress {
#     172.19.189.100 #虚拟ip配置完之后就用它访问 需要同意网段
#   }
# }

# 7. 启动keepalived进程
systemctl start keepalived
# [root@izuf69ibpdra8ui5a6edjtz keepalived]# ps -ef |grep keep
# root       504     1  0 Apr08 ttyS0    00:00:00 /sbin/agetty --keep-baud 115200,38400,9600 ttyS0 vt220
# root     22944     1  0 13:53 ?        00:00:00 /usr/local/keepalived/sbin/keepalived -D
# root     22945 22944  0 13:53 ?        00:00:00 /usr/local/keepalived/sbin/keepalived -D
# root     22951 22945  0 13:53 ?        00:00:00 /bin/bash /etc/keepalived/nginx_check.sh
# root     22957 16142  0 13:53 pts/0    00:00:00 grep --color=auto keep

# [root@izuf69ibpdra8ui5a6edjtz keepalived]# systemctl status keepalived
# ● keepalived.service - LVS and VRRP High Availability Monitor
#    Loaded: loaded (/usr/lib/systemd/system/keepalived.service; disabled; vendor preset: disabled)
#    Active: active (running) since Thu 2020-05-21 13:53:38 CST; 15s ago
#   Process: 22943 ExecStart=/usr/local/keepalived/sbin/keepalived $KEEPALIVED_OPTIONS (code=exited, status=0/SUCCESS)
#  Main PID: 22944 (keepalived)
#    CGroup: /system.slice/keepalived.service
#            ├─22944 /usr/local/keepalived/sbin/keepalived -D
#            └─22945 /usr/local/keepalived/sbin/keepalived -D

# May 21 13:53:44 izuf69ibpdra8ui5a6edjtz Keepalived_vrrp[22945]: Sending gratuitous ARP on eth0 for 172.19.189.100
# May 21 13:53:44 izuf69ibpdra8ui5a6edjtz Keepalived_vrrp[22945]: Sending gratuitous ARP on eth0 for 172.19.189.100
# May 21 13:53:48 izuf69ibpdra8ui5a6edjtz Keepalived_vrrp[22945]: Track script check_haproxy is already running, expect idle - skipping run
# May 21 13:53:49 izuf69ibpdra8ui5a6edjtz Keepalived_vrrp[22945]: Sending gratuitous ARP on eth0 for 172.19.189.100
# May 21 13:53:49 izuf69ibpdra8ui5a6edjtz Keepalived_vrrp[22945]: (PROXY) Sending/queueing gratuitous ARPs on eth0 for 172.19.189.100
# May 21 13:53:49 izuf69ibpdra8ui5a6edjtz Keepalived_vrrp[22945]: Sending gratuitous ARP on eth0 for 172.19.189.100
# May 21 13:53:49 izuf69ibpdra8ui5a6edjtz Keepalived_vrrp[22945]: Sending gratuitous ARP on eth0 for 172.19.189.100
# May 21 13:53:49 izuf69ibpdra8ui5a6edjtz Keepalived_vrrp[22945]: Sending gratuitous ARP on eth0 for 172.19.189.100
# May 21 13:53:49 izuf69ibpdra8ui5a6edjtz Keepalived_vrrp[22945]: Sending gratuitous ARP on eth0 for 172.19.189.100
# May 21 13:53:52 izuf69ibpdra8ui5a6edjtz Keepalived_vrrp[22945]: Track script check_haproxy is already running, expect idle - skipping run

# [root@izuf69ibpdra8ui5a6edjtz keepalived]# ip addr
# 1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN qlen 1
#     link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
#     inet 127.0.0.1/8 scope host lo
#        valid_lft forever preferred_lft forever
# 2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP qlen 1000
#     link/ether 00:16:3e:0a:ff:87 brd ff:ff:ff:ff:ff:ff
#     inet 172.19.189.121/20 brd 172.19.191.255 scope global eth0
#        valid_lft forever preferred_lft forever
#     inet 172.19.189.100/32 scope global eth0
#        valid_lft forever preferred_lft forever

# 6. 修改keepalived的BACKUP配置文件:
# !Configuration File for keepalived
# global_defs {
#   router_id coderZsq #路由器标志
# }
# #集群资源监控, 组合track_script进行
# vrrp_script check_haproxy {
#   script "/etc/keepalived/nginx_check.sh" #检测 nginx 状态的脚本路径
#   interval 2 #检测时间间隔
#   weight -20 #条件成立 权重减20
# }
# vrrp_instance PROXY {
#   #设置当前主机为主节点, 如果是备用节点, 则设置为BACKUP
#   state BACKUP
#   #指定HA监测网络接口, 可以用ifconfig查看来决定设置哪一个 ens32 / eth0
#   interface eth0
#   #虚拟路由标识, 同一个VRRP实例要使用同一个标识, 主备机
#   virtual_router_id 80
#   #因为当前环境中VRRP组播有问题, 改为使用单播发送VRRP报文 如果VRRP组播没问题, 以下这块对的内容可以注释掉
#   #这个地方需要关注, 之前未做此设置, 结果主备节点互相不能发现, 因此主备节点都升级成了MASTER, 并且绑定了VIP
#   #主节点时, 内容为:
#   #unicast_src_ip xxx.xxx.xxx.xxx
#   #设置优先级, 确保主节点的优先级高过备用节点
#   priority 90
#   #用于设定主备节点间同步检查时间间隔
#   advert_int 2
#   #设置主备节点间的通信验证类型及密码, 同一个VRRP实例中需一致
#   authentication {
#     auth_type PASS
#     auth_pass coderZsq
#   }
#   #集群资源监控, 组合vrrp_script进行
#   track_script {
#     check_haproxy
#   }
#   #设置虚拟IP地址, 当keepalived状态切换为MASTER时, 此IP会自动添加到系统中
#   #当状态切换到BACKUP时, 此IP会自动从系统中删除
#   #可以通过命令 ip addr查看切换后的状态
#   virtual_ipaddress {
#     172.19.189.100 #虚拟ip配置完之后就用它访问 需要同意网段
#   }
# }