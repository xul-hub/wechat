package ssh;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 
* @ClassName: CyclicBarrierTest.java
* @Description: 并发工具类
*
* @version: v1.0.0
* @author: xul
* @date: 2019年5月15日 上午9:50:57
* 
* CyclicBarrier试用与多线程结果合并的操作，用于多线程计算数据，最后合并计算结果的应用场景。
* 比如我们需要统计多个Excel中的数据，然后等到一个总结果。我们可以通过多线程处理每一个Excel，
* 执行完成后得到相应的结果，最后通过barrierAction来计算这些线程的计算结果，得到所有Excel的总和
* 
* 
 */
public class CyclicBarrierTest {

	private static CyclicBarrier cyclicBarrier;
	private static int count = 0;
	
	static class CyclicBarrierThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println(Thread.currentThread().getName() + ":run quickly;total:" + count++);
			try {
				cyclicBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		cyclicBarrier = new CyclicBarrier(10, new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("一起 run quickly！" + count);
			}
		});
		
		for (int i = 0; i < 10; i++) {
			new CyclicBarrierThread().start();
		}
	}
	
}
