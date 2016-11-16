package com.design_pattern.chain_Of_Responsibility;
/**
 * 演示责任链模式
 * @author Administrator
 *
 */
public class Main {
	public static void main(String[] args) {
		String str = "<script>  敏感";
		Request request = new Request(str);
		Response response = new Response("response");
		
		FilterChain fc = new FilterChain();
		fc.addFilter(new SensitiveFilter());
		fc.addFilter(new HtmlFilter());
		fc.doFilter(request, response);
		
		System.out.println(request.getRequestMsg());
		System.out.println(response.getResponseMsg());
	}
}
