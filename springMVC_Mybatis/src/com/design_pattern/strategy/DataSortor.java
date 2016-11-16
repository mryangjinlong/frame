package com.design_pattern.strategy;

import  com.design_pattern.strategy.Comparable;
public class DataSortor {
	
		public static void sort(Object[] t) {
		    for(int i = t.length ; i > 0 ; i--){
		    	for(int j = 0 ; j< i-1 ; j++){
		    		Comparable c1 = (Comparable)t[j];
		    		Comparable c2 = (Comparable)t[j+1];
		    		if(c1.compareTo(c2) == 1){
		    			Comparable temp = c1;
		    			c1 = c2;
		    			c2 = temp;
		    			t[j] = c1;
		    			t[j+1] = c2;
		    		}
		    	}
		    }
		}
}
