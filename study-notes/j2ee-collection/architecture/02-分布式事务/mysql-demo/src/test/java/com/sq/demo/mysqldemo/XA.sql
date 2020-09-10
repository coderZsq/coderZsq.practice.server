-- 开启XA事务, 需要指定一个事务ID
XA START 'xatest';
-- 执行sql语句
INSERT INTO t_account(id, amount) VALUES(300, 3000);
-- 结束事务
XA END 'xatest';
-- 准备提交阶段
XA PREPARE 'xatest';
-- 提交
XA COMMIT 'xatest';
-- 回滚
XA ROLLBACK 'xatest';

XA RECOVER 'xatest';