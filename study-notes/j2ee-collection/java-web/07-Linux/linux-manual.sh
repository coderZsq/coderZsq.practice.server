# ssh协议
$ ps -ef | grep ssh
  # 501  1069     1   0 21Sep20 ??         0:00.04 /usr/bin/ssh-agent -l
  # 501 62155 62099   0 12:33PM ttys000    0:00.15 ssh CentOS
  # 501 64427 64341   0  2:22PM ttys001    0:00.00 grep ssh

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

# 递归显示
$ ls -R store/

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

# 命名别名
$ alias
# alias cp='cp -i'
# alias egrep='egrep --color=auto'
# alias fgrep='fgrep --color=auto'
# alias grep='grep --color=auto'
# alias l.='ls -d .* --color=auto'
# alias ll='ls -l --color=auto'
# alias ls='ls --color=auto'
# alias mv='mv -i'
# alias rm='rm -i'
# alias which='alias | /usr/bin/which --tty-only --read-alias --show-dot --show-tilde'

# 帮助命令
$ man ls
$ ls --help

# 压缩与解压缩命令'
# zip
# 安装zip unzip
$ yum install -y zip
$ yum install -y unzip

$ zip anaconda-ks.cfg.zip anaconda-ks.cfg
  # adding: anaconda-ks.cfg (deflated 45%)
$ ll
# total 16
# -rw-------. 1 root root 1834 Aug 10  2017 anaconda-ks.cfg
# -rw-r--r--. 1 root root 1187 Sep 29 14:32 anaconda-ks.cfg.zip
# -rw-r--r--. 1 root root    0 Sep 29 13:57 file.txt
# -rw-r--r--. 1 root root 1862 Aug 10  2017 initial-setup-ks.cfg
# drwxr-xr-x. 4 root root   45 Jun 22 13:30 logs
# -rw-------. 1 root root 1542 Aug  5 11:37 nohup.out
# drwxr-xr-x. 6 root root  113 Jul 31 13:19 store

$ zip -r store.zip store/
  # adding: store/ (stored 0%)
  # adding: store/lock (stored 0%)
  # adding: store/checkpoint (deflated 99%)
  # adding: store/config/ (stored 0%)
  # adding: store/config/consumerOffset.json.bak (deflated 73%)
  # adding: store/config/consumerFilter.json.bak (stored 0%)
  # adding: store/config/delayOffset.json.bak (stored 0%)
  # adding: store/config/subscriptionGroup.json.bak (deflated 92%)
  # adding: store/config/consumerFilter.json (stored 0%)
  # adding: store/config/topics.json (deflated 90%)
  # adding: store/config/consumerOffset.json (deflated 73%)
  # adding: store/config/topics.json.bak (deflated 90%)
  # adding: store/config/subscriptionGroup.json (deflated 92%)
  # adding: store/config/delayOffset.json (stored 0%)
  # adding: store/commitlog/ (stored 0%)
  # adding: store/commitlog/00000000000000000000 (deflated 100%)
  # ...

$ ll
# total 1956
# -rw-------. 1 root root    1834 Aug 10  2017 anaconda-ks.cfg
# -rw-r--r--. 1 root root    1187 Sep 29 14:32 anaconda-ks.cfg.zip
# -rw-r--r--. 1 root root       0 Sep 29 13:57 file.txt
# -rw-r--r--. 1 root root    1862 Aug 10  2017 initial-setup-ks.cfg
# drwxr-xr-x. 4 root root      45 Jun 22 13:30 logs
# -rw-------. 1 root root    1542 Aug  5 11:37 nohup.out
# drwxr-xr-x. 6 root root     113 Jul 31 13:19 store
# -rw-r--r--. 1 root root 1983555 Sep 29 14:34 store.zip

