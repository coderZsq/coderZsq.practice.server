```sh
$ mysql -uroot -proot
mysql: [Warning] Using a password on the command line interface can be insecure.
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 137
Server version: 5.7.29 MySQL Community Server (GPL)

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> use db;
Database changed
mysql> select database();
+------------+
| database() |
+------------+
| db         |
+------------+
1 row in set (0.00 sec)

mysql> show tables;
+--------------+
| Tables_in_db |
+--------------+
| lockt        |
+--------------+
1 row in set (0.00 sec)

mysql> show columns from lockt;
+-------+---------+------+-----+---------+----------------+
| Field | Type    | Null | Key | Default | Extra          |
+-------+---------+------+-----+---------+----------------+
| id    | int(11) | NO   | PRI | NULL    | auto_increment |
| col1  | int(11) | YES  |     | NULL    |                |
| col2  | int(11) | YES  |     | NULL    |                |
+-------+---------+------+-----+---------+----------------+
3 rows in set (0.00 sec)

mysql> select * from lockt;
+------+-------+------+
| id   | col1  | col2 |
+------+-------+------+
|    1 |     1 |    1 |
|    2 |     2 |    3 |
|    5 |     5 |    5 |
|    6 |     6 |    9 |
|   10 |    10 | 1111 |
|  123 |   123 |    8 |
| 1007 | 10077 |  144 |
| 1008 |  1008 |  220 |
| 1019 |  1019 |  200 |
| 1020 |  1020 |  201 |
| 1111 |  1111 | 1111 |
| 1234 |  1234 |   33 |
+------+-------+------+
12 rows in set (0.00 sec)

mysql> set autocommit=0;
Query OK, 0 rows affected (0.00 sec)

mysql> begin;
Query OK, 0 rows affected (0.00 sec)

mysql> select * from lockt;
+------+-------+------+
| id   | col1  | col2 |
+------+-------+------+
|    1 |     1 |    1 |
|    2 |     2 |    3 |
|    5 |     5 |    5 |
|    6 |     6 |    9 |
|   10 |    10 | 1111 |
|  123 |   123 |    8 |
| 1007 | 10077 |  144 |
| 1008 |  1008 |  220 |
| 1019 |  1019 |  200 |
| 1020 |  1020 |  201 |
| 1111 |  1111 | 1111 |
| 1234 |  1234 |   33 |
+------+-------+------+
12 rows in set (0.00 sec)

mysql> select * from lockt where id=10 for update;
+----+------+------+
| id | col1 | col2 |
+----+------+------+
| 10 |   10 | 1111 |
+----+------+------+
1 row in set (0.00 sec)
```

---

```sh
$ mysql -uroot -proot
mysql: [Warning] Using a password on the command line interface can be insecure.
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 138
Server version: 5.7.29 MySQL Community Server (GPL)

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> use db;
Database changed
mysql> set autocommit=0;
Query OK, 0 rows affected (0.00 sec)

mysql> begin;
Query OK, 0 rows affected (0.00 sec)

mysql> select database();
+------------+
| database() |
+------------+
| db         |
+------------+
1 row in set (0.00 sec)

mysql> select * from lockt;
+------+-------+------+
| id   | col1  | col2 |
+------+-------+------+
|    1 |     1 |    1 |
|    2 |     2 |    3 |
|    5 |     5 |    5 |
|    6 |     6 |    9 |
|   10 |    10 | 1111 |
|  123 |   123 |    8 |
| 1007 | 10077 |  144 |
| 1008 |  1008 |  220 |
| 1019 |  1019 |  200 |
| 1020 |  1020 |  201 |
| 1111 |  1111 | 1111 |
| 1234 |  1234 |   33 |
+------+-------+------+
12 rows in set (0.00 sec)

mysql> update lockt set id=11 where id=10;
```

---

```sh
show engine innodb status\G;
------------
TRANSACTIONS
------------
Trx id counter 1857
Purge done for trx's n:o < 0 undo n:o < 0 state: running but idle
History list length 0
LIST OF TRANSACTIONS FOR EACH SESSION:
---TRANSACTION 422169758665400, not started
0 lock struct(s), heap size 1136, 0 row lock(s)
---TRANSACTION 422169758664496, not started
0 lock struct(s), heap size 1136, 0 row lock(s)
---TRANSACTION 1855, ACTIVE 8 sec
2 lock struct(s), heap size 1136, 1 row lock(s)
MySQL thread id 137, OS thread handle 123145524248576, query id 244 localhost root starting
show engine innodb status
---TRANSACTION 1853, ACTIVE 221 sec starting index read
mysql tables in use 1, locked 1
LOCK WAIT 2 lock struct(s), heap size 1136, 2 row lock(s)
MySQL thread id 138, OS thread handle 123145524690944, query id 243 localhost root updating
update lockt set id=11 where id=10
Trx read view will not see trx with id >= 1852, sees < 1850
------- TRX HAS BEEN WAITING 4 SEC FOR THIS LOCK TO BE GRANTED:
RECORD LOCKS space id 24 page no 3 n bits 80 index PRIMARY of table `db`.`lockt` trx id 1853 lock_mode X locks rec but not gap waiting
Record lock, heap no 6 PHYSICAL RECORD: n_fields 5; compact format; info bits 0
 0: len 4; hex 8000000a; asc     ;;
 1: len 6; hex 00000000071c; asc       ;;
 2: len 7; hex bc000001330110; asc     3  ;;
 3: len 4; hex 8000000a; asc     ;;
 4: len 4; hex 80000457; asc    W;;
 ```

 