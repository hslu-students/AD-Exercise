package ch.hslu.AD.SW2.RingBufferQueue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

/**
 * Unit test for simple App.
 */
public class RingBufferQueueTest {
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Rigorous Test :-)
     */
	@Test
    public void testCreation() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(9);
    	assertEquals(9, queue.size());
    	assertTrue(queue.isEmpty());
    }
	
	@Test
	public void testWritingElements() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(9);
    	queue.enqueue('J');
    	queue.enqueue('A');
    	queue.enqueue('V');
    	queue.enqueue('A');
    	queue.enqueue('S');
    	queue.enqueue('U');
    	queue.enqueue('C');
    	queue.enqueue('K');
    	queue.enqueue('S');
    	
    	assertEquals(9, queue.used());
    	assertTrue(queue.isFull());
	}
	
	@Test
	public void testReadingElements() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(9);
    	queue.enqueue('J');
    	queue.enqueue('A');
    	queue.enqueue('V');
    	queue.enqueue('A');
    	queue.enqueue('S');
    	queue.enqueue('U');
    	queue.enqueue('C');
    	queue.enqueue('K');
    	queue.enqueue('S');
    	
    	assertEquals(new Character('J'), queue.dequeue());
    	assertEquals(new Character('A'), queue.dequeue());
    	assertEquals(new Character('V'), queue.dequeue());
    	assertEquals(new Character('A'), queue.dequeue());
    	assertEquals(new Character('S'), queue.dequeue());
    	assertEquals(new Character('U'), queue.dequeue());
    	assertEquals(new Character('C'), queue.dequeue());
    	assertEquals(new Character('K'), queue.dequeue());
    	assertEquals(new Character('S'), queue.dequeue());
	}
	
	@Test
	public void testStringRepr() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(9);
    	queue.enqueue('J');
    	queue.enqueue('A');
    	queue.enqueue('V');
    	queue.enqueue('A');
    	queue.enqueue('S');
    	queue.enqueue('U');
    	queue.enqueue('C');
    	queue.enqueue('K');
    	queue.enqueue('S');
    	
    	assertEquals("| J | A | V | A | S | U | C | K | S |", queue.toString().trim());
	}
	
	@Test
	public void testOveflow() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(4);
    	queue.enqueue('J');
    	queue.enqueue('A');
    	queue.enqueue('V');
    	queue.enqueue('A');
    	
    	exception.expect(BufferOverflowException.class);
    	queue.enqueue('S');
	}
	
	@Test
	public void testUnderflow() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(4);

    	exception.expect(BufferUnderflowException.class);
    	queue.dequeue();
	}
	
	@Test
	public void testUnderflow2() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(4);
    	queue.enqueue('J');
    	queue.enqueue('O');
    	queue.enqueue('H');
    	queue.dequeue();
    	queue.dequeue();
    	queue.dequeue();

    	exception.expect(BufferUnderflowException.class);
    	queue.dequeue();
	}
	
	@Test
	public void testReuse() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(4);
    	queue.enqueue('J');
    	queue.enqueue('O');
    	queue.enqueue('H');
    	queue.enqueue('N');
    	
    	assertEquals(new Character('J'), queue.dequeue());
    	queue.enqueue('S');
    	assertEquals("| S | O | H | N |", queue.toString().trim());
    	
    	assertEquals(new Character('O'), queue.dequeue());
    	assertEquals(new Character('H'), queue.dequeue());
    	assertEquals(new Character('N'), queue.dequeue());
    	
    	queue.enqueue('N');
    	queue.enqueue('O');
    	queue.enqueue('W');
    	assertEquals("| S | N | O | W |", queue.toString().trim());
    	
    	assertEquals(new Character('S'), queue.dequeue());
    	assertEquals(new Character('N'), queue.dequeue());
    	assertEquals(new Character('O'), queue.dequeue());
    	assertEquals(new Character('W'), queue.dequeue());
    	
    	assertTrue(queue.isEmpty());
	}
	
	@Test
	public void testContains() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(10);
    	queue.enqueue('J');
    	queue.enqueue('O');
    	queue.enqueue('H');
    	queue.enqueue('N');
    	
    	assertFalse(queue.contains('A'));
    	assertTrue(queue.contains('O'));
	}
	
	@Test
	public void testUsedSize() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(4);
    	assertEquals(0, queue.used());
    	queue.enqueue('J');
    	assertEquals(1, queue.used());
    	queue.enqueue('O');
    	assertEquals(2, queue.used());
    	queue.enqueue('H');
    	assertEquals(3, queue.used());
    	queue.enqueue('N');
    	assertEquals(4, queue.used());
    	queue.dequeue();
    	assertEquals(3, queue.used());
    	queue.dequeue();
    	assertEquals(2, queue.used());
    	queue.enqueue('S');
    	assertEquals(3, queue.used());
    	queue.enqueue('N');
    	assertEquals(4, queue.used());
    	queue.dequeue();
    	assertEquals(3, queue.used());
    	queue.dequeue();
    	assertEquals(2, queue.used());
    	queue.enqueue('O');
    	assertEquals(3, queue.used());
    	queue.enqueue('W');
    	assertEquals(4, queue.used());
    	queue.dequeue();
    	assertEquals(3, queue.used());
    	queue.dequeue();
    	assertEquals(2, queue.used());
    	queue.dequeue();
    	assertEquals(1, queue.used());
    	queue.dequeue();
    	assertEquals(0, queue.used());
	}
}
