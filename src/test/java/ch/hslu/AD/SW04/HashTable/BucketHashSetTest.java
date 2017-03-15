package ch.hslu.AD.SW04.HashTable;

import org.junit.Test;
import ch.hslu.AD.SW04.HashTable.DumbHashSet;
import ch.hslu.AD.SW04.HashTable.SoundedHashSet;
import ch.hslu.AD.SW04.HashTable.Playground.DumbItem;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit test for simple App.
 */
public class BucketHashSetTest {

	public class DumbItem {
		private int hashCode;
		private int value;
		
		public DumbItem(int value, int hashCode) {
			this.hashCode = hashCode;
			this.value = value;
		}
		
		@Override
		final public boolean equals(Object other) {
			if(this == other) {
				return true;
			}
			
			if(!(other instanceof DumbItem)) {
				return false;
			}
			
			DumbItem o = (DumbItem) other;
			return this.value == o.value;
		}
		
		@Override
		public int hashCode() {
			return this.hashCode;
		}
		
		@Override
		public String toString() {
			return String.format("%d (%d)", value, hashCode);
		}
	}
	
    /**
     * Rigourous Test :-)
     */
	@Test
    public void testAdd() {
    	BucketHashSet<Integer> set = new BucketHashSet<>();
    	assertTrue(set.add(1));
    	assertTrue(set.add(2));
    	assertTrue(set.add(3));
    	
    	assertEquals(3, set.size());
    }
	
	@Test
	public void testContains() {
		BucketHashSet<DumbItem> set = new BucketHashSet<>();
    	set.add(new DumbItem(10, 10));
    	assertTrue(set.contains(new DumbItem(10, 10)));
    	assertFalse(set.contains(new DumbItem(10, 11)));
    	set.add(new DumbItem(10, 11));
    	assertTrue(set.contains(new DumbItem(10, 11)));
	}
	
	@Test
	public void testContainsCollisions() {
		BucketHashSet<DumbItem> set = new BucketHashSet<>();
    	set.add(new DumbItem(10, 10));
    	set.add(new DumbItem(11, 10));
    	set.add(new DumbItem(12, 10));
    	
    	assertTrue(set.contains(new DumbItem(10, 10)));
    	assertTrue(set.contains(new DumbItem(11, 10)));
    	assertTrue(set.contains(new DumbItem(12, 10)));
	}
	
	@Test
	public void testRemoveFirstInBucket() {
		BucketHashSet<DumbItem> set = new BucketHashSet<>();
    	set.add(new DumbItem(10, 10));
    	set.add(new DumbItem(11, 10));
    	set.add(new DumbItem(12, 10));
    	
    	assertTrue(set.remove(new DumbItem(12, 10)));
    	assertFalse(set.contains(new DumbItem(12, 10)));
    	assertTrue(set.contains(new DumbItem(10, 10)));
    	assertTrue(set.contains(new DumbItem(11, 10)));
	}
	
	@Test
	public void testRemoveMiddleInBucket() {
		BucketHashSet<DumbItem> set = new BucketHashSet<>();
    	set.add(new DumbItem(10, 10));
    	set.add(new DumbItem(11, 10));
    	set.add(new DumbItem(12, 10));
    	
    	assertTrue(set.remove(new DumbItem(11, 10)));
    	assertFalse(set.contains(new DumbItem(11, 10)));
    	assertTrue(set.contains(new DumbItem(10, 10)));
    	assertTrue(set.contains(new DumbItem(12, 10)));
	}
	
	@Test
	public void testRemoveLastInBucket() {
		BucketHashSet<DumbItem> set = new BucketHashSet<>();
    	set.add(new DumbItem(10, 10));
    	set.add(new DumbItem(11, 10));
    	set.add(new DumbItem(12, 10));
    	
    	assertTrue(set.remove(new DumbItem(10, 10)));
    	assertFalse(set.contains(new DumbItem(10, 10)));
    	assertTrue(set.contains(new DumbItem(11, 10)));
    	assertTrue(set.contains(new DumbItem(12, 10)));
	}
	
	@Test
	public void testSize() {
		BucketHashSet<DumbItem> set = new BucketHashSet<>();
    	assertEquals(0, set.size());
    	
    	set.add(new DumbItem(10, 10));
    	set.add(new DumbItem(11, 10));
    	set.add(new DumbItem(12, 10));
    	
    	assertEquals(3, set.size());
    	
    	set.remove(new DumbItem(10, 10));
    	
    	assertEquals(2, set.size());
	}
}
