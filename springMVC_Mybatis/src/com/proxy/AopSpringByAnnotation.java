package com.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 用spring实现动态代理  注解的方式
 * @author Administrator
 *
 */
@Component
@Aspect
public class AopSpringByAnnotation {

	@Pointcut("execution(public * com.service..*.*(..))")
	public void pointcut() {}

	@Before("pointcut()")
	public void beforeMethod() {
		System.out.println("beforeMethod");
	}

	@After("pointcut()")
	public void afterMethod() {
		System.out.println("afterMethod");
	}

	@Around("pointcut()")
	public Object aroundMehthod(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("aroundMehthod111");
		Object object = pjp.proceed();
		System.out.println("aroundMehthod222");
		if(object == null) return false;
		return object;
	}
}
