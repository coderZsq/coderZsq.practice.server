# 查询命令
$ ls
# anaconda-ks.cfg  initial-setup-ks.cfg  logs  nohup.out  store
$ ls /
# 1  bin  boot  data  dev  etc  ftpfile  home  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
$ ls /root/
# anaconda-ks.cfg  initial-setup-ks.cfg  logs  nohup.out  store

# 显示详细信息 -list
$ ls -l /
# total 28
# -rw-r--r--.   1 root    root       0 Aug 10  2017 1
# lrwxrwxrwx.   1 root    root       7 Aug 16  2018 bin -> usr/bin
# dr-xr-xr-x.   5 root    root    4096 Apr 22 11:41 boot
# drwxr-xr-x.   3 root    root      16 Jun 10 14:24 data
# drwxr-xr-x.  21 root    root    3260 Sep 28 18:03 dev
# drwxr-xr-x. 141 root    root    8192 Sep 28 18:03 etc
# drwx------.   3 ftpuser ftpuser   78 Nov 14  2018 ftpfile
# drwxr-xr-x.   3 root    root      23 Apr 11  2018 home
# lrwxrwxrwx.   1 root    root       7 Aug 16  2018 lib -> usr/lib
# lrwxrwxrwx.   1 root    root       9 Aug 16  2018 lib64 -> usr/lib64
# drwxr-xr-x.   3 root    root      17 Apr 22 11:39 media
# drwxr-xr-x.   2 root    root       6 Apr 11  2018 mnt
# drwxr-xr-x.   3 root    root      16 Apr 11  2018 opt
# dr-xr-xr-x. 270 root    root       0 Jul 31 13:17 proc
# dr-xr-x---.  14 root    root    4096 Aug  5 11:37 root
# drwxr-xr-x.  39 root    root    1260 Sep 29 13:22 run
# lrwxrwxrwx.   1 root    root       8 Aug 16  2018 sbin -> usr/sbin
# drwxr-xr-x.   2 root    root       6 Apr 11  2018 srv
# dr-xr-xr-x.  13 root    root       0 Jul 31 13:18 sys
# drwxrwxrwt.  14 root    root    4096 Sep 29 13:23 tmp
# drwxr-xr-x.  14 root    root     167 Jun 28 06:24 usr
# drwxr-xr-x.  22 root    root    4096 Nov 14  2018 var

# 显示所有文件包含隐藏 -all
$ ls -a / 
# .  ..  1  .bash_history  bin  boot  data  dev  etc  ftpfile  home  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var

$ ls -a
# .   anaconda-ks.cfg  .bash_logout   .bashrc  .config  .dbshell  initial-setup-ks.cfg  logs  .mongorc.js  .oracle_jre_usage  .pki               .rnd  .ssh   .tcshrc
# ..  .bash_history    .bash_profile  .cache   .cshrc   .dbus     .ivy2                 .m2   nohup.out    .parallels         .rediscli_history  .sbt  store  .viminfo
$ ls -a -l
# total 76
# dr-xr-x---. 14 root root  4096 Aug  5 11:37 .
# dr-xr-xr-x. 19 root root   281 Jun 10 14:24 ..
# -rw-------.  1 root root  1834 Aug 10  2017 anaconda-ks.cfg
# -rw-------.  1 root root 18620 Sep 15 16:38 .bash_history
# -rw-r--r--.  1 root root    18 Dec 29  2013 .bash_logout
# -rw-r--r--.  1 root root   176 Dec 29  2013 .bash_profile
# -rw-r--r--.  1 root root   176 Dec 29  2013 .bashrc
# drwx------.  4 root root    31 Aug 10  2017 .cache
# drwx------.  5 root root    43 Aug 16  2018 .config
# -rw-r--r--.  1 root root   100 Dec 29  2013 .cshrc
# -rw-------.  1 root root  1093 Jun 20 12:42 .dbshell
# drwx------.  3 root root    25 Aug 10  2017 .dbus
# -rw-r--r--.  1 root root  1862 Aug 10  2017 initial-setup-ks.cfg
# drwxr-xr-x.  3 root root    63 Jul 17 13:26 .ivy2
# drwxr-xr-x.  4 root root    45 Jun 22 13:30 logs
# drwxr-xr-x.  3 root root    24 Jun 22 11:15 .m2
# -rw-------.  1 root root     0 Jun 10 16:03 .mongorc.js
# -rw-------.  1 root root  1542 Aug  5 11:37 nohup.out
# drwxr-x---.  2 root root    40 Nov 14  2018 .oracle_jre_usage
# drwxr-xr-x.  2 root root    25 Apr 22 11:40 .parallels
# drwxr-----.  3 root root    19 Jul 17 12:06 .pki
# -rw-------.  1 root root   794 Jul 24 14:55 .rediscli_history
# -rw-------.  1 root root  1024 Aug 10  2017 .rnd
# drwxr-xr-x.  5 root root    47 Jul 17 12:31 .sbt
# drwx------.  2 root root    29 Feb  2  2018 .ssh
# drwxr-xr-x.  6 root root   113 Jul 31 13:19 store
# -rw-r--r--.  1 root root   129 Dec 29  2013 .tcshrc
# -rw-------.  1 root root  6364 Jun  1 13:28 .viminfo

