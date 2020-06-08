package com.coderZsq.service.impl;

import com.coderZsq.domain.Product;
import com.coderZsq.service.BizException;
import com.coderZsq.service.IProductService;
import com.coderZsq.mapper.ProductMapper;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(loadbalance = "roundrobin", cluster = "failfast")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> list() {
        return productMapper.list();
    }

    @Override
    public Product get(Long id) {
        System.out.println(RpcContext.getContext().getAttachment("name"));
        // 抛出自定义异常
        if (1 < 2) {
            throw new BizException("测试抛出业务异常");
            // 服务端 BizException 异常类型
            // 客户端 RuntimeException 异常类型 不一致 throw RuntimeException("info")
            // 如果要保持一致, 那么客户端应该抛出BizException throw BizException("info")
        }
        System.out.println("调用指定服务, 正在写的测试代码");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productMapper.get(id);
    }
}
