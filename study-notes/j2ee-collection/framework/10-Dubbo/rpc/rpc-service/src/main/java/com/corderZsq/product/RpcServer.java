package com.corderZsq.product;

import com.coderZsq.product.common.RpcRequest;
import com.coderZsq.product.common.RpcResponse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class RpcServer implements ApplicationContextAware, InitializingBean {
    @Autowired
    private ApplicationContext applicationContext;

    // InitializingBean --> 可以完成容器中bean对象的属性的设置
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    // ApplicationContextAware --> 把上下文环境装配到容器中
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 提供socket服务
        try {
            // 1 创建一个ServerSocket对象
            ServerSocket serverSocket = new ServerSocket(7654);
            // 2 serverSocket 等待接收客户端连接
            while (true) {
                // 一直等待客户端连接
                Socket socket = serverSocket.accept(); // 阻塞方法
                System.out.println("服务端执行=========");

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            // 通过socket进行业务的处理
                            // 通过输入流对象就可以获取请求的相关参数
                            InputStream inputStream = socket.getInputStream();
                            ObjectInputStream ois = new ObjectInputStream(inputStream);
                            RpcRequest rpcRequest = (RpcRequest) ois.readObject(); // 阻塞, 读取对象
                            // 有请求对象 找到对应的bean对象, 通过反射调用
                            String className = rpcRequest.getClassName();
                            String methodName = rpcRequest.getMethodName();
                            Object[] parameters = rpcRequest.getParameters();
                            Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
                            // 1 获取到对应的bean对象
                            Object bean = applicationContext.getBean(className); // ProductServiceImpl
                            // 2 获取到对象的字节码
                            Class<?> clz = bean.getClass();
                            // 3 获取到调用的方法
                            Method method = null;
                            if (parameterTypes == null) {
                                method = clz.getMethod(methodName);
                            } else {
                                method = clz.getMethod(methodName, parameterTypes);
                            }
                            // 4 调用方法 返回调用结果
                            Object result = method.invoke(bean, parameters);
                            RpcResponse rpcResponse = new RpcResponse();
                            rpcResponse.setResult(result);
                            rpcResponse.setSuccess(true);
                            // 5 通过输出流把对象输出
                            OutputStream os = socket.getOutputStream();
                            ObjectOutputStream oos = new ObjectOutputStream(os);
                            oos.writeObject(rpcResponse);
                            oos.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
