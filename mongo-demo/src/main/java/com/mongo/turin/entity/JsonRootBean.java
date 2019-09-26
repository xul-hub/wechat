package com.mongo.turin.entity;

public class JsonRootBean {
	
	private int groupType;
    private String resultType;
    private Values values;
    
	public int getGroupType() {
		return groupType;
	}
	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public Values getValues() {
		return values;
	}
	public void setValues(Values values) {
		this.values = values;
	}
    
    
}
