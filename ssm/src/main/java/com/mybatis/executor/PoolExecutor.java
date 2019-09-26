package com.mybatis.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PoolExecutor {
	
	public static void main(String[] args) {
//		newCachedThreadPool();
//		newFixedThreadPool();
		newScheduledThreadPool();
//		newSingleThreadExecutor();
	}
	/**
	 * newCachedThreadPool,是一种线程数量不定的线程池，并且其最大线程数为Integer.MAX_VALUE，这个数是很大的，一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	 * 但是线程池中的空闲线程都有超时限制，这个超时时长是60秒，超过60秒闲置线程就会被回收。调用execute将重用以前构造的线程(如果线程可用)。
	 * 这类线程池比较适合执行大量的耗时较少的任务，当整个线程池都处于闲置状态时，线程池中的线程都会超时被停止。
	 */
	public static void newCachedThreadPool() {
		ExecutorService mCachelThreadPool = Executors.newCachedThreadPool();
		for(int i = 0;i < 7;i++ ) {
			final int index = i;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mCachelThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("第" +index +"个线程" +Thread.currentThread().getName()); 
				}
			});
		}
	}
	
	/**
	 * newFixedThreadPool 创建一个指定工作线程数量的线程池，每当提交一个任务就创建一个工作线程，当线程 处于空闲状态时，
	 * 它们并不会被回收，除非线程池被关闭了，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列（没有大小限制）中。
	 * 由于newFixedThreadPool只有核心线程并且这些核心线程不会被回收，这样它更加快速底相应外界的请求。
	 */
	public static void newFixedThreadPool() {
		ExecutorService mFixedThreadPool = Executors.newFixedThreadPool(5);
		for(int i = 0;i < 7;i++ ) {
			final int index = i;
			mFixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("时间是:"+System.currentTimeMillis()+"第" +index +"个线程" +Thread.currentThread().getName()); 
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				 }
			});
			
		}
	}
	
	/**
	 * newScheduledThreadPool 创建一个线程池，它的核心线程数量是固定的，而非核心线程数是没有限制的，并且当非核心线程闲置时会被立即回收，它可安排给定延迟后运行命令或者定期地执行。
	 * 这类线程池主要用于执行定时任务和具有固定周期的重复任务。
	 */
	public static void newScheduledThreadPool(){
		//设置池中核心数量是2
        ScheduledExecutorService mScheduledThreadPool = Executors.newScheduledThreadPool(2);  
        System.out.println("现在的时间:"+System.currentTimeMillis());
        mScheduledThreadPool.schedule(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("现在的时间:"+System.currentTimeMillis());
			}
		}, 4, TimeUnit.SECONDS);//这里设置延迟4秒执行   ..... },2, 3,TimeUnit.SECONDS);//这里设置延迟2秒后每3秒执行一次
	}
	
	/**
	 * newSingleThreadExecutor这类线程池内部只有一个核心线程，以无界队列方式来执行该线程，这使得这些任务之间不需要处理线程同步的问题，
	 * 它确保所有的任务都在同一个线程中按顺序中执行，并且可以在任意给定的时间不会有多个线程是活动的。
	 */
	public static void newSingleThreadExecutor(){
		ExecutorService mSingleThreadPool = Executors.newSingleThreadExecutor();     
        for(int i = 0;i < 7;i++) {
        	final int number = i;
        	mSingleThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("现在的时间:"+System.currentTimeMillis()+"第"+number+"个线程");
	                try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
        	
        }
	}
}
