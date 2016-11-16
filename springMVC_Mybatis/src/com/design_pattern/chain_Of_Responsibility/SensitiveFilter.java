package com.design_pattern.chain_Of_Responsibility;

public class SensitiveFilter implements Filter{

	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		String str = request.getRequestMsg();
		str = str.replaceAll("敏感", "超级敏感");
		request.setRequestMsg(str);
		chain.doFilter(request, response);
		response.setResponseMsg(response.getResponseMsg()+"---SensitiveFilter");
	}

}
