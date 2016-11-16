package com.design_pattern.strategy;

public class CatIdComparator<T> implements Comparator<T> {

	@Override
	public int compare(T t1 , T t2) {
		Cat cat1 = (Cat)t1;
		Cat cat2 = (Cat)t2;
		if(cat1.getId() > cat2.getId()) return 1;
		else if(cat2.getId() > cat1.getId()) return -1;
		else return 0;
	}

}