$ unzip anaconda-ks.cfg.zip
# Archive:  anaconda-ks.cfg.zip
#   inflating: anaconda-ks.cfg
$ unzip store.zip
# Archive:  store.zip
#    creating: store/
#  extracting: store/lock
#   inflating: store/checkpoint
#    creating: store/config/
#   inflating: store/config/consumerOffset.json.bak
#  extracting: store/config/consumerFilter.json.bak
#  extracting: store/config/delayOffset.json.bak
#   inflating: store/config/subscriptionGroup.json.bak
#  extracting: store/config/consumerFilter.json
#   inflating: store/config/topics.json
#   inflating: store/config/consumerOffset.json
#   inflating: store/config/topics.json.bak
#   inflating: store/config/subscriptionGroup.json
#  extracting: store/config/delayOffset.json
#    creating: store/commitlog/
#   inflating: store/commitlog/00000000000000000000
# ...


# gzip 源文件
# 压缩为.gz格式的压缩文件, 原文件会消失
$ gzip anaconda-ks.cfg
$ ll
# total 1948
# -rw-------. 1 root root    1041 Aug 10  2017 anaconda-ks.cfg.gz
# -rw-r--r--. 1 root root    1187 Sep 29 14:32 anaconda-ks.cfg.zip
# drwxr-xr-x. 6 root root     113 Jul 31 13:19 store
# -rw-r--r--. 1 root root 1983555 Sep 29 14:34 store.zip

# 解压
$ gzip -d anaconda-ks.cfg.gz
$ ll
# total 1948
# -rw-------. 1 root root    1834 Aug 10  2017 anaconda-ks.cfg
# -rw-r--r--. 1 root root    1187 Sep 29 14:32 anaconda-ks.cfg.zip
# drwxr-xr-x. 6 root root     113 Jul 31 13:19 store
# -rw-r--r--. 1 root root 1983555 Sep 29 14:34 store.zip

# 压缩目录下的所有子文件, 但是不能压缩目录
$ gzip -r store

# 将压缩的结果抽取的新的压缩文件中去
$ gzip -c anaconda-ks.cfg > test.gz
$ ll
# total 1952
# -rw-------. 1 root root    1834 Aug 10  2017 anaconda-ks.cfg
# -rw-r--r--. 1 root root    1187 Sep 29 14:32 anaconda-ks.cfg.zip
# drwxr-xr-x. 6 root root     122 Sep 29 14:45 store
# -rw-r--r--. 1 root root 1983555 Sep 29 14:34 store.zip
# -rw-r--r--. 1 root root    1041 Sep 29 14:46 test.gz

# 打包命令
# -c 打包
# -v 显示过程
# -f 指定打包后的文件名
$ tar -cvf store.tar store
$ tar -cf store.tar store
$ ll
# total 3972
# -rw-------. 1 root root    1834 Aug 10  2017 anaconda-ks.cfg
# -rw-r--r--. 1 root root    1187 Sep 29 14:32 anaconda-ks.cfg.zip
# drwxr-xr-x. 6 root root     122 Sep 29 14:45 store
# -rw-r--r--. 1 root root 2068480 Sep 29 14:51 store.tar
# -rw-r--r--. 1 root root 1983555 Sep 29 14:34 store.zip
# -rw-r--r--. 1 root root    1041 Sep 29 14:46 test.gz

# 解打包命令
$ tar -xvf store.tar
$ tar -xf store.tar

# tar.gz -z 表示压缩算法
$ tar -zcvf store.tar.gz store
$ tar -zxvf store.tar.gz

# 查看系统运行级别
$ runlevel
# N 3
$ cat /etc/inittab
# inittab is no longer used when using systemd.
#
# ADDING CONFIGURATION HERE WILL HAVE NO EFFECT ON YOUR SYSTEM.
#
# Ctrl-Alt-Delete is handled by /usr/lib/systemd/system/ctrl-alt-del.target
#
# systemd uses 'targets' instead of runlevels. By default, there are two main targets:
#
# multi-user.target: analogous to runlevel 3
# graphical.target: analogous to runlevel 5
#
# To view current default target, run:
# systemctl get-default
#
# To set a default target, run:
# systemctl set-default TARGET.target
#

