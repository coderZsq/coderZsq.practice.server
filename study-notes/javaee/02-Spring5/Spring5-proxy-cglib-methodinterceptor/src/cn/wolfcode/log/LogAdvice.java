package cn.wolfcode.log;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodProxy;

//日志增强
public class LogAdvice implements org.springframework.cglib.proxy.MethodInterceptor {

	private Object target;//真实对象
	private LogUtil logUtil;

	public void setTarget(Object target) {
		this.target = target;
	}

	public void setLogUtil(LogUtil logUtil) {
		this.logUtil = logUtil;
	}

	//创建代理对象
	public <T> T getProxyObject() {
		return (T) Enhancer.create(target.getClass(), this);

	}

	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		logUtil.writeLog(method.getDeclaringClass().getName(), method.getName());
		Object ret = method.invoke(target, args);//调用真实对象的方法
		return ret;
	}

}
