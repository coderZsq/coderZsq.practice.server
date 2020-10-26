SELECT *
FROM employees;

-- 优化ORDER BY
EXPLAIN
SELECT first_name, gender, last_name
FROM employees
ORDER BY first_name;

SHOW VARIABLES LIKE 'max_length_for_sort_data'; -- 1K

SHOW VARIABLES LIKE '%sort%'; -- 1048576 innodb_sort_buffer_size: 1M