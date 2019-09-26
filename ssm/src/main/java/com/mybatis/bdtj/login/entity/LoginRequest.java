package com.mybatis.bdtj.login.entity;


public interface LoginRequest {
	String getUsername();
	String getToken();
	String getUuid();
	String getFunctionName();
	Object getRequest();
}

