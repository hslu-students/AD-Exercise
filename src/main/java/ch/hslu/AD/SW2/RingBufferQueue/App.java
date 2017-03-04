package ch.hslu.AD.SW2.RingBufferQueue;

/**
 * RingBufferQueue Playground
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	RingBufferQueue<Character> q = new RingBufferQueue<>(4);
    	q.write('J');
    	System.out.println(q);
    	q.write('O');
    	System.out.println(q);
    	q.write('H');
    	System.out.println(q);
    	q.write('N');
    	System.out.println(q);
    	
    	q.read();
    	System.out.println(q);
    	
    	q.write('S');
    	System.out.println(q);
    	q.read();
    	System.out.println(q);
    	q.read();
    	System.out.println(q);
    	q.read();
    	System.out.println(q);
    	q.read();
    	System.out.println(q);
    	// UNDERFLOW
    	/*q.read();
    	System.out.println(q);*/
    	q.write('N');
    	System.out.println(q);
    	q.write('O');
    	System.out.println(q);
    	q.write('W');
    	System.out.println(q);
    	q.write('S');
    	System.out.println(q);
    	// OVERFLOW
    	/*q.write('A');
    	System.out.println(q);*/
    }
}