# 人性化显示 -human
$ ls -a -l -h
$ ls -alh
# total 76K
# dr-xr-x---. 14 root root 4.0K Aug  5 11:37 .
# dr-xr-xr-x. 19 root root  281 Jun 10 14:24 ..
# -rw-------.  1 root root 1.8K Aug 10  2017 anaconda-ks.cfg
# -rw-------.  1 root root  19K Sep 15 16:38 .bash_history
# -rw-r--r--.  1 root root   18 Dec 29  2013 .bash_logout
# -rw-r--r--.  1 root root  176 Dec 29  2013 .bash_profile
# -rw-r--r--.  1 root root  176 Dec 29  2013 .bashrc
# drwx------.  4 root root   31 Aug 10  2017 .cache
# drwx------.  5 root root   43 Aug 16  2018 .config
# -rw-r--r--.  1 root root  100 Dec 29  2013 .cshrc
# -rw-------.  1 root root 1.1K Jun 20 12:42 .dbshell
# drwx------.  3 root root   25 Aug 10  2017 .dbus
# -rw-r--r--.  1 root root 1.9K Aug 10  2017 initial-setup-ks.cfg
# drwxr-xr-x.  3 root root   63 Jul 17 13:26 .ivy2
# drwxr-xr-x.  4 root root   45 Jun 22 13:30 logs
# drwxr-xr-x.  3 root root   24 Jun 22 11:15 .m2
# -rw-------.  1 root root    0 Jun 10 16:03 .mongorc.js
# -rw-------.  1 root root 1.6K Aug  5 11:37 nohup.out
# drwxr-x---.  2 root root   40 Nov 14  2018 .oracle_jre_usage
# drwxr-xr-x.  2 root root   25 Apr 22 11:40 .parallels
# drwxr-----.  3 root root   19 Jul 17 12:06 .pki
# -rw-------.  1 root root  794 Jul 24 14:55 .rediscli_history
# -rw-------.  1 root root 1.0K Aug 10  2017 .rnd
# drwxr-xr-x.  5 root root   47 Jul 17 12:31 .sbt
# drwx------.  2 root root   29 Feb  2  2018 .ssh
# drwxr-xr-x.  6 root root  113 Jul 31 13:19 store
# -rw-r--r--.  1 root root  129 Dec 29  2013 .tcshrc
# -rw-------.  1 root root 6.3K Jun  1 13:28 .viminfo

# 文件处理命令 
# 查询所在目录位置 print working directory
$ pwd
# /root

# 创建文件
$ touch file.txt

# 建立目录
$ mkdir directory

