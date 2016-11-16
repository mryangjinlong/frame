package com.design_pattern.chain_Of_Responsibility;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
	
	private List<Filter> filters = new ArrayList<Filter>();
	
	private int index = 0 ;
	
	
	public FilterChain addFilter(Filter f ){
		filters.add(f);
		return this;
	}
	
	public void doFilter(Request request, Response response) {
		if(index == filters.size()) return;
		Filter f = filters.get(index);
		index ++;
		f.doFilter(request, response, this);
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
}
