package com.mybatis.entity.model;

public class VoiceDTO {
	private String voicename;
	private String errmeg;
	
	public VoiceDTO(String voicename, String errmeg) {
		super();
		this.voicename = voicename;
		this.errmeg = errmeg;
	}
	
	public String getVoicename() {
		return voicename;
	}
	public void setVoicename(String voicename) {
		this.voicename = voicename;
	}
	public String getErrmeg() {
		return errmeg;
	}
	public void setErrmeg(String errmeg) {
		this.errmeg = errmeg;
	}
	
	
}
