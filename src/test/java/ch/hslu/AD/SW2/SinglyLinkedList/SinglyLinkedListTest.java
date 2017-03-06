package ch.hslu.AD.SW2.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SinglyLinkedListTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SinglyLinkedListTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SinglyLinkedListTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAddingElements()
    {
    	SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    	assertEquals(0, list.size());
    	list.add(1);
    	list.add(2);
    	assertEquals(2, list.size());
    	list.add(3);
    	assertEquals(3, list.size());
    }
    
    public void testSimpleIterating() {
    	SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    	list.add(1);
    	list.add(2);
    	
    	Iterator<Integer> it = list.iterator();
    	
    	assertEquals(true, it.hasNext());
    	assertEquals(new Integer(2), it.next());
    	assertEquals(true, it.hasNext());
    	assertEquals(new Integer(1), it.next());
    	assertEquals(false, it.hasNext());
    }
    
    public void testAddingAtIndex() {
    	SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    	list.add(1);
    	list.add(2);
    	list.add(3);
    	Iterator<Integer> it = list.iterator();
    	assertEquals(new Integer(3), it.next());
    	assertEquals(new Integer(2), it.next());
    	assertEquals(new Integer(1), it.next());
    	list.add(1, 4);
    	it = list.iterator();
    	assertEquals(new Integer(3), it.next());
    	assertEquals(new Integer(4), it.next());
    	assertEquals(new Integer(2), it.next());
    	assertEquals(new Integer(1), it.next());
    	list.add(2, 5);
    	it = list.iterator();
    	assertEquals(new Integer(3), it.next());
    	assertEquals(new Integer(4), it.next());
    	assertEquals(new Integer(5), it.next());
    	assertEquals(new Integer(2), it.next());
    	assertEquals(new Integer(1), it.next());
    }
    
    public void testAddingCollection() {
    	SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    	List<Integer> boringList = new ArrayList<>();
    	boringList.add(1);
    	boringList.add(2);
    	boringList.add(3);
    	
    	list.addAll(boringList);
    	assertEquals(3, list.size());
    	Iterator<Integer> it = list.iterator();
    	assertEquals(new Integer(3), it.next());
    	assertEquals(new Integer(2), it.next());
    	assertEquals(new Integer(1), it.next());
    	assertEquals(false, it.hasNext());
    	
    	list.addAll(boringList);

    	assertEquals(6, list.size());
    	it = list.iterator();
    	assertEquals(new Integer(3), it.next());
    	assertEquals(new Integer(2), it.next());
    	assertEquals(new Integer(1), it.next());
    	assertEquals(new Integer(3), it.next());
    	assertEquals(new Integer(2), it.next());
    	assertEquals(new Integer(1), it.next());
    	assertEquals(false, it.hasNext());
    }
    
    public void testClear() {
    	SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    	list.add(1);
    	list.add(2);
    	assertEquals(2, list.size());
    	assertEquals(false, list.isEmpty());
    	list.clear();
    	assertEquals(0, list.size());
    	assertEquals(true, list.isEmpty());
    }
    
    public void testGetElementAtIndex() {
    	SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    	list.add(1);
    	list.add(2);
    	list.add(3);
    	list.add(4);
    	
    	assertEquals(new Integer(4), list.get(0));
    	assertEquals(new Integer(3), list.get(1));
    	assertEquals(new Integer(2), list.get(2));
    	assertEquals(new Integer(1), list.get(3));
    }
    
    public void testRemoveElement() {
    	SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    	list.add(1);
    	list.add(2);
    	list.add(3);
    	list.add(4);
    	
    	list.remove(new Integer(2));
    	assertEquals(3, list.size());
    	Iterator<Integer> it = list.iterator();
    	assertEquals(new Integer(4), it.next());
    	assertEquals(new Integer(3), it.next());
    	assertEquals(new Integer(1), it.next());
    	
    	list.remove(new Integer(1));
    	assertEquals(2, list.size());
    	it = list.iterator();
    	assertEquals(new Integer(4), it.next());
    	assertEquals(new Integer(3), it.next());
    }
    
    public void testRemoveNonExistingElement() {
    	SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    	list.add(1);
    	list.add(2);
    	list.add(3);
    	list.add(4);
    	
    	assertFalse(list.remove(new Integer(42)));
    	assertEquals(4, list.size());
    }
    
    public void testRemoveElementAtIndex() {
    	SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    	list.add(6);
    	list.add(5);
    	list.add(4);
    	list.add(3);
    	list.add(2);
    	list.add(1);
    	
    	assertEquals(new Integer(2), list.remove(1));
    	assertEquals(5, list.size());
    	Iterator<Integer> it = list.iterator();
    	assertEquals(new Integer(1), it.next());
    	assertEquals(new Integer(3), it.next());
    	assertEquals(new Integer(4), it.next());
    	assertEquals(new Integer(5), it.next());
    	assertEquals(new Integer(6), it.next());
    	
    	assertEquals(new Integer(1), list.remove(0));
    	assertEquals(4, list.size());
    	it = list.iterator();
    	assertEquals(new Integer(3), it.next());
    	assertEquals(new Integer(4), it.next());
    	assertEquals(new Integer(5), it.next());
    	assertEquals(new Integer(6), it.next());
    	
    	assertEquals(new Integer(6), list.remove(list.size() - 1));
    	assertEquals(3, list.size());
    	it = list.iterator();
    	assertEquals(new Integer(3), it.next());
    	assertEquals(new Integer(4), it.next());
    	assertEquals(new Integer(5), it.next());
    }
}
