package ch.hslu.AD.SW04.HashTable;

import ch.hslu.AD.SW04.HashTable.SoundedHashSetTest.DumbItem;

public class Playground {
	public static void main(String[] args) {
		SoundedHashSet<DumbItem> set = new SoundedHashSet<>(3);
		DumbItem firstItem = new DumbItem(1, 1);
		DumbItem soundedItem = new DumbItem(2, 1);
		DumbItem overlappedItem = new DumbItem(3, 2);
		
		set.add(firstItem);
		set.add(soundedItem);
		set.add(overlappedItem);
		
		System.out.println(set);
	}
	
	public static class DumbItem {
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
		final public int hashCode() {
			return this.hashCode;
		}
		
		@Override
		public String toString() {
			return String.format("%d (%d)", value, hashCode);
		}
	}
}
