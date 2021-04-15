package cn.wolfcode.spring.test.aop.early;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leon
 */
@Configuration
public class AopTestConfig {

	/**
	 * 前置通知
	 *
	 * @return
	 */
	@Bean
	public UserLogAdvice userLogAdvice() {
		return new UserLogAdvice();
	}

	/**
	 * 环绕通知
	 *
	 * @return
	 */
	@Bean
	public UserLogInterceptor userLogInterceptor() {
		return new UserLogInterceptor();
	}

	/**
	 * Advisor 你也可以理解为 Aspect
	 *
	 * @return
	 */
	@Bean
	public NameMatchMethodPointcutAdvisor userLogAdvisor() {
		NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
		// 设置 advice
		advisor.setAdvice(userLogAdvice());
		// 设置 pointcut 匹配方式（此 advisor 的 pointcut 实现就是采用 NameMatchMethodPointcut）
		// 因此为其设置需要匹配的方法名即可，可以设置多个
		advisor.setMappedNames("login");
		return advisor;
	}

//	/**
//	 * 配置代理工厂 bean
//	 *
//	 * @param userServiceImpl
//	 * @return
//	 */
//	@Bean
//	public ProxyFactoryBean userServiceProxy(IUserService userServiceImpl) {
//		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
//
//		// 配置增强方法，会按照参数顺序执行
////		proxyFactoryBean.setInterceptorNames("userLogAdvice", "userLogInterceptor");
//
//		// 基于 advisor 来进行方法级别的控制
//		proxyFactoryBean.setInterceptorNames("userLogAdvisor");
//
//		// 关联真实对象
//		proxyFactoryBean.setTarget(userServiceImpl);
//		return proxyFactoryBean;
//	}

	@Bean
	public BeanNameAutoProxyCreator autoProxyCreator() {
		BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
		// 设置要被代理的 beanNames，可以用通配符 *
		beanNameAutoProxyCreator.setBeanNames("user*");
		// 与上面的配置类似，也可以配置 advice、interceptor 或者 advisor
		beanNameAutoProxyCreator.setInterceptorNames("userLogInterceptor");
		return beanNameAutoProxyCreator;
	}
}
