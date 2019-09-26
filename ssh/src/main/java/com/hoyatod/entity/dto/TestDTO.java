package com.hoyatod.entity.dto;

import lombok.Data;
import okio.Timeout;

@Data
public class TestDTO {
	
	private Long tid;
	private String test_name;
	private String test_content;
	private Timeout create_time;
	
	public static void main(String[] args) {
		TestDTO dto = new TestDTO();
		System.out.println(dto.test_name);
	}
}
