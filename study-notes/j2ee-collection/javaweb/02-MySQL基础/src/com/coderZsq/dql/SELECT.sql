# 需求: 查询所有货品信息
SELECT * FROM product
# 需求: 查询所有货品的id productName, salePrice
SELECT id, saleprice, productName FROM product
# 需求: 查询商品的分类编号 (避免重复数据)
SELECT DISTINCT dir_id FROM product
# 对NUMBER型的数据可以使用算数操作符创建表达式(+ - * /)
# 对DATE型数据可以使用部分算数操作符创建表达式(+ -)
# 运算符优先级:
# 1. 乘法和除法的优先级高于加法和减法
# 2. 同级运算的顺序是从左到右
# 3. 表达式中使用"括号"可强行改变优先级的运算顺序
# -----------------------------------------------
# 需求: 查询所有货品的id, 名称和批发价(批发价=卖价*折扣)
SELECT id, productName, salePrice, cutoff, salePrice * cutoff FROM product
# 需求: 查询所有货品的id, 名称, 和各进50个的成本价(成本=costPrice)
SELECT id, productName, costPrice * 50 FROM product
# 需求: 查询所有货品的id, 名称, 各进50个, 并且每个运费1元的成本
SELECT id, productName, (costPrice + 1) * 50 FROM product
# 需求: 查询所有货品的id, 名称, 各进50个, 并且每个运费1元的成本 (使用别名)
SELECT id AS 货品ID, productName, (costPrice + 1) * 50 FROM product
SELECT id, productName, (costPrice + 1) * 50 allPrice FROM product
# 为方便用户浏览查询的结果数据, 有时需要设置显示格式, 可以使用CONCAT函数来链接字符串.
# 需求: 查询商品的名字和零售价.
#     格式: xxx商品的零售价为: xxx
SELECT CONCAT (productName, '商品的零售价为: ', salePrice) AS productSalePrice FROM product
# 运算符   含义
# -----------------------------------------------
# =       等于
# >       大于
# >=      大于或等于
# <       小于
# <=      小于或等于
# != (<>) 不等于
# -----------------------------------------------
# 需求: 查询货品零售价为119的所有货品信息
SELECT * FROM product WHERE salePrice = 119
# 需求: 查询货品名为罗技G9X的所有货品信息
SELECT * FROM product WHERE productName = '罗技G9X'
# 需求: 查询货品名 不为 罗技G9X的所有货品信息
SELECT * FROM product WHERE productName != '罗技G9X'
# 需求: 查询分类编号不等于2的货品信息
SELECT * FROM product WHERE dir_id <> 2
# 需求: 查询货品名称, 零售价小于等于200的货品
SELECT productName, salePrice FROM product WHERE salePrice <= 200
# 需求: 查询id, 货品名称, 批发价大于350的货品
SELECT id, productName, salePrice * cutoff
FROM product
WHERE salePrice * cutoff > 350
# 思考: 使用where后面使用别名不行, 总结select和where的执行顺序
# -----------------------------------------------
# 运算符    含义
# -----------------------------------------------
# AND (&&) 如果组合的条件都是TRUE, 返回TRUE
# OR (||)  如果组合的条件之一都是TRUE, 返回TRUE
# NOT (!)  如果下面的条件时FALSE, 返回TRUE
# -----------------------------------------------
# 需求: 选择id, 货品名称, 零售价在300-400之间的货品
SELECT id, productName, salePrice FROM product
WHERE salePrice >= 300 AND salePrice <= 400
# 需求: 选择id, 货品名称, 分类编号为2, 4的所有货品
SELECT * FROM product WHERE dir_id = 2 OR dir_id = 4
# 需求: 选择id, 货品名称, 分类编号不为2的所有商品
SELECT id, productName, dir_id FROM product WHERE dir_id != 2
SELECT id, productName, dir_id FROM product WHERE NOT dir_id = 2
# 需求: 选择id, 分类编号的货品零售价大于等于250或者是成本大于等于200
SELECT id, productName, dir_id, salePrice, costPrice
FROM product
WHERE salePrice >= 250 OR costPrice >= 200

SELECT id, productName FROM product
WHERE
    (NOT productName LIKE '%M%' AND salePrice > 100) OR (dir_id = 2)
# -----------------------------------------------
# WHERE 列名 BETWEEN 最小值 AND 最大值: 闭区间
# 需求: 选择id, 货品名称, 零售价在300-400之间的货品
SELECT id, productName, salePrice FROM product
WHERE salePrice >= 300 AND salePrice <= 400
SELECT id, productName, salePrice FROM product
WHERE salePrice BETWEEN 300 AND 400
# 需求: 选择id, 货品名称, 零售价不在300-400之间的货品
SELECT id, productName, salePrice FROM product
WHERE NOT (salePrice >= 300 AND salePrice <= 400)
SELECT id, productName, salePrice FROM product
WHERE  salePrice NOT BETWEEN 300 AND 400
# 需求: 选择id, 货品名称, 分类编号为2, 4的所有货品
SELECT * FROM product WHERE dir_id = 2 OR dir_id = 4
SELECT * FROM product WHERE dir_id IN (2, 4)
# 需求: 选择id, 货品名称, 分类编号不为2, 4的所有货品
SELECT * FROM product WHERE dir_id NOT IN (2, 4)
# IS NULL: 判断列的值是否为空
# 格式: WHERE 列名 IS NULL
# 需求: 查询商品名为NULL的所有商品信息
SELECT * FROM product WHERE productName IS NULL
# -----------------------------------------------
# 使用LIKE运算符执行通配查询, 查询条件可包含文字字符或数字
# %通配符: 可表示零或多个字符
# _通配符: 可表示一个字符
# 通配符: 用来实现匹配部分值的特殊字符
# -----------------------------------------------
# 需求: 查询id, 货品名称, 货品名称匹配'%罗技M9_'
SELECT * FROM product WHERE productName LIKE '%罗技M9_'
SELECT * FROM product WHERE productName LIKE '%罗技M9%'
# 需求: 查询id, 货品名称, 分类编号, 零售价大于等于80并且货品名称匹配'%罗技M1__'
SELECT id, productName, dir_id, salePrice FROM product
WHERE salePrice >= 80 AND productName LIKE '%罗技M1__'