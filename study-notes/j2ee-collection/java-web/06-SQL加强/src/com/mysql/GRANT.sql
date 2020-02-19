-- 给一个存在用户赋予权限
-- 语法1: GRANT 权限 (columns) ON 数据库对象 TO 用户
GRANT SELECT ON jdbc.t_user TO coderZsq@localhost;
GRANT ALL ON jdbc.* TO coderZsq@localhost;
GRANT ALL ON *.* TO coderZsq@localhost;

-- 创建用户, 设置密码, 赋予权限
-- 语法2: GRANT 权限 (columns) ON 数据库对象 TO 用户 IDENTIFIED BY 密码
GRANT ALL ON *.* TO coderZsq@localhost IDENTIFIED BY '1';

-- 创建用户, 设置密码, 赋予权限, 并且该用户可以继续授权给其他用户
-- 语法3: GRANT 权限 (columns) ON 数据库对象 TO 用户 IDENTIFIED BY 密码 WITH GRANT OPTION
GRANT ALL ON *.* TO coderZsq@localhost IDENTIFIED BY '1' WITH GRANT OPTION;

-- 权限的其他命令
-- 查看当前用户的权限
SHOW GRANTS;
-- 查看特定用户的权限
SHOW GRANTS FOR coderZsq@localhost;

-- 使用REVOKE命令回收对用户的授权
-- REVOKE 权限 ON 数据库对象 FROM 用户
-- 注意: 使用REVOKE 撤销全部权限, 操作者必须拥有MySQL数据库的全局CREATE USER权限或UPDATE权限
REVOKE SELECT ON *.* FROM coderZsq@localhost;

-- 删除用户: DROP USER 用户名称
-- 注意, user必须包括user@host
DROP USER coderZsq@localhost;
