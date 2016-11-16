package com.design_pattern.chain_Of_Responsibility;

public class Request {
	private String requestMsg;

	public Request(String msg){
		this.requestMsg = msg;
	}
	
	public String getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}

	
}
