package com.design_pattern.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {
	
	Object obejct = null;
	
	public TimeHandler(Object obejct) {
		super();
		this.obejct = obejct;
	}

	@Override
	public void invoke(Object proxy , Method method , Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	        System.out.println("代理方法开始");
	        
	        method.invoke(obejct, args);
	        
	        System.out.println("代理方法结束 ");
	}

	
}
