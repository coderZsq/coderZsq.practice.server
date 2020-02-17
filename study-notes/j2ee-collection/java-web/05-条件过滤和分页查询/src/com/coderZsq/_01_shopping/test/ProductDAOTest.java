package com.coderZsq._01_shopping.test;

import com.coderZsq._01_shopping.dao.IProductDAO;
import com.coderZsq._01_shopping.dao.impl.ProductDAOImpl;
import com.coderZsq._01_shopping.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class ProductDAOTest {
    private IProductDAO dao = new ProductDAOImpl();

    @Test
    public void testList() {
        List<Product> list = dao.list();
        for (Product p : list) {
            System.out.println(p);
        }
    }

    // 高级查询测试
    @Test
    public void testQuery() {
        List<Product> list = dao.query("", new BigDecimal("100"), new BigDecimal("200"));
        System.out.println(list.size());
        for (Product p : list) {
            System.out.println(p);
        }
    }

}
