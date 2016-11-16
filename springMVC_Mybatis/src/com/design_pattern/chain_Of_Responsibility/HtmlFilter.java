package com.design_pattern.chain_Of_Responsibility;

public class HtmlFilter implements Filter{

	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
			String str = request.getRequestMsg();
			str = str.replaceAll("<", "[")
					 .replaceAll(">", "]");
			request.setRequestMsg(str);
			chain.doFilter(request, response);
			response.setResponseMsg(response.getResponseMsg()+"---HtmlFilter");
	}

}
