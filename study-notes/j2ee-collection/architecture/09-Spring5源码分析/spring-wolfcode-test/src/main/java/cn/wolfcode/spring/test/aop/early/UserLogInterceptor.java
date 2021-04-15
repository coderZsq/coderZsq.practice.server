package cn.wolfcode.spring.test.aop.early;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Leon
 */
public class UserLogInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("【执行方法】-----------------------Before invoke-----------------------");
		Object proceed = invocation.proceed();
		System.out.println("【执行方法】-----------------------After invoke-----------------------");
		return proceed;
	}
}
