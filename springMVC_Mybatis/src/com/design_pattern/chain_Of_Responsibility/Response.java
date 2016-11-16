package com.design_pattern.chain_Of_Responsibility;

public class Response {
		private String responseMsg;

		public Response(String msg){
			this.responseMsg = msg;
		}
		public String getResponseMsg() {
			return responseMsg;
		}

		public void setResponseMsg(String responseMsg) {
			this.responseMsg = responseMsg;
		}

}
