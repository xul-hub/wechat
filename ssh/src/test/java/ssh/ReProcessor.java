package ssh;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpRequest;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.params.HttpParams;

public class ReProcessor {
	
    private static boolean reminds = true;
    private static int i = 10;
    private static void forbidden() {
        // Do something.
    }
    public static boolean checkReminds() {
        if (reminds) {
            if (i-- < 0) {// 远程检测是否还有剩余，该RPC接口应由数据库服务器提供，不必完全严格检查.
                reminds = false;
            }
        }
        return reminds;
    }
  /**
   * 每一个HTTP请求都要经过该预处理.
   */
    public static void preProcess(Object obj) {
        if (checkReminds()) {
            RequestQueue.queue.add(null);
            System.out.println("一个并发的队列");
        } else {
            forbidden();
            System.out.println("如果已经没有商品了，则直接驳回请求即可.");
        }
    }
    
    public static void main(String[] args) {
		preProcess(null);
	}
}
