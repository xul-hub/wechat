package ssh;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.http.HttpRequest;


/**
* Java的并发包提供了三个常用的并发队列实现，分别是：ConcurrentLinkedQueue、LinkedBlockingQueue和ArrayBlockingQueue。
  
  1.ArrayBlockingQueue是初始容量固定的阻塞队列，我们可以用来作为数据库模块成功竞拍的队列，比如有10个商品，那么我们就设定一个10大小的数组队列。
  2.ConcurrentLinkedQueue使用的是CAS原语无锁队列实现，是一个异步队列，入队的速度很快，出队进行了加锁，性能稍慢。
  3.LinkedBlockingQueue也是阻塞的队列，入队和出队都用了加锁，当队空的时候线程会暂时阻塞。
  
*/

//由于我们的系统入队需求要远大于出队需求，
//一般不会出现队空的情况,所以我们可以选择ConcurrentLinkedQueue来作为我们的请求队列实现：
public class RequestQueue {
    public static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
}
