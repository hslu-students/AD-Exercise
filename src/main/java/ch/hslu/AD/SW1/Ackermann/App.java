package ch.hslu.AD.SW1.Ackermann;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    }
    
    /**
     * 
     * Fuer ack(0, 4)
     * | Rekursion | IN: m | IN: n | m == 0 | n == 0 | OUT: m | OUT: n |
     * | 0         | 0     | 4     | true   | N/A    | 5      | 4      |  <-- Recursion end due to m == 0 -> Result: 5
     * 
     * 
     * Fuer ack(1, 2)
     *                | Rekursion | IN: m | IN: n | m == 0 | n == 0 | OUT: m | OUT: n       |
     *                | 0         | 1     | 2     | false  | false  | 0      | ack(1, 1): x |
     * ack(1, 1) ->   | 1         | 1     | 1     | false  | false  | 0      | ack(1, 0): y |
     * ack(1, 0) ->   | 2         | 1     | 0     | false  | true   | 0      | 1            |
     * ack(0, 1) ->   | 3         | 0     | 1     | true   | N/A    | 0      | 2            |
     * call y:        | 4         | 0     | 2     | true   | N/A    | 0      | 3            |
     * call x:        | 5         | 0     | 3     | true   | N/A    | 0      | 4            | <-- Recursion end due to m == 0 -> Result: 4
     *    
     *                
     * Fuer ack(2, 2)
     * 
     * Max frames on stack: 8 when reaching recursion 21
     * Total recursions: 27
     * 
     * Ist nicht primitiv rekursiv und nicht linear weil die Rekursionsaufrufe verschachtelt sind.
     */
    public static long ack(final long m, final long n) {
    	if(m == 0) {
    		return n + 1;
    	}
    	
    	if(n == 0) {
    		return ack(m - 1, 1);
    	}
    	
    	return ack(m - 1, ack(m, n - 1));
    }
}
