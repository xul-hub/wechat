package com.mongo.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
* @ClassName: Person.java
* @Description: 人物类
*
* @version: v1.0.0
* @author: xul
* @date: 2018年12月21日 下午2:02:42 
*/

@Document(collection="person")
public class Person {
	
	@Id
    private ObjectId id;
    private String name;
    private int age;
    private Address address;
    private String times;
    
	public Person() {
		super();
	}
	public Person(String name, int age, Address address,String times) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.times = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + ", times=" + times
				+ "]";
	}
    
}
