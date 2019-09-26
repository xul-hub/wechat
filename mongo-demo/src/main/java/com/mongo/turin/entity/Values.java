package com.mongo.turin.entity;

public class Values {
	
	private String text;

	public Values(String text) {
		super();
		this.text = text;
	}

	public Values() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Values [text=" + text + "]";
	}
	
}
