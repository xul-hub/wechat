package com.mybatis.entity.model;

public class ResultStateDTO {

	private Integer stateCode;
	private String erroMsg;

	public Integer getStateCode() {
		return stateCode;
	}

	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}

	public String getErroMsg() {
		return erroMsg;
	}

	public void setErroMsg(String erroMsg) {
		this.erroMsg = erroMsg;
	}

	@Override
	public String toString() {
		return "ResultStateDTO [stateCode=" + stateCode + ", erroMsg=" + erroMsg + "]";
	}
}
