package com.mybatis.utils;

import java.util.Collection;

/**
 * 基于MemCache的一个队列实现
 * chenhl
 */
public class MemCacheQueue<T>{
	
	private static final String KEY_SIZE = "_size";
	
	private static final String KEY_OFFSET = "_offset";
	
	private static final String KEY = "_key";
	
	private static final String LOCK = "_lock";
	
	private String name ;
	
	public MemCacheQueue(String name) {
		this.name = name ;
//		MemcachedUtils.set( name + KEY_SIZE , 0);
//		MemcachedUtils.set( name + KEY_OFFSET , 0);
	}
	
	/**
	 * 向队列里添加一个元素
	 * @param t
	 */
	public void push(T t){
		lock();
		int size = getSize();
		MemcachedUtils.set( name + KEY + "_" + size, t);
		MemcachedUtils.set( name + KEY_SIZE , size + 1 );
		unlock();
	}
	
	public void pushAll(Collection<T> ts){
		lock();
		int _size = ts.size() ;
		int size = getSize();
		int c = 0 ;
		for( T t : ts){
			MemcachedUtils.set( name + KEY + "_" + (size + c++), t);
		}
		MemcachedUtils.set( name + KEY_SIZE , size + _size );
		unlock();
	}
	/**
	 * 读取队头
	 * @return
	 */
	public T peek(){
		int offset = getOffset();
		@SuppressWarnings("unchecked")
		T t = (T) MemcachedUtils.get( name + KEY + "_" + offset );
		return t ;
	}
	
	/**
	 * 读取并删除队头
	 * @return
	 */
	public T poll(){
		lock();
		T t = peek();
		int offset = getOffset();
		MemcachedUtils.delete( name + KEY + "_" + offset );
		MemcachedUtils.set( name + KEY_OFFSET , offset + 1 );
		unlock();
		return t;
	}
	
	public void clear(){
		lock();
		int size = getSize();
		int offset = getOffset();
		for( int i = 0 ; i < size ; i++){
			MemcachedUtils.delete(name + KEY + "_" + ( offset + i ) );
		}
		MemcachedUtils.delete( name + KEY_SIZE );
		MemcachedUtils.delete( name + KEY_OFFSET );
		unlock();
	}
	
	/**
	 * @return
	 */
	public int getSize() {
		Integer size = (Integer) MemcachedUtils.get(name+KEY_SIZE);
		if( size == null ){
			size = 0 ;
		}
		return size;
	}
	
	public int getOffset(){
		Integer off = (Integer) MemcachedUtils.get(name+KEY_OFFSET);
		if( off == null ){
			off = 0 ;
		}
		return off;
	}
	
	private void lock(){
		while(true){
			Long lock = MemcachedUtils.addOrDecr(name+LOCK,1);
			if( lock == 1 ) {
				return;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
		}
	}
	
	private void unlock(){
		MemcachedUtils.delete(name+LOCK);
	}

}