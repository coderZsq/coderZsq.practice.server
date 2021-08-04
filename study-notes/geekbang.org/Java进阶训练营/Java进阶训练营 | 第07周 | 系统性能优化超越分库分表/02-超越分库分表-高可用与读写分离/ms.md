### 主从复制演示

[TOC]

#### 准备两个MySQL服务实例

> windows上可以用压缩版本，例如mysql-5.7.31-winx64.zip，解压文件夹再复制一份，添加my.ini配置文件。假设一个叫mysql-5.7.31-winx64，一个叫mysql-5.7.31-winx64-2，以为分别配置其为主和从。
>
> Mac和Linux环境，自己想办法，也可以用docker

#### 修改主mysql-5.7.31-winx64的my.ini

```
basedir = ./
datadir = ./data
port = 3306
server_id = 1

sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 
log_bin=mysql-bin
binlog-format=Row
```



#### 修改从mysql-5.7.31-winx64-2的my.ini

```
basedir = ./
datadir = ./data
port = 3316
server_id = 2

sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 
log_bin=mysql-bin
binlog-format=Row
```



#### 初始化和启动数据库

空数据库需要执行mysqld --initialize-insecure 进行初始化。

分别启动主和从，在命令行下直接执行mysqld或start mysqld命令即可。



#### 配置主节点

mysql命令登录到主节点：mysql -uroot -P3306

```
mysql> CREATE USER 'repl'@'%' IDENTIFIED BY '123456';
Query OK, 0 rows affected (0.11 sec)

mysql> GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
Query OK, 0 rows affected (0.12 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.10 sec)

mysql> show master status;
+------------------+----------+--------------+------------------+-------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+------------------+----------+--------------+------------------+-------------------+
| mysql-bin.000003 |      305 |              |                  |                   |
+------------------+----------+--------------+------------------+-------------------+
1 row in set (0.00 sec)
```

创建数据库：create schema db;



#### 配置从节点

mysql命令登录到从节点：mysql -uroot -P3316

```
CHANGE MASTER TO
    MASTER_HOST='localhost',  
    MASTER_PORT = 3306,
    MASTER_USER='repl',      
    MASTER_PASSWORD='123456',   
    MASTER_LOG_FILE='mysql-bin.000003',
    MASTER_LOG_POS=305;
    
    //MASTER_AUTO_POSITION = 1;
```

创建数据库：create schema db;



#### 验证操作

在主库执行：

```
mysql> use db
Database changed
mysql> create table t1(id int);
Query OK, 0 rows affected (0.17 sec)

mysql>
mysql>
mysql> insert into t1(id) values(1),(2);
Query OK, 2 rows affected (0.04 sec)
```



在从库查看数据同步情况

```
mysql> use db
Database changed
mysql>
mysql>
mysql> show tables;
+--------------+
| Tables_in_db |
+--------------+
| t1           |
+--------------+
1 row in set (0.00 sec)

mysql>
mysql>
mysql> select * from t1;
+------+
| id   |
+------+
|    1 |
|    2 |
+------+
2 rows in set (0.00 sec)
```



#### 查看命令

可以通过show master status\G, show slave status\G 查看状态

可以能改过stop slave; start slave;来停止复制。



#### 其他

GTID与复制：

https://blog.51cto.com/13540167/2086045

https://www.cnblogs.com/zping/p/10789151.html

半同步复制：

https://www.cnblogs.com/zero-gg/p/9057092.html

组复制：

https://www.cnblogs.com/lvxqxin/p/9407080.html