package com.sq.orderserver.service.impl;

import com.sq.orderserver.domain.Order;
import com.sq.orderserver.service.IOrderService;
import com.sq.productapi.domain.Product;
import com.sq.productapi.feign.ProductFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService {
    // @Autowired
    // private RestTemplate restTemplate;

    // ProductFeignApi 类型的Bean对象 根据ProductFeignApi接口创建动态代理对象jdk动态代理对象
    @Autowired
    private ProductFeignApi productFeignApi;

    @Override
    public Order save(Long userId, Long productId) {
        // 商品信息应该通过productId 从商品服务查询到
        // http请求: http://localhost:8081/api/v1/product/find?id=1
        // 方式: httpClient, RestTemplate, URLConnection
        // dubbo rpc -> http, dubbo, webservice
        // springCloud --> http --> feign
        // Product product = restTemplate.getForObject("http://product-server/api/v1/product/find?id=" + productId, Product.class); // 远程获取

        // 调用代理对象的方法invoker方法
        // 1 请求协议: 必须是http
        // 2 根据ribbon的负载策略, 根据service-id查找@Feign-Client中的name属性: product-server
        // 3 请求的资源路径: @RequestMapping: api/v1/product/find
        // 4 请求的参数名称: 通过注解@RequestParam("id")注解可以找到
        // 5 请求的参数值, 在调用的时候传入进来productId
        // http://localhost:8081/api/v1/product/find?id=2
        Product product = productFeignApi.find(productId); // 远程获取

        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        order.setCreateTime(new Date());
        order.setUserId(userId);
        order.setProductName(product.getName());
        order.setProductPrice(product.getPrice());
        System.out.println("执行保存订单操作");
        return order;
    }
}
