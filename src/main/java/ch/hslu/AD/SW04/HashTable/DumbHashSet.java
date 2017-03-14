package ch.hslu.AD.SW04.HashTable;

public class DumbHashSet<T> {
	private static int DEFAULT_ARRAY_SIZE = 42;
	
	private T[] items;
	
	public DumbHashSet() {
		@SuppressWarnings("unchecked")
		final T[] items = (T[]) new Object[DEFAULT_ARRAY_SIZE];
		this.items = items;
	}
	
	private int getIndex(T item) {
		return item.hashCode() % DEFAULT_ARRAY_SIZE;
	}
	
	public boolean add(T item) {
		items[getIndex(item)] = item;
		return true;
	}
	
	public boolean contains(T item) {
		return items[getIndex(item)] != null;
	}
	
	public void remove(T item) {
		items[getIndex(item)] = null;
	}
}
