package com.design_pattern.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface InvocationHandler {
	public void invoke(Object proxy,Method method,Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
