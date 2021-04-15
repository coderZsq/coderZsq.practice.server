package cn.wolfcode.spring.test.aop.early;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Leon
 */
public class UserLogAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String name = method.getName();
		System.out.println("【执行方法】：[" + name + "]的<BeforeAdvice>，传入参数：" + Arrays.asList(args));
	}
}
