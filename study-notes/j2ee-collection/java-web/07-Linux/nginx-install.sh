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

