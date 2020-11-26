# 下载链接
http://www.mycat.org.cn/

# 解压tar包
$ tar -zxf Mycat-server-1.6.7.4-release-20200105164103-linux.tar.gz -C /usr/local

# 查看mycat目录结构
$ cd /usr/local/mycat/
$ ll
# total 12
# drwxr-xr-x. 2 root root  190 Nov 26 15:52 bin
# drwxrwxrwx. 2 root root    6 Oct 22  2019 catlet
# drwxrwxrwx. 4 root root 4096 Nov 26 15:52 conf
# drwxr-xr-x. 2 root root 4096 Nov 26 15:52 lib
# drwxrwxrwx. 2 root root    6 Jan  5  2020 logs
# -rwxrwxrwx. 1 root root  227 Jan  5  2020 version.txt

# 启动mycat
 $ ./bin/mycat  start
# Starting Mycat-server...