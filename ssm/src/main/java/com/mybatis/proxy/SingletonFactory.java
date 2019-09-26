package com.mybatis.proxy;


/**
* @ClassName: SingletonFactory.java
* @Description: 枚举版单例模式
*
* @version: v1.0.0
* @author: xul
* @date: 2019年1月28日 上午9:40:31 
*/
public class SingletonFactory {
	
	private enum EnmuSingleton{
		
		Singleton;
		private Singleton2 singleton;
		
		private EnmuSingleton() {
			singleton = new Singleton2();
		}
		
		public Singleton2 getInstance(){
            return singleton;
        }
	}
	
	public static Singleton2 getInstance() {
        return EnmuSingleton.Singleton.getInstance();
    }
}

class Singleton2{

	public Singleton2() {
		super();
	}
	
}