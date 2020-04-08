# 查看内置mysql版本
rpm -qa |grep -i mysql
# yum remove 

# 查找mysql相关目录
find / -name mysql
# rm -rf /var/lib/mysql
# rm -rf /var/lib/mysql/mysql
# rm -rf /etc/selinux/targeted/active/modules/100/mysql
# rm -rf /usr/share/mysql

# 删除/etc/my.cnf
rm -rf /etc/my.cnf

# 删除/var/log/mysqld.log
rm -rf /var/log/mysqld.log

# 删除冲突库
rpm -e postfix mariadb-libs

# 安装依赖
yum -y install net-tools perl

# 安装rpm包
rpm -ivh *.rpm

# 启动mysql服务
systemctl start mysqld

# 查看状态
systemctl status mysqld

# 设置开机启动
systemctl enable mysqld

# 查看临时密码
grep 'temporary password' /var/log/mysqld.log

# 登录账户修改密码
mysql -uroot -p'KI_qgkwxq5dK' 
ALTER USER 'root'@'localhost' IDENTIFIED BY 'KI_qgkwxq5dK';

# 分配远程访问权限
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'KI_qgkwxq5dK' WITH GRANT OPTION;

# 刷新权限
FLUSH PRIVILEGES;

# 开放3306端口
firewall-cmd --zone=public --add-port=3306/tcp --permanent
firewall-cmd --reload

# 查看端口开放
firewall-cmd --list-port

# 重启服务
systemctl restart mysqld

# 查看配置文件
rpm -qc mysql-community-server

# 修改配置文件 (字符集)
vi /etc/my.cnf
# character_set_server=utf8

# 重启服务
systemctl restart mysqld