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
public class SoundedHashSetTest {

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
    	SoundedHashSet<Integer> set = new SoundedHashSet<>();
    	assertTrue(set.add(1));
    	assertTrue(set.add(2));
    	assertTrue(set.add(1));
    }
	
	@Test
	public void testContains() {
    	SoundedHashSet<DumbItem> set = new SoundedHashSet<>();
    	set.add(new DumbItem(10, 10));
    	assertTrue(set.contains(new DumbItem(10, 10)));
    	assertFalse(set.contains(new DumbItem(10, 11)));
    	set.add(new DumbItem(10, 11));
    	assertTrue(set.contains(new DumbItem(10, 11)));
	}
	
	@Test
	public void testRemove() {
    	SoundedHashSet<DumbItem> set = new SoundedHashSet<>();
    	DumbItem item = new DumbItem(10, 10);
    	set.add(item);
    	assertTrue(set.contains(item));
    	assertTrue(set.remove(item));
    	assertFalse(set.contains(item));
	}
	
	@Test
	public void testRemoveNonExistent() {
    	SoundedHashSet<DumbItem> set = new SoundedHashSet<>();
    	assertFalse(set.contains(new DumbItem(11, 11)));
	}
	
	@Test
	public void testIterator() {
    	SoundedHashSet<DumbItem> set = new SoundedHashSet<>();
		set.add(new DumbItem(10, 10));
		set.add(new DumbItem(11, 11));
		set.add(new DumbItem(12, 12));
		set.add(new DumbItem(13, 13));
		
		Iterator<DumbItem> it = set.iterator();
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(10, 10), it.next());
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(11, 11), it.next());
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(12, 12), it.next());
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(13, 13), it.next());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testIteratorSounded() {
    	SoundedHashSet<DumbItem> set = new SoundedHashSet<>();
		set.add(new DumbItem(10, 10));
		set.add(new DumbItem(14, 10));
		set.add(new DumbItem(11, 11));
		set.add(new DumbItem(12, 12));
		set.add(new DumbItem(13, 13));
		
		Iterator<DumbItem> it = set.iterator();
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(10, 10), it.next());
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(14, 10), it.next());
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(11, 11), it.next());
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(12, 12), it.next());
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(13, 13), it.next());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testIteratorSoundedRemoved() {
    	SoundedHashSet<DumbItem> set = new SoundedHashSet<>();
    	DumbItem soundedItem = new DumbItem(14, 10);
		set.add(new DumbItem(10, 10));
		set.add(soundedItem);
		set.add(new DumbItem(11, 11));
		set.add(new DumbItem(12, 12));
		set.add(new DumbItem(13, 13));
		
		assertTrue(set.remove(soundedItem));
		
		Iterator<DumbItem> it = set.iterator();
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(10, 10), it.next());
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(11, 11), it.next());
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(12, 12), it.next());
		assertTrue(it.hasNext());
		assertEquals(new DumbItem(13, 13), it.next());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testAddSoundedOverlap() {
		SoundedHashSet<DumbItem> set = new SoundedHashSet<>(3);
		DumbItem firstItem = new DumbItem(1, 1);
		DumbItem soundedItem = new DumbItem(2, 1);
		DumbItem overlappedItem = new DumbItem(3, 2);
		
		set.add(firstItem);
		set.add(soundedItem);
		set.add(overlappedItem);
		
		assertTrue(set.contains(firstItem));
		assertTrue(set.contains(soundedItem));
		assertTrue(set.contains(overlappedItem));

		Iterator<DumbItem> it = set.iterator();
		assertTrue(it.hasNext());
		assertEquals(overlappedItem, it.next());
		assertTrue(it.hasNext());
		assertEquals(firstItem, it.next());
		assertTrue(it.hasNext());
		assertEquals(soundedItem, it.next());
		assertFalse(it.hasNext());
		
	}
}
