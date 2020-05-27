package com.coderZsq.product.web;

import com.coderZsq.product.RpcProxy;
import com.coderZsq.product.domain.Product;
import com.coderZsq.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired // 去IoC容器中 根据类型匹配对应的bean
    private IProductService productService;
    // private RpcProxy rpcProxy;

    @RequestMapping("save")
    public String save(Product product) throws Exception {
        // 调用方法, ---> 浏览器发送请求 --> 创建连接 ---> 获取到返回结果 --> 关闭连接
        // 调用save方法 ---> 并不是真正的本地保存操作
        // 发送请求到 服务端 调用, 把结果返回
        // IProductService productService = rpcProxy.getInstance(IProductService.class);
        productService.save(product);
        return "success";
    }

    @RequestMapping("get")
    public Product get(Long id) throws Exception {
        // IProductService productService = rpcProxy.getInstance(IProductService.class);
        Product product = productService.get(id);
        return product;
    }
}
