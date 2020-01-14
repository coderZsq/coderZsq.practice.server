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
