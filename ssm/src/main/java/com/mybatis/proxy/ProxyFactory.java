package com.mybatis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
* @ClassName: ProxyFactory.java
* @Description: 动态代理工厂
*
* @version: v1.0.0
* @author: xul
* @date: 2019年1月25日 上午11:17:05 
*/
public class ProxyFactory {
	
	private Object target;

	public ProxyFactory(Object target) {
		super();
		this.target = target;
	}
	
	public Object getProxyInstance() {
		/*
		 * 1.指定当前目标对象使用类加载器,获取加载器的方法是固定的
		 * 2.目标对象实现的接口的类型,使用泛型方式确认类型
		 * 3.事件处理,执行目标对象的方法时,会触发事件处理器的方法,会把当前执行目标对象的方法作为参数传入
		 */
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				Object invokeValue = method.invoke(target, args);
				return invokeValue;
			}
		});
	}
}
