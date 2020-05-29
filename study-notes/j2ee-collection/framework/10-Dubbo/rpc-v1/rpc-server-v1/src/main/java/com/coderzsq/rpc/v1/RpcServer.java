package com.coderzsq.rpc.v1;

import com.coderZsq.rpc.v1.common.RpcRequest;
import com.coderZsq.rpc.v1.common.RpcResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcServer {
    private int port;

    private ApplicationContext applicationContext;

    // 服务启动类 --> 启动一个ServerSocket
    @PostConstruct
    public void startup() {
        try {
            // 1 创建一个socket通信
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动成功, 等待客户端连接.....");
            // 2 接收客户端请求
            while (true) {
                Socket socket = serverSocket.accept(); // 等待客户端连接 如果没有客户端连接, 会阻塞在这里
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
                    log.error(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
