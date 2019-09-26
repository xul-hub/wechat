package ssh;

import java.util.Vector;

import org.junit.Test;

public class EmojiTest {
	
	public void test1() {
		
	}
	
	@Test
	public void vectorTest(){
	    Vector<String> vector = new Vector<String>();
	    for(int i = 0 ; i < 10 ; i++){
	        vector.add(i + "");
	    }
	    long start = System.currentTimeMillis();
	    for (String str : vector) {
			System.out.println(str);
		}
	    long end = System.currentTimeMillis();
	    System.out.printf( "大约%s秒",end - start);
	}
}
