-- 需求: 插入一条学生信息
-- 1. 插入完整数据记录
INSERT INTO t_student (name, email, age) VALUES ('陆小凤', 'lu@', 28)
-- 2. 插入数据记录一部分
INSERT INTO t_student (name, email, age) VALUES ('司空摘星', NULL, 29)
INSERT INTO t_student (name, age) VALUES ('叶孤城', 29)
-- 3. 插入多条数据记录 (MySQL特有)
INSERT INTO t_student (name, email, age) VALUES ('A', 'a@', 20), ('B', 'b@', 20), ('C', 'c@', 20)
-- 4. 插入查询结果 (测试)
INSERT INTO t_student (name) SELECT name FROM t_student