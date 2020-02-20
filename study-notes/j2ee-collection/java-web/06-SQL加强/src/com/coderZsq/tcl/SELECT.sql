-- 悲观锁
BEGIN;
SELECT * FROM emp FOR UPDATE;
COMMIT;
ROLLBACK;

-- 乐观锁 version
