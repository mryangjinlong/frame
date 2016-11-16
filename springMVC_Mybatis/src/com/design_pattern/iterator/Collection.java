package com.design_pattern.iterator;

public interface Collection<E> {
	
	void add(E e);
	int size();
	Iterator<E> iterator();
}
