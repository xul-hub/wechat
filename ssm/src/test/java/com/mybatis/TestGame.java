package com.mybatis;

import org.junit.Test;

public class TestGame {
	static int [] arr = new int [1000];
	
	/**
	 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
	 */
	//f(0) = 0,f(1) = 1，等价于 n<=1时，f(n) = n.
	public static int game1(int n) {
		if(n <= 1)
			return n;
		if(arr[n] != 0 ) {
			System.out.println("计算过直接返回：" + arr[n] );
			return arr[n];
		}else {
			arr[n] = game1(n-1) + game1(n-2);
			System.out.println("下标计算：" + n + "，下标计算：" + (n-1) + "，下标计算：" + (n-2));
			System.out.println("没有计算过开始计算：" + arr[n]);
			return arr[n];
		}
	}
	
	@Test
	public void test1() {
		System.out.println("总共：" + game1(8) + "跳法。");
		System.out.printf("%s","wo shi zhong guo ren");
	}
}
























