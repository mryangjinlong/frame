package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.model.AbstractEntity;
import com.model.Company;
import com.model.Entity;

/**
 * @author Administrator
 * 动态代理
 */
public class FirstInvocation implements InvocationHandler{
	private Object target;
	
	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target){
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		System.out.println("方法从这里开始 ");
		Object object = method.invoke(target, args);
		
		return object;
	}

	public static void main(String[] args) {
		Company company = new Company();
		FirstInvocation fi = new FirstInvocation();
		fi.setTarget(company);
		ClassLoader classLoader = company.getClass().getClassLoader();
		Class[] clazz =  company.getClass().getInterfaces();
		AbstractEntity companyProxy = 
				(AbstractEntity) (Proxy.newProxyInstance(classLoader , clazz , fi));
		
		companyProxy.print();
	}
}
