-- 给一个存在用户赋予权限
-- 语法1: GRANT 权限 (columns) ON 数据库对象 TO 用户
GRANT SELECT ON jdbc.t_user TO coderZsq@localhost

GRANT ALL ON jdbc.* TO coderZsq@localhost

-- 创建用户, 设置密码, 赋予权限
-- 语法2: GRANT 权限 (columns) ON 数据库对象 TO 用户 IDENTIFIED BY 密码
GRANT ALL ON

-- 创建用户, 设置密码, 赋予权限, 并且该用户可以继续授权给其他用户
-- 语法3: GRANT 权限 (columns) ON 数据库对象 TO 用户 IDENTIFIED BY 密码 WITH GRANT OPTION
