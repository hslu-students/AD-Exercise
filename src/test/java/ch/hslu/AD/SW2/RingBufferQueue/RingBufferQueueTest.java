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
    	queue.write('J');
    	queue.write('A');
    	queue.write('V');
    	queue.write('A');
    	queue.write('S');
    	queue.write('U');
    	queue.write('C');
    	queue.write('K');
    	queue.write('S');
    	
    	assertEquals(9, queue.used());
    	assertTrue(queue.isFull());
	}
	
	@Test
	public void testReadingElements() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(9);
    	queue.write('J');
    	queue.write('A');
    	queue.write('V');
    	queue.write('A');
    	queue.write('S');
    	queue.write('U');
    	queue.write('C');
    	queue.write('K');
    	queue.write('S');
    	
    	assertEquals(new Character('J'), queue.read());
    	assertEquals(new Character('A'), queue.read());
    	assertEquals(new Character('V'), queue.read());
    	assertEquals(new Character('A'), queue.read());
    	assertEquals(new Character('S'), queue.read());
    	assertEquals(new Character('U'), queue.read());
    	assertEquals(new Character('C'), queue.read());
    	assertEquals(new Character('K'), queue.read());
    	assertEquals(new Character('S'), queue.read());
	}
	
	@Test
	public void testStringRepr() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(9);
    	queue.write('J');
    	queue.write('A');
    	queue.write('V');
    	queue.write('A');
    	queue.write('S');
    	queue.write('U');
    	queue.write('C');
    	queue.write('K');
    	queue.write('S');
    	
    	assertEquals("| J | A | V | A | S | U | C | K | S |", queue.toString().trim());
	}
	
	@Test
	public void testOveflow() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(4);
    	queue.write('J');
    	queue.write('A');
    	queue.write('V');
    	queue.write('A');
    	
    	exception.expect(BufferOverflowException.class);
    	queue.write('S');
	}
	
	@Test
	public void testUnderflow() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(4);

    	exception.expect(BufferUnderflowException.class);
    	queue.read();
	}
	
	@Test
	public void testUnderflow2() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(4);
    	queue.write('J');
    	queue.write('O');
    	queue.write('H');
    	queue.read();
    	queue.read();
    	queue.read();

    	exception.expect(BufferUnderflowException.class);
    	queue.read();
	}
	
	@Test
	public void testReuse() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(4);
    	queue.write('J');
    	queue.write('O');
    	queue.write('H');
    	queue.write('N');
    	
    	assertEquals(new Character('J'), queue.read());
    	queue.write('S');
    	assertEquals("| S | O | H | N |", queue.toString().trim());
    	
    	assertEquals(new Character('O'), queue.read());
    	assertEquals(new Character('H'), queue.read());
    	assertEquals(new Character('N'), queue.read());
    	
    	queue.write('N');
    	queue.write('O');
    	queue.write('W');
    	assertEquals("| S | N | O | W |", queue.toString().trim());
    	
    	assertEquals(new Character('S'), queue.read());
    	assertEquals(new Character('N'), queue.read());
    	assertEquals(new Character('O'), queue.read());
    	assertEquals(new Character('W'), queue.read());
    	
    	assertTrue(queue.isEmpty());
	}
	
	@Test
	public void testContains() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(10);
    	queue.write('J');
    	queue.write('O');
    	queue.write('H');
    	queue.write('N');
    	
    	assertFalse(queue.contains('A'));
    	assertTrue(queue.contains('O'));
	}
	
	@Test
	public void testUsedSize() {
    	RingBufferQueue<Character> queue = new RingBufferQueue<>(4);
    	assertEquals(0, queue.used());
    	queue.write('J');
    	assertEquals(1, queue.used());
    	queue.write('O');
    	assertEquals(2, queue.used());
    	queue.write('H');
    	assertEquals(3, queue.used());
    	queue.write('N');
    	assertEquals(4, queue.used());
    	queue.read();
    	assertEquals(3, queue.used());
    	queue.read();
    	assertEquals(2, queue.used());
    	queue.write('S');
    	assertEquals(3, queue.used());
    	queue.write('N');
    	assertEquals(4, queue.used());
    	queue.read();
    	assertEquals(3, queue.used());
    	queue.read();
    	assertEquals(2, queue.used());
    	queue.write('O');
    	assertEquals(3, queue.used());
    	queue.write('W');
    	assertEquals(4, queue.used());
    	queue.read();
    	assertEquals(3, queue.used());
    	queue.read();
    	assertEquals(2, queue.used());
    	queue.read();
    	assertEquals(1, queue.used());
    	queue.read();
    	assertEquals(0, queue.used());
	}
}
