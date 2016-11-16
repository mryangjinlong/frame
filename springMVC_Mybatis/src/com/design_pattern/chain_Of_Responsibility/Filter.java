package com.design_pattern.chain_Of_Responsibility;

public interface Filter {
	public void doFilter(Request request ,Response response , FilterChain chain);
}
