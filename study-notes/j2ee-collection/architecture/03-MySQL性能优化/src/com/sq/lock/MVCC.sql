CREATE TABLE t1 (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20)
);

SET AUTOCOMMIT = 0;

INSERT INTO t1(name) VALUES ('lucy');
INSERT INTO t1(name) VALUES ('jack');
INSERT INTO t1(name) VALUES ('tom');

# id name create_version delete_version
DELETE FROM t1 WHERE id = 1;

# 设置事务的隔离级别
SET SESSION TRANSACTION ISOLATION LEVEL REPEATABLE READ;
# 查看数据库的事务隔离级别
SHOW VARIABLES LIKE '%isolation%'