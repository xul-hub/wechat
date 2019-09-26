package com.mybatis.entity;

import java.lang.reflect.Field;

import com.mybatis.annotation.MyFirstAnnotation;

import lombok.Data;

@Data
public class Test {
	private Long id;
	@MyFirstAnnotation(value = "fail")
	private String message;
	
	public static void main(String[] args) {
		Field[] fields = Test.class.getFields();
		for (Field field : fields) {
			MyFirstAnnotation annotation = field.getAnnotation(MyFirstAnnotation.class);
			System.out.println(annotation.toString());
		}
	}
}
