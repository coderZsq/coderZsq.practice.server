SHOW INDEX FROM employees.titles;

-- 全列匹配
EXPLAIN -- key_len 59
SELECT *
FROM employees.titles
WHERE emp_no = '10001'
  AND from_date = '1986-06-26'
  AND title = 'Senior Engineer';

-- 最左前缀匹配
EXPLAIN -- type ref 走索引 key_len 4
SELECT *
FROM employees.titles
WHERE emp_no = '10001';

-- 查询条件没有指定索引第一列
EXPLAIN -- type ALL 不走索引
SELECT *
FROM employees.titles
WHERE from_date = '1986-06-26';

-- 复合索引中间某个条件未提供
EXPLAIN -- key_len 4
SELECT *
FROM employees.titles
WHERE emp_no = '10001'
  AND from_date = '1986-06-26';

EXPLAIN -- key_len 59
SELECT *
FROM employees.titles
WHERE emp_no = '10001'
  AND title IN
      ('Senior Engineer', 'Staff', 'Engineer', 'Senior Staff', 'Assistant Engineer', 'Technique Leader', 'Manager')
  AND from_date = '1986-06-26';

-- 匹配某列的前缀字符串
EXPLAIN -- key_len 56
SELECT *
FROM employees.titles
WHERE emp_no = '10001'
  AND title LIKE 'Senior%';

-- 范围查询
EXPLAIN -- type range 如果第一个索引是范围索引时, 第二索引就失效了 key_len 4
SELECT *
FROM employees.titles
WHERE emp_no < '10010'
  AND title = 'Senior Engineer';

EXPLAIN -- type range BETWEEN会进行转化成IN 走索引 key_len 59
SELECT *
FROM employees.titles
WHERE emp_no BETWEEN '10001' AND '10010'
  AND title = 'Senior Engineer'
  AND from_date BETWEEN '1986-01-01' AND '1986-12-31';

-- 查询条件中含有函数或表达式
EXPLAIN -- 用函数不会走索引 key_len 4
SELECT *
FROM employees.titles
WHERE emp_no = '10001'
  AND LEFT(title, 6) = 'Senior';

EXPLAIN -- key_len 56
SELECT *
FROM employees.titles
WHERE emp_no = '10001'
  AND title LIKE 'Senior%';

SELECT *
FROM employees.titles
WHERE emp_no = '10001'
  AND LEFT(title, 6) = 'Senior';
SHOW PROFILES; -- Duration 0.002098

SELECT *
FROM employees.titles
WHERE emp_no = '10001'
  AND title LIKE 'Senior%';
SHOW PROFILES; -- Duration 0.000986

EXPLAIN -- type ALL emp_no - 1是表达式 不走索引
SELECT *
FROM employees.titles
WHERE emp_no - 1 = '10000';

EXPLAIN -- type ref key_len 4
SELECT *
FROM employees.titles
WHERE emp_no = '10001';

SELECT *
FROM employees.titles
WHERE emp_no - 1 = '10000';
SHOW PROFILES; -- Duration 0.16446

SELECT *
FROM employees.titles
WHERE emp_no = '10001';
SHOW PROFILES; -- Duration 0.000777