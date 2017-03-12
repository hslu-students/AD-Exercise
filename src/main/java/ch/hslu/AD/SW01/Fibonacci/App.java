package ch.hslu.AD.SW01.Fibonacci;

import java.util.Map;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    }
    
    
    public static int fiboRec1(final int n) {
    	if(n <= 1) {  // Rekursionsbasis
    		return n; // Rekursionsbasis
    	}
    	
    	// Rekursions Vorschrift -> n > 1
    	return fiboRec1(n - 1) + fiboRec1(n - 2); // Rekursions Vorschrift
    }
    
    public static Map<Integer, Integer> memoizedFib = new HashMap<Integer, Integer>();
    
    public static int fiboRec2(final int n) {
    	if(n <= 1) {  // Rekursionsbasis
    		return n; // Rekursionsbasis
    	}
    	
    	if (memoizedFib.containsKey(n)) {
    		return memoizedFib.get(n);
  	   	}
    	
    	// Rekursionsvorschrift
    	int tmp = fiboRec2(n - 1) + fiboRec2(n - 2); 
  	   	memoizedFib.put(n, tmp);
  	   	return tmp;
    }
    
    public static int fiboIter(int n) {
    	int prev = 0;
    	int curr = 1;
    	int tmp;
    	
    	for(int i = 0; i < n; i++) {
    		tmp = prev;
    		prev = curr;
    		curr += tmp;
    	}
    	return prev;
    }
}
