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

