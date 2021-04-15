package cn.wolfcode.spring.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Leon
 * @date 2021/3/30
 */
//@Aspect
//@Component
public class UserLogAspect {

	@Pointcut("execution(* cn.wolfcode.spring.test.service.impl.*.*(..))")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void before(JoinPoint joinPoint) {
		System.out.println("【切面：Before】->" + joinPoint.getTarget() + ", " + joinPoint.getSignature() + ", " + Arrays.toString(joinPoint.getArgs()));
	}

	@After("pointcut()")
	public void after(JoinPoint joinPoint) {
		System.out.println("【切面：After】->" + joinPoint.getTarget() + ", " + joinPoint.getSignature() + ", " + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(value = "pointcut()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		System.out.println("【切面：AfterReturning】->" + joinPoint.getTarget() + ", " + joinPoint.getSignature() + ", " + Arrays.toString(joinPoint.getArgs()));
	}

	@AfterThrowing(value = "pointcut()")
	public void afterThrowing(JoinPoint joinPoint) {
		System.out.println("【切面：afterThrowing】->" + joinPoint.getTarget() + ", " + joinPoint.getSignature() + ", " + Arrays.toString(joinPoint.getArgs()));
	}
}
