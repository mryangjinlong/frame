package com.design_pattern.proxy;

public class Main {

	public static void main(String[] args) throws Exception{
		Tank tank = new Tank();
		InvocationHandler invationHandler = new TimeHandler(tank);
		Moveable moveable = (Moveable)Proxy.newProxyInstance(Moveable.class,invationHandler);
		moveable.move();
	}

}
