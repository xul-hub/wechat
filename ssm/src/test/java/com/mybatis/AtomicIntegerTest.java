package com.mybatis;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class AtomicIntegerTest {
	
	private static AtomicInteger datas = new AtomicInteger(0);
	
	@Test
	public void test1() {
		for (int i = 0; i < 1000; i++) {
			
			new Runnable() {
				public void run() {
					datas.incrementAndGet();
				}
			}.run();
		}
		System.out.println(datas.getAndIncrement());
	}
	
	@Test
	public void test2() {
		for (int i = 0; i < 20; i++) {
			Thread t = new Thread(new Runner());
			System.out.println(t.getName());
			t.run();
		}
	}
}

class Runner implements Runnable {
	private static int data = 0;
    public void run() {
    	data++;
    	System.out.println(data);
    } 
}  