$ ll
# total 12
# -rw-------. 1 root root 1834 Aug 10  2017 anaconda-ks.cfg
# drwxr-xr-x. 2 root root    6 Sep 29 13:36 directory
# -rw-r--r--. 1 root root    0 Sep 29 13:36 file.txt
# -rw-r--r--. 1 root root 1862 Aug 10  2017 initial-setup-ks.cfg
# drwxr-xr-x. 4 root root   45 Jun 22 13:30 logs
# -rw-------. 1 root root 1542 Aug  5 11:37 nohup.out
# drwxr-xr-x. 6 root root  113 Jul 31 13:19 store

# 批量创建目录 -p
$ mkdir -p aaa/bbb/ccc

# 切换所在目录 change directory
$ cd aaa
$ pwd
# /root/aaa
$ cd /root/aaa/bbb/
$ pwd
# /root/aaa/bbb

# 简化操作
$ cd ~ # 进入当前用户的家目录
$ cd   # 同上
$ cd - # 进入上次目录
$ cd .. # 机内上一级目录
$ cd . # 进入当前目录

# 删除空目录 remove empty directory
$ rmdir

# 删除文件或目录 remove
$ rm file.txt
# rm: remove regular empty file ‘file.txt’? y

# 强制删除 -force
$ rm -f file.txt

# 删除文件夹 -recursion
$ rm -rf directory/

# 复制命令 copy
$ cp anaconda-ks.cfg  /
$ ls /
# 1  anaconda-ks.cfg  bin  boot  data  dev  etc  ftpfile  home  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var

# 复制文件并重命名
$ cp anaconda-ks.cfg /copy.cfg
$ ls /
# 1  anaconda-ks.cfg  bin  boot  copy.cfg  data  dev  etc  ftpfile  home  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var

# 复制目录 -r
$ cp -r aaa/ /test
$ ls /
# 1  anaconda-ks.cfg  bin  boot  copy.cfg  data  dev  etc  ftpfile  home  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  test  tmp  usr  var

# 剪切或改名命令
$ mv /test/ /root/aaa/bbb/ccc/
$ ls /root/aaa/bbb/ccc/
# test

# 文件搜索命令
# 命令搜索命令 PATH环境变量搜索
$ whereis java
# java: /usr/bin/java /usr/lib/java /etc/java /usr/share/java /usr/java/jdk1.8.0_191-amd64/bin/java /usr/share/man/man1/java.1
$ whereis ls
# ls: /usr/bin/ls /usr/share/man/man1/ls.1.gz /usr/share/man/man1p/ls.1p.gz
$ whereis cd
# cd: /usr/bin/cd /usr/share/man/man1/cd.1.gz /usr/share/man/man1p/cd.1p.gz

$ which cd
# /usr/bin/cd

# 文件搜索命令 默认当前目录子目录查找
$ find -name file.txt
# ./file.txt

$ cd /
$ find -name file.txt
./root/file.txt

# 指定目录查找
$ find /root -name file.txt
# /root/file.txt

# 忽略大小写 -iname
$ find /root -iname FILE.txt
# /root/file.txt

# atime 文件访问时间
# ctime 改变文件属性时间
# mtime 修改文件时间
# 查找10天前修改的文件
$ find /var/log -mtime +10 #
# 查找10天当天修改的文件
$ find /var/log -mtime 10
# 查找10天内修改的文件
$ find /var/log -mtime -10

# 查找文件大小是25KB的文件, 注意k是小写的
# -25k 小于25KB的文件
# 25k 等于25KB的文件
# +25k 大于25KB的文件
$ find . -size 25k

# 查找文件大小是25MB的文件, 注意M是大写的
$ find . -size 25M

# 查找/etc/目录下, 大于20KB并且小于50KB的文件
# -a and 逻辑与, 两个条件都满足
# -o or 逻辑或, 两个条件满足一个即可
$ find /etc -size +20k -a -size -50k

# 查找/etc/目录下, 大于20KB并且小于50KB的文件, 并显示详细信息
# -exec {} \; 这是固定格式
$ find /etc -size +20k -a -size -50k -exec ls -lh {} \;

# 字符串搜索命令
$ grep core anaconda-ks.cfg
# @core

# 显示行号 -number 
$ grep -n core anaconda-ks.cfg
# 38:@core

# 排除指定字符串 -v
$ grep -n core anaconda-ks.cfg | grep -v @