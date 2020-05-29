package com.coderzsq.rpc.v1;

import com.coderZsq.rpc.v1.common.RpcRequest;
import com.coderZsq.rpc.v1.service.IProductService;

// 模拟客户端调用
public class SocketDemo {
    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient("127.0.0.1", 9000);
        // 1. 构造请求参数
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(IProductService.class.getSimpleName());
        rpcRequest.setMethodName("get");
        rpcRequest.setParameters(new Object[]{1L});
        rpcRequest.setParameterTypes(new Class[]{Long.class});
        Object result = rpcClient.send(rpcRequest);
        System.out.println("result = " + result);
    }
}
