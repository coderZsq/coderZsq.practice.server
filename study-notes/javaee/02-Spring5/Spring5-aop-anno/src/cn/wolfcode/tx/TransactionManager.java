package cn.wolfcode.tx;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//模拟事务管理器

@Component
@Aspect //配置一个切面
public class TransactionManager {
	
	//XML:<aop:pointcut id="txPoint"  expression="execution(* cn.wolfcode.service.*Service.*(..))" />
	@Pointcut("execution(* cn.wolfcode.service.impl.*.*(..))")
	public void txPoint() {
	}
	
	//@Before("txPoint()")
	public void begin(JoinPoint jp) {
		System.out.println("开启事务");
	}
	
	//@AfterReturning("txPoint()")
	public void commit(JoinPoint jp) {
		System.out.println("提交事务");
	}

	//@AfterThrowing(value="txPoint()",throwing="ex")
	public void rollback(JoinPoint jp, Throwable ex) {
		System.out.println("回滚事务,异常信息:" + ex.getMessage());
	}

	//@After("txPoint()")
	public void close(JoinPoint jp) {
		System.out.println("释放资源");
	}

	@Around("txPoint()")
	public Object aroundMethod(ProceedingJoinPoint pjp) {
		Object ret = null;
		System.out.println("开启事务");
		try {
			ret = pjp.proceed();//调用真实对象的方法
			System.out.println("提交事务");
		} catch (Throwable e) {
			System.out.println("回滚事务,异常信息=" + e.getMessage());
		} finally {
			System.out.println("释放资源");
		}
		return ret;
	}
}