# 其他命令
# 查看用户登录信息
$ w
#  15:07:20 up 42 days, 22:21,  1 user,  load average: 0.00, 0.01, 0.05
# USER     TTY      FROM             LOGIN@   IDLE   JCPU   PCPU WHAT
# root     pts/0    58.246.70.114    15:05    0.00s  0.00s  0.00s w
$ who
# root     pts/0        2020-09-29 15:05 (58.246.70.114)
$ last

# 磁盘使用情况
$ df
# Filesystem     1K-blocks    Used Available Use% Mounted on
# /dev/vda1       41151808 9058352  29980024  24% /
# devtmpfs          932240       0    932240   0% /dev
# tmpfs             941744       0    941744   0% /dev/shm
# tmpfs             941744     308    941436   1% /run
# tmpfs             941744       0    941744   0% /sys/fs/cgroup
# tmpfs             188352       0    188352   0% /run/user/0

$ df -h 
# Filesystem      Size  Used Avail Use% Mounted on
# /dev/vda1        40G  8.7G   29G  24% /
# devtmpfs        911M     0  911M   0% /dev
# tmpfs           920M     0  920M   0% /dev/shm
# tmpfs           920M  308K  920M   1% /run
# tmpfs           920M     0  920M   0% /sys/fs/cgroup
# tmpfs           184M     0  184M   0% /run/user/0

# 查看内存占用
$ free -h
#               total        used        free      shared  buff/cache   available
# Mem:           1.8G        245M        490M        308K        1.1G        1.4G
# Swap:            0B          0B          0B

# 查看操作历史
$ history

# 在显示器输出内容
$ echo $PATH
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/usr/local/jdk1.8.0_161/bin:/root/bin

# 权限管理
# 用户管理
# 添加用户
$ useradd user1
$ useradd user2
$ passwd user1
$ passwd user2

# 添加用户组
$ groupadd group1

# 修改用户组属性
$ usermod -G group1 user1
$ usermod -G group1 user2

# 查看系统用户组
$ cat /etc/group

# 用户及用户组的删除
$ userdel
$ groupdel

# 文件基本权限
# rwx 所有者 rwx 所属组 rwx 其他用户
$ ll
# total 19124
# drwxr-xr-x 5 root root     4096 Aug 28 17:34 apps
# -rw-r--r-- 1 root root 19538485 Jul 17 16:08 mail-0.0.1-SNAPSHOT.jar
# -rw------- 1 root root    30208 Jul 17 16:36 nohup.out
# drwxr-xr-x 3 root root     4096 May 21 11:55 software

# 修改文件所属组
$ chown user1:group1 test.txt

# 修改权限 
# a 所有用户
# g 所属组
# u 当前用户
# + 添加权限
# - 减去权限
# = 赋值权限
$ chmod g+rx test.txt
$ chmod 754 test.txt

$ chmod g+wx,o+x jdk.tar.gz
$ chmod 675 /root/jdk.tar.gz

# sudo权限
$ cat /etc/sudoers
$ visudo # 搜索 /root

# 文件和目录权限区别
$ vi hello.sh
# #!/bin/bash
# echo "hello"

$ ll
# -rw-r--r--. 1 root root   25 Oct 13 17:46 hello.sh
$ ./hello.sh
# -bash: ./hello.sh: Permission denied
$ chmod a+x hello.sh
$ ll
# -rwxr-xr-x. 1 root root   25 Oct 13 17:46 hello.sh
$ ./hello.sh
# hello

$ mkdir test
# drwxr-xr-x. 2 root root    6 Oct 13 17:52 test
$ chmod 000 test
# d---------. 2 root root    6 Oct 13 17:52 test