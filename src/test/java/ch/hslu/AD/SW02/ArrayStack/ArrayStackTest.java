package ch.hslu.AD.SW02.ArrayStack;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.hslu.AD.SW02.ArrayStack.ArrayStack;
import ch.hslu.AD.SW02.ArrayStack.StackFullException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class ArrayStackTest {

    /**
     * Rigourous Test :-)
     */
	@Test
    public void testCreatingStack() {
    	ArrayStack<String> stack = new ArrayStack<>(5);
    	assertTrue(stack.isEmpty());
    }
    
    @Test
    public void testAddingElements() throws Exception {
    	ArrayStack<String> stack = new ArrayStack<>(5);
    	stack.push("Hodor");
    	assertFalse(stack.isEmpty());
    }
    
    @Test
    public void testFillingStack() throws Exception {
    	ArrayStack<String> stack = new ArrayStack<>(1);
    	stack.push("Hodor");
    	assertTrue(stack.isFull());
    }
    
    @Test
    public void testPushAndPop() throws Exception {
    	ArrayStack<String> stack = new ArrayStack<>(3);
    	stack.push("toll");
    	stack.push("sind");
    	stack.push("Datenstrukturen");
    	
    	assertEquals("Datenstrukturen", stack.pop());
    	assertEquals("sind", stack.pop());
    	assertEquals("toll", stack.pop());
    }
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Test
    public void testStackFull() throws StackFullException {
    	ArrayStack<String> stack = new ArrayStack<>(1);
    	stack.push("Hodor");
    	
    	exception.expect(StackFullException.class);
    	stack.push("White Walker");
    }
    
    @Test
    public void testPeek() throws StackFullException {
    	ArrayStack<String> stack = new ArrayStack<>(1);
    	stack.push("Hodor");
    	assertEquals("Hodor", stack.peek());
    	assertFalse(stack.isEmpty());
    	assertEquals("Hodor", stack.pop());
    	assertTrue(stack.isEmpty());
    }
}
