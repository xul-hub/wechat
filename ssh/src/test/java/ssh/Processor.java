package ssh;

import org.apache.http.HttpRequest;

//用户请求模块
public class Processor {
	
	/**
           * 发送秒杀事务到数据库队列.
     */
    public static void kill(BidInfo info) {
        DB.bids.add(info);
        System.out.print("kill" + " ");
    }
    public static void process(int sj) {
        BidInfo info = new BidInfo(sj);
        if (info != null) {
            kill(info);
            System.out.println("成功！");
        }
    }
    
    public static void main(String[] args) {
    	Processor p = new Processor();
    	for (int i = 0; i < 1000000; i++) {
    		p.process(getSJ());
		}
    	
	}
    
    private static int getSJ() {
    	double d = Math.random()*10;
		int x=1+(int)d;
		return x;
    }
    
}

class BidInfo {
    BidInfo(int num) {
        // Do something.
    	System.out.print("用户" + num + "\t");
    }
}