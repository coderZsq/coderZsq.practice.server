package com.coderZsq.shopping.test;

import com.coderZsq.shopping.dao.IProductDAO;
import com.coderZsq.shopping.dao.impl.ProductDAOImpl;
import com.coderZsq.shopping.domain.Product;
import com.coderZsq.shopping.handler.BeanListHandler;
import com.coderZsq.shopping.handler.IResultSetHandler;
import com.coderZsq.shopping.page.PageResult;
import com.coderZsq.shopping.query.ProductQueryObject;
import com.coderZsq.shopping.util.JdbcTemplate;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;

public class PageTest {
    private IProductDAO dao = new ProductDAOImpl();

    // 分页测试
    @Test
    public void test2() throws Exception {
        Integer currentPage = 1;
        Integer pageSize = 5;
        PageResult pageResult = dao.query(currentPage, pageSize);
        System.out.println("总条数: " + pageResult.getTotalCount());
        System.out.println("上页 = " + pageResult.getPrevPage() + ", 下页 = " + pageResult.getNextPage());
        for (Object o : pageResult.getListData()) {
            System.out.println(o);
        }
    }

    @Test
    public void test3() throws Exception {
        ProductQueryObject qo = new ProductQueryObject();
        qo.setCurrentPage(2);
        qo.setPageSize(10);
        qo.setName("M");
        qo.setMinSalePrice(new BigDecimal("100"));
        // qo.setMaxSalePrice(new BigDecimal("200"));
        // ----------------------------------
        PageResult pageResult = dao.query(qo);
        System.out.println("总条数: " + pageResult.getTotalCount());
        System.out.println("上页 = " + pageResult.getPrevPage() + ", 下页 = " + pageResult.getNextPage());
        for (Object o : pageResult.getListData()) {
            System.out.println(o);
        }
    }

    // 控制台版本的分页
    @Test
    public void test1() throws Exception {
        // 用户传入
        Integer currentPage = 1; // 当前第N页
        Integer pageSize = 5; // 每页最多多少条数据
        // 查询结果总数
        String countSql = "SELECT COUNT(*) FROM product";
        Integer totalCount = JdbcTemplate.query(countSql, new IResultSetHandler<Long>() {
            @Override
            public Long handle(ResultSet rs) throws Exception {
                if (rs.next()) {
                    return rs.getLong(1);
                }
                return 0L;
            }
        }).intValue();
        // ===============================
        // 查询结果集
        String resultSql = "SELECT * FROM product LIMIT ?,?";
        Object[] params = {(currentPage - 1) * pageSize, pageSize};
        List<Product> listData = JdbcTemplate.query(resultSql, new BeanListHandler<>(Product.class), params);
        System.out.println(totalCount);
        for (Product p : listData) {
            System.out.println(p);
        }
    }
}