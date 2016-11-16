package com.design_pattern.proxy;

public class Tank implements Moveable {

	@Override
	public void move() {
		System.out.println("move.....");
	}

	@Override
	public boolean stop(int time) {
		System.out.println("stop  after "+ time+" second.....");
		return false;
	}

}
