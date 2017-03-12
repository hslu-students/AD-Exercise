package ch.hslu.AD.SW02.RingBufferQueue;

/**
 * RingBufferQueue Playground
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	RingBufferQueue<Character> q = new RingBufferQueue<>(4);
    	q.enqueue('J');
    	System.out.println(q);
    	q.enqueue('O');
    	System.out.println(q);
    	q.enqueue('H');
    	System.out.println(q);
    	q.enqueue('N');
    	System.out.println(q);
    	
    	q.dequeue();
    	System.out.println(q);
    	
    	q.enqueue('S');
    	System.out.println(q);
    	q.dequeue();
    	System.out.println(q);
    	q.dequeue();
    	System.out.println(q);
    	q.dequeue();
    	System.out.println(q);
    	q.dequeue();
    	System.out.println(q);
    	// UNDERFLOW
    	/*q.read();
    	System.out.println(q);*/
    	q.enqueue('N');
    	System.out.println(q);
    	q.enqueue('O');
    	System.out.println(q);
    	q.enqueue('W');
    	System.out.println(q);
    	q.enqueue('S');
    	System.out.println(q);
    	// OVERFLOW
    	/*q.write('A');
    	System.out.println(q);*/
    }
}
