package com.design_pattern.strategy;

import  com.design_pattern.strategy.Comparable;
public class Cat implements Comparable<Cat>{
	private int id;
	private Comparator<Cat> comparator;   //比较策略
	
	
	public Cat(int id , Comparator<Cat> comparator) {
		super();
		this.id = id;
		this.comparator = comparator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id){
		this.id = id;
	}
	
	@Override
	public String toString() {
		return id +" ";
	}

	@Override
	public int compareTo(Cat t) {
		return comparator.compare(this, t);
	}
	
	
	
	
}
