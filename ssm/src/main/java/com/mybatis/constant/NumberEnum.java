package com.mybatis.constant;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public enum NumberEnum {
	SPRING(1), SUMMER(2), AUTUMN(3),WINTER(4);
	
	private int seq;

	private NumberEnum(int seq) {
		this.seq = seq;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public static void main(String[] args) {
		String [] arrs = new String[] {"xu","Liang"};
		List<String> asList = Arrays.asList(arrs);
		Arrays.sort(arrs);
		for (Iterator<String> iterator = asList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		//
//		System.out.println(NumberEnum.SPRING.getSeq());
	}
}
