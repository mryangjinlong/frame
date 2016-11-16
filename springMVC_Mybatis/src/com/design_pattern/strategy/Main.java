package com.design_pattern.strategy;

public class Main {

	public static void main(String[] args) {
		Comparator<Cat> comparator = new CatIdComparator<Cat>();
		Cat[] cats = new Cat[]{new Cat(8,new CatIdComparator<Cat>()) , new Cat(2,comparator) , new Cat(3,comparator) , new Cat(1,comparator) , new Cat(6,comparator)};
		DataSortor.sort(cats);
		for (Cat cat : cats) {
			System.out.println(cat + "-");
		}
	}

}
