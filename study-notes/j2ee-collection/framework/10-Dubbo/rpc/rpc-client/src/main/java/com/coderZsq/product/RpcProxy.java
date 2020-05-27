package com.coderZsq.product;

import com.coderZsq.product.common.RpcRequest;
import com.coderZsq.product.common.RpcResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

@Setter
@Getter
// 动态代理类, 用于获取到每个类的代理对象
// 对于被代理对象的所有的方法调用都会执行invoke方法
public class RpcProxy {
    public <T> T getInstance(Class<T> interfaceClass) {
        Object instance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), // 类加载器
                new Class[]{interfaceClass}, // 类实现的接口
                new InvocationHandler() { // 业务处理对象
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("args = " + args);
                        System.out.println("通过动态代理技术获取对应的操作");
                        // 发送远程服务请求
                        String host = "127.0.0.1";
                        // 1 创建一个客户端连接
                        int port = 7654;
                        Socket socket = null;
                        RpcResponse result = null;
                        ObjectOutputStream oos = null;
                        ObjectInputStream ois = null;
                        try {
                            socket = new Socket(host, port);
                            oos = new ObjectOutputStream(socket.getOutputStream());

                            // 2 封装请求参数
                            RpcRequest rpcRequest = new RpcRequest();
                            // 设置方法
                            rpcRequest.setMethodName(method.getName());
                            // 设置请求参数
                            rpcRequest.setParameters(args);
                            // 设置参数类型
                            rpcRequest.setParameterTypes(method.getParameterTypes());
                            // 传入接口的简单名称
                            rpcRequest.setClassName(interfaceClass.getSimpleName());
                            oos.writeObject(rpcRequest); // 往服务端发送请求
                            oos.flush();
                            // 接收服务端的响应
                            ois = new ObjectInputStream(socket.getInputStream());
                            RpcResponse rpcResponse = (RpcResponse) ois.readObject();
                            return rpcResponse.getResult(); // 返回结果
                        } catch (EOFException e) {
                            System.out.println("客户端关闭连接");
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } finally {
                            if (ois!= null) {
                                try {
                                    ois.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (oos != null) {
                                try {
                                    oos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        return null;
                    }
                });
        return (T) instance;
    }
}
