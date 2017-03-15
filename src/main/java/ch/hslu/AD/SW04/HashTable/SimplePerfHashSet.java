package ch.hslu.AD.SW04.HashTable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SimplePerfHashSet {

	final private static int MAX_PERF_CYCLES = 10; 
	final private static int MAX_ITEM_SAMPLES = 100000;
	final private static int MAX_SET_SIZE = 100;
	
	private static Item[] getItems(int amount) {
		Item[] items = new Item[amount];
		
		for(int i = 0; i < amount; i++) {
			items[i] = new Item(i);
		}
		
		return items;
	}

	public static void main(String[] args) {
		doPerfMeasurement(new BucketHashSet<Item>(MAX_SET_SIZE), MAX_PERF_CYCLES, MAX_ITEM_SAMPLES);
		//doPerfMeasurement(new SoundedHashSet<Item>(MAX_SET_SIZE), MAX_PERF_CYCLES, MAX_ITEM_SAMPLES);
		doPerfMeasurement(new HashSet<Item>(MAX_SET_SIZE), MAX_PERF_CYCLES, MAX_ITEM_SAMPLES);
	}
	
	final public static void doPerfMeasurement(Set<Item> set, int cycles, int samples) {
		Item[] items = getItems(samples);
		
		// measure bucket hashset times
		long[] startTimes = new long[cycles];
		long[] endTimes = new long[cycles];
		for(int i = 0; i < cycles; i++) {
			set.clear();
			
			startTimes[i] = System.currentTimeMillis();
			
			for(int j = 0; j < samples; j++) {
				set.add(items[j]);
			}
			
			endTimes[i] = System.currentTimeMillis();
		}
		
		System.out.println(String.format("======== Perf for: %s ========", set.getClass().getName()));
		
		// calculate duration and average
		long duration = 0;
		for(int i = 0; i < cycles; i++) {
			System.out.println(String.format("Cycle %d time: %d", i, endTimes[i] - startTimes[i]));
			duration += endTimes[i] - startTimes[i]; 
		}
		
		float averageDuration = duration / (float) cycles;
		
		System.out.println(String.format("==> Average cycle time for %s: %f" , set.getClass().getName(), averageDuration));
		System.out.println("========");
	}
	
	final public static void log(String msg) {
		System.out.println(msg);
	}
	
	final public static class Item {
		private int value;
		
		public Item(int value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return String.format("Item [%d]", value);
		}
		
		@Override
		public int hashCode() {
			//log("Calculation hashCode for '" + this + "'");
			return Objects.hash(value);
		}
		
		@Override
		public boolean equals(Object other) {
			//log("Check equality of '" + this + "' and '" + other + "'");
			if(this == other) {
				return true;
			}
			
			if(!(other instanceof Item)) {
				return false;
			}
			
			Item o = (Item) other;
			return this.value == o.value;
		}
	}

}
