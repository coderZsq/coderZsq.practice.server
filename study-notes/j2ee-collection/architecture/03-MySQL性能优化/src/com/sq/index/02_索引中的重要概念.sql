-- 创建一个表
CREATE TABLE `t`
(
    `id`   int(11) NOT NULL,
    `k`    int(11) NOT NULL,
    `name` varchar(16) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `k` (`k`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

SHOW CREATE TABLE t;

-- id: 主键索引 k:普通索引
INSERT INTO t
VALUES (100, 1, '张三'),
       (200, 2, '李四'),
       (300, 3, '王五'),
       (400, 4, '赵六'),
       (500, 5, '孙七');

SELECT *
FROM t;

-- 回表 在普通索引树中查到主键并在主键索引树种查找
EXPLAIN
SELECT *
FROM t
WHERE k > 2;

-- 索引覆盖 在普通索引树种直接找到主键
EXPLAIN
SELECT id
FROM t
WHERE k > 2;

-- -------------------------------
CREATE TABLE `t2`
(
    `id`    int(11)     NOT NULL,
    `age`   int(11)     NOT NULL,
    `name`  varchar(16) NOT NULL,
    `score` int(11) DEFAULT NULL,
    `level` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `idx_name_age` (`name`, `age`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

SHOW CREATE TABLE t2;

SELECT *
FROM t2;

INSERT INTO t2 (id, age, name)
VALUES (11, 18, '张三'),
       (12, 21, '李四'),
       (13, 32, '王五'),
       (14, 38, '赵六'),
       (15, 43, '孙七');

-- 索引前缀 联合索引: name + age
SELECT *
FROM t2;

EXPLAIN
SELECT *
FROM t2
WHERE name = '张三';

EXPLAIN
SELECT *
FROM t2
WHERE age = 20;

EXPLAIN
SELECT *
FROM t2
WHERE name = '王五'
  AND age > 30;

SELECT name
FROM t2
ORDER BY name;

SELECT age
FROM t2
ORDER BY age;

SELECT *
FROM t2
WHERE name LIKE '%jack';

-- 索引下推
EXPLAIN
SELECT *
FROM t2
WHERE name LIKE '张%'
  AND age < 51;

