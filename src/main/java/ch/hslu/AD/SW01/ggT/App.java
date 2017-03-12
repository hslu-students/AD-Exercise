package ch.hslu.AD.SW01.ggT;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	int a = 28;
    	int b = 68;
    	
    	System.out.println(ggtIterativ1(a, b));
    	System.out.println(ggtIterativ2(a, b));
    	System.out.println(ggtRekursiv(a, b));
    }
    
    /**
     * Iterative Variante 1 um ggT von zwei Zahlen zu berechnen.
     * 
     * Iterationen: 
     * 
     * | Iteration | IN: a | IN: b | a > b | OUT: a | OUT: b |
     * | 0         | 28    | 68    | false | 28     | 40     |
     * | 1         | 28    | 40    | false | 28     | 12     |
     * | 2         | 28    | 12    | true  | 16     | 12     |
     * | 3         | 16    | 12    | true  | 4      | 12     |
     * | 4         | 4     | 12    | false | 4      | 8      |
     * | 5         | 4     | 8     | false | 4      | 4      |  <-- while condition fails because a == b
     * 
     * Memory usage:
     * - thread's stack frame size for method call
     * - size of two int arguments for parameter a and b
     *
     */
    public static int ggtIterativ1(int a, int b) {
    	while(a != b) {
    		if(a > b) {
    			a = a - b;
    		} else {
    			b = b - a;
    		}
    	}
    	return a;
    }

    /**
     * Iterative Variante 2 um ggT von zwei Zahlen zu berechnen.
     * 
     * Iterationen: 
     * 
     * | Iteration | IN: a | IN: b | a > b | OUT: a | OUT: b |
     * | 0         | 28    | 68    | false | 28     | 12     |
     * | 1         | 28    | 12    | true  | 4      | 12     |
     * | 2         | 4     | 12    | false | 4      | 0      |  <-- while condition fails because b == 0
     *
     * Memory usage:
     * - thread's stack frame size for method call
     * - size of two int arguments for parameter a and b
     * 
     */
    public static int ggtIterativ2(int a, int b) {
    	while((a != b ) && (b != 0)) {
    		if(a > b) {
    			a = a % b;
    		} else {
    			b = b % a;
    		}
    	}
    	
    	return a + b; // One of those numbers is 0
    }
    
    /**
     * Rekursive Variante um ggT von zwei Zahlen zu berechnen.
     * 
     * Rekursionen:
     * 
     * | Rekursion | IN: a | IN: b | a > b | a < b | OUT: a | OUT: b |
     * | 0         | 28    | 68    | false | true  | 28     | 40     |
     * | 1         | 28    | 40    | false | true  | 28     | 12     |
     * | 2         | 28    | 12    | true  | N/A   | 16     | 12     |
     * | 3         | 16    | 12    | true  | N/A   | 4      | 12     |
     * | 4         | 4     | 12    | false | true  | 4      | 8      |
     * | 5         | 4     | 8     | false | true  | 4      | 4      |
     * | 6         | 4     | 4     | false | false | 4      | 4      |  <-- recursion end due to not calling ggtRekursive another time
     * 
     *  Memory usage:
     *  - 7 * thread's stack frame size for method calls
     *  - 7 * two int arguments for parameter a and b
     * 
     */
    public static int ggtRekursiv(final int a, final int b) {
    	if(a > b) {
    		return ggtRekursiv(a - b, b);
    	} else {
    		if(a < b) {
    			return ggtRekursiv(a, b - a);
    		} else {
    			return a;
    		}
    	}
    }
}
