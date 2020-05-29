package com.coderZsq.rpc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationContext;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

@Setter
@Getter
@NoArgsConstructor
public class RpcServerHandler implements Runnable {
    private Socket socket;
    private ApplicationContext applicationContext;

    public RpcServerHandler(Socket socket, ApplicationContext applicationContext) {
        this.socket = socket;
        this.applicationContext = applicationContext;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
            // 读取内容
            RpcRequest rpcRequest = (RpcRequest) ois.readObject();
            System.out.println("服务端收到客户端的请求: rpcRequest = " + rpcRequest);
            // 调用业务方法执行代码
            // 1 通过类名找到对应的Bean对象
            String className = rpcRequest.getClassName(); // IProductService
            Object bean = applicationContext.getBean(className);
            Class<?> clazz = bean.getClass(); // 字节码对象
            // 2 通过反射调用类的对应方法
            String methodName = rpcRequest.getMethodName();
            Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
            Object[] parameters = rpcRequest.getParameters();
            Method method = null; // 方法对象
            Object result = null; // 反射的返回结果
            if (parameterTypes == null || parameterTypes.length == 0) {
                method = clazz.getMethod(methodName);
                result = method.invoke(bean);
            } else {
                method = clazz.getMethod(methodName, parameterTypes);
                result = method.invoke(bean, parameters);
            }
            // 发送数据到客户端
            RpcResponse rpcResponse = new RpcResponse();
            rpcResponse.setSuccess(true);
            rpcResponse.setResult(result);
            oos.writeObject(rpcResponse);
            oos.flush(); // 刷新缓冲区域
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
