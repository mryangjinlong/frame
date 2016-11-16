package com.design_pattern.iterator;

public class Cat {
	private int id;
	
	public Cat(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return id +" ";
	}
}
