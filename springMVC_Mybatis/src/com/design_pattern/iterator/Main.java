package com.design_pattern.iterator;

public class Main {
	
	
	
	public static void main(String[] args) {
		 
		ArrayList<Cat> list = new ArrayList<Cat>();
		for(int i = 0  ; i < 15 ; i++){
			list.add(new Cat(i));
		}
		
		System.out.println(list.size());
		Iterator<Cat> it = list.iterator();
		while(it.hasNext()){
			Cat cat = it.next();
			System.out.println(cat);
		}
	}
	
		
}
