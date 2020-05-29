package com.coderZsq.rpc.v1.service;


import com.coderZsq.rpc.v1.domain.Product;

/**
 * 定义产品接口的操作规范
 */
public interface IProductService {
    /**
     * 保存产品
     * @param product
     */
    void save(Product product);

    /**
     * 根据产品id删除产品
     * @param productId
     */
    void deleteById(Long productId);

    /**
     * 修改产品信息
     * @param product
     */
    void update(Product product);

    /**
     * 根据产品id获取到产品信息
     * @param productId
     * @return
     */
    Product get(Long productId);
}
