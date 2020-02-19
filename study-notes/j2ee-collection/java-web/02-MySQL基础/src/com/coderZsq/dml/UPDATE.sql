-- 需求: 修改西门吹雪的名字为叶孤城
UPDATE t_student SET name = '叶孤城' WHERE name = '西门吹雪'
-- 需求2: 修改id为3的学生, 名字改为张三, 年龄改为18, 邮箱改为zhang@
UPDATE t_student SET name = '张三', age = 18, email = 'zhang@' WHERE id = 3
-- 请勿轻易更改主键
UPDATE t_student SET id = 30 WHERE id = 3