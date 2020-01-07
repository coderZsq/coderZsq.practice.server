package cn.wolfcode.tx;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@SuppressWarnings("all")
//事务的增强操作
public class TransactionManagerAdvice implements java.lang.reflect.InvocationHandler {

	private Object target;//真实对象(对谁做增强)
	private TransactionManager txManager;//事务管理器(模拟)

	public void setTxManager(TransactionManager txManager) {
		this.txManager = txManager;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	//创建一个代理对象
	public <T> T getProxyObject() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), //类加载器,一般跟上真实对象的类加载器
				target.getClass().getInterfaces(), //真实对象所实现的接口(JDK动态代理必须要求真实对象有接口)
				this);//如何做事务增强的对象
	}

	//如何为真实对象的方法做增强的具体操作
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getName().startsWith("get") || method.getName().startsWith("list")) {
			return method.invoke(target, args);//放行
		}
		
		Object ret = null;
		txManager.begin();
		try {
			//---------------------------------------------------------------
			ret = method.invoke(target, args);//调用真实对象的方法
			//---------------------------------------------------------------
			txManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			txManager.rollback();
		}
		return ret;
	}
}
