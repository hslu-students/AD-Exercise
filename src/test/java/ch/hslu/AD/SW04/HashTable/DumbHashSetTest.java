package ch.hslu.AD.SW04.HashTable;

import org.junit.Test;
import ch.hslu.AD.SW04.HashTable.DumbHashSet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class DumbHashSetTest {

    /**
     * Rigourous Test :-)
     */
	@Test
    public void testAdd() {
    	DumbHashSet<Integer> set = new DumbHashSet<>();
    	assertTrue(set.add(1));
    	assertTrue(set.add(2));
    	assertTrue(set.add(1));
    }
	
	@Test
	public void testContains() {
    	DumbHashSet<Integer> set = new DumbHashSet<>();
    	assertFalse(set.contains(1));
    	set.add(1);
    	assertTrue(set.contains(1));
	}
	
	@Test
	public void testRemove() {
    	DumbHashSet<Integer> set = new DumbHashSet<>();
    	set.add(1);
    	assertTrue(set.contains(1));
    	set.remove(1);
    	assertFalse(set.contains(1));
	}
}
