package com.design_pattern.iterator;

public class ArrayList<E> implements Collection<E> {
	
	Object[] objects =  new Object[10];
	
	private int index = 0;
	
	@Override
	public void add(E o) {
		if(index < objects.length) 
			objects[index] = o;
		else{
			Object[] newObject = new Object[10*2];
			System.arraycopy(objects, 0, newObject, 0, objects.length);
			objects = newObject;
			objects[index] = o ;
		};
		index ++ ;
	}

	@SuppressWarnings("unchecked")
	public E get(int index){
		return (E) objects[index];
	}
	@Override
	public int size() {
		return index;
	}

	@Override
	public Iterator<E> iterator() {
		
		return new Iterator<E>() {
			private int currentIndex = 0 ;
			
			@Override
			public E next(){
				if(currentIndex == index) return null;
				
				@SuppressWarnings("unchecked")
				E o = (E) objects[currentIndex];
				currentIndex ++;
				return o;
			}
			
			@Override
			public boolean hasNext() {
				if(currentIndex < index)  return true;
				else return false;
			}
		};
	}

	
}
