package com.hoyatod.util;

import com.alibaba.fastjson.JSON;

public class APIData {
	
	private boolean status;
	private String callback;
	private Object data;
	
	public APIData(boolean status,Object data,String callback){
		this.status = status;
		this.data = data;
		this.callback = callback == null ? "" : callback;
	}
	
	public String toString(){
		return StringUtils.isNotBlank(callback) ? "{\"status\":\""+status+"\",\"data\":"+JSON.toJSONString(data)+"}"
				: callback+"({\"status\":"+status+",\"data\":"+JSON.toJSONString(data)+"})";
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}
	
	
}
