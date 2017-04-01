package ch.hslu.AD.SW06.BoundedBuffer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Unit test for simple App.
 */
public class BoundedBufferTest {

    /**
     * Rigourous Test :-)
     * @throws InterruptedException 
     */
	@Test
    public void testSequentialPut() throws InterruptedException {
    	BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
    	
    	assertTrue(buffer.empty());
    	assertFalse(buffer.full());
    	assertEquals(0, buffer.size());
    	
    	buffer.put(1);
    	buffer.put(2);
    	buffer.put(3);
    	buffer.put(4);
    	buffer.put(5);

    	assertFalse(buffer.empty());
    	assertEquals(5, buffer.size());
    	assertTrue(buffer.full());
    }
	
	/*@Test
    public void testSequentialPutTimeout() throws InterruptedException {
    	BoundedBuffer<Integer> buffer = new BoundedBuffer<>(2);
    	
    	assertTrue(buffer.empty());
    	assertFalse(buffer.full());
    	assertEquals(0, buffer.size());
    	
    	buffer.put(1, 1000);
    	buffer.put(2, 1000);

    	assertFalse(buffer.empty());
    	assertEquals(2, buffer.size());
    	assertTrue(buffer.full());
    	
    	buffer.put(3, 1000);

    	assertFalse(buffer.empty());
    	assertEquals(2, buffer.size());
    	assertTrue(buffer.full());
    }*/
	
	@Test
	public void testSequentialPutGet() throws InterruptedException {
		BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
    	
    	assertEquals(0, buffer.size());
    	
    	buffer.put(1);
    	assertEquals(1, buffer.size());
    	
    	buffer.put(2);
    	assertEquals(2, buffer.size());
    	
    	assertEquals(new Integer(1), buffer.get());
    	assertEquals(1, buffer.size());
    	
    	buffer.put(3);
    	assertEquals(2, buffer.size());
    	
    	buffer.put(4);
    	assertEquals(3, buffer.size());
    	
    	assertEquals(new Integer(2), buffer.get());
    	assertEquals(2, buffer.size());
    	
    	buffer.put(5);
    	assertEquals(3, buffer.size());
    	
    	assertEquals(new Integer(3), buffer.get());
    	assertEquals(2, buffer.size());
    	
    	assertEquals(new Integer(4), buffer.get());
    	assertEquals(1, buffer.size());
    	
    	assertEquals(new Integer(5), buffer.get());
    	assertEquals(0, buffer.size());
	}
	
	private class ConsumerElement<T> {
		public T element;
	}
	
	@Test
	public void testAsyncWaitGet() throws InterruptedException {
		BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
		
		ConsumerElement<Integer> c1Element = new ConsumerElement<>();
		ConsumerElement<Integer> c2Element = new ConsumerElement<>();
    	
    	// set up two consumers to read from buffer
		Thread c1 = new Thread(() -> {
			try {
				c1Element.element = buffer.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Thread c2 = new Thread(() -> {
			try {
				c2Element.element = buffer.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		// start consumers
		c1.start();
		c2.start();
		
		// give the threads a chance to get elements (case of wrong implementation)
		Thread.sleep(250);
		
		assertNull(c1Element.element);
		assertNull(c2Element.element);
		
		// produce a value and expect that it was consumed
		buffer.put(21);
		Thread.sleep(250);
		
		assertTrue(new Integer(21).equals(c1Element.element) ^ (new Integer(21).equals(c2Element.element)));
		
		buffer.put(42);
		Thread.sleep(250);
		assertTrue(new Integer(42).equals(c1Element.element) ^ new Integer(42).equals(c2Element.element));
		
		assertTrue((new Integer(42).equals(c1Element.element) && new Integer(21).equals(c2Element.element)) ^
				(new Integer(21).equals(c1Element.element) && new Integer(42).equals(c2Element.element)));
	}
	
	@Test
	public void testAsyncWaitPut() throws InterruptedException {
		BoundedBuffer<Integer> buffer = new BoundedBuffer<>(2);
		
		// fill buffer
		buffer.put(21);
		buffer.put(42);
		
		// set up two consumers to put elements
		Thread p1 = new Thread(() -> {
			try {
				buffer.put(314);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Thread p2 = new Thread(() -> {
			try {
				buffer.put(3141);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		// start producers
		p1.start();
		p2.start();
		
		// give the threads a chance to put elements (case of wrong implementation)
		Thread.sleep(250);
		
		assertTrue(buffer.full());
		
		// get the first inserted element from the buffer
		assertEquals(new Integer(21), buffer.get());
		
		// allow a producer to place it's element
		Thread.sleep(250);
		
		// buffer should be full
		assertTrue(buffer.full());
		
		// one of the producer threads should be exited
		assertTrue(p1.isAlive() ^ p2.isAlive());
		
		// get the second inserted element from the buffer
		assertEquals(new Integer(42), buffer.get());
		
		// allow the second producer to place it's element
		Thread.sleep(250);

		// buffer should be full
		assertTrue(buffer.full());
		
		// both producer should have exited
		assertFalse(p1.isAlive());
		assertFalse(p2.isAlive());
		
		// get all elements from the buffer
		Integer element1 = buffer.get();
		Integer element2 = buffer.get();
		
		// expect buffer to be empty
		assertTrue(buffer.empty());
		
		assertTrue((element1.equals(new Integer(314)) && element2.equals(new Integer(3141))) ^
				(element2.equals(new Integer(314)) && element1.equals(new Integer(3141))));
	}
	
	public boolean wasInterrupted = false;
	@Test
	public void testInterrupts() throws InterruptedException {
		BoundedBuffer<Integer> buffer = new BoundedBuffer<>(2);
		
		// fill buffer
		buffer.put(21);
		buffer.put(42);
		
		//Boolean wasInterrupted = new Boolean(false);
		
		// setup thread with producer
		Thread p1 = new Thread(() -> {
			try {
				buffer.put(314);
			} catch (InterruptedException e) {
				wasInterrupted = true;
			}
		});
		
		// start producer
		p1.start();
		
		// Interrupt buffer
		p1.interrupt();
		
		// give the producer thread the chance to exit
		Thread.sleep(250);
		
		// check if thread exited
		assertFalse(p1.isAlive());
		
		// check if it really was interrupted
		assertTrue(wasInterrupted);
	}
}
