package com.proxy;

import org.aspectj.lang.ProceedingJoinPoint;

public class AopSpringByXml {
	
	public void before(){
		System.out.println("before method  by  xml");
	}
	
	public void after(){
		System.out.println("after method   by xml");
	}
	
	public void around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("around method   by xml    11111111");
		pjp.proceed();
		System.out.println("around method   by  xml    22222222");
	}
}
