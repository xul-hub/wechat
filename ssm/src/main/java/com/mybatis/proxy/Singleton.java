package com.mybatis.proxy;

/**
* @ClassName: Singleton.java
* @Description: 单例模式
*
* @version: v1.0.0
* @author: xul
* @date: 2019年1月28日 上午9:26:28 
*/
public class Singleton {

    private Singleton() {}

    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}

//同步线程安全的 线程同步
/*class Singleton1{
	
	 private static volatile Singleton1 singleton1;

	    private Singleton1() {}

	    public static Singleton1 getInstance() {
	        if (singleton1 == null) {
	            synchronized (Singleton1.class) {
	                if (singleton1 == null) {
	                	singleton1 = new Singleton1();
	                }
	            }
	        }
	        return singleton1;
	    }
}*/