package test.com.rhb.tcard;

import java.util.Calendar;

import org.junit.Test;

public class TestTcard {

	@Test
	public void test(){
		long t1 = System.currentTimeMillis();
		  try {  
	            Thread.currentThread().sleep(3160);  
	        } catch (InterruptedException e) {  
	            e.printStackTrace();  
	        }  
		  
		  long t2 = System.currentTimeMillis();
		  
		  System.out.println(t2 - t1);
		  
		  Calendar c = Calendar.getInstance();  
	        c.setTimeInMillis(t2 - t1);  
	  
	        System.out.println("∫ƒ ±: " +
	        		  c.get(Calendar.MINUTE) + "∑÷ "  
	                + c.get(Calendar.SECOND) + "√Î " 
	        		+ c.get(Calendar.MILLISECOND) + " ∫¡√Î");  
	}
}
