package com.coderZsq._20_product.test;

import com.coderZsq._20_product.dao.IProductDAO;
import com.coderZsq._20_product.dao.impl.ProductDAOImpl;
import com.coderZsq._20_product.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class ProductDAOTest {
    private IProductDAO dao = new ProductDAOImpl();

    @Test
    public void testSave() {
        Product p = dao.get(1L);
        p.setProductName("机械键盘");
        p.setCostPrice(new BigDecimal("100"));
        p.setSalePrice(new BigDecimal("500"));
        dao.save(p);
    }

    @Test
    public void testUpdate() {
        Product p = dao.get(21L);
        p.setProductName("专属键盘");
        dao.update(p);
    }

    @Test
    public void testDelete() {
        dao.delete(21L);
    }

    @Test
    public void testGet() {
        Product p = dao.get(1L);
        System.out.println(p);
    }

    @Test
    public void testList() {
        List<Product> list = dao.list();
        for (Product p : list) {
            System.out.println(p);
        }
    }
}
