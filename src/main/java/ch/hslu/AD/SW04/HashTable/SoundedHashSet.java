package ch.hslu.AD.SW04.HashTable;

import java.lang.reflect.Array;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class SoundedHashSet<T> implements Iterable<T>, Set<T> {
	private static int DEFAULT_ARRAY_SIZE = 42;
	
	private Node[] items;
	private int size = 0;
	
	public SoundedHashSet() {
		this(DEFAULT_ARRAY_SIZE);
	}
	
	public SoundedHashSet(int size) {
		this.initialize(size);
	}
	
	public void clear() {
		this.initialize(this.items.length);
	}
	
	private void initialize(int size) {
		@SuppressWarnings("unchecked")
		Node[] items = Node[].class.cast(Array.newInstance(Node.class, size));
		this.items = items;
		this.size = 0;
	}
	
	private int getNextIndex(T item) {
		int startIndex = item.hashCode() % items.length;
		int currentIndex = startIndex;
		Node currentNode = items[currentIndex];
		
		while(currentNode != null && !currentNode.isTombstone() && !currentNode.item.equals(item)) {
			currentIndex = (currentIndex + 1) % items.length;
			if(currentIndex == startIndex) {
				throw new BufferOverflowException();
			}
			
			currentNode = items[currentIndex];
		}
		
		return currentIndex;
	}
	
	public int size() {
		return size;
	}
	
	public boolean add(T item) {
		items[getNextIndex(item)] = new Node(item);
		size++;
		return true;
	}
	
	public boolean contains(Object item) {
		return searchNode(item) != null;
	}
	
	public Node searchNode(Object item) {
		int startIndex = item.hashCode() % items.length;
		int currentIndex = startIndex;
		Node currentNode;
		
		do {
			currentNode = items[currentIndex];
			if(currentNode != null && !currentNode.isTombstone() && currentNode.item.equals(item)) {
				return currentNode; // item found
			}
			currentIndex = (currentIndex + 1) % items.length;
		} while(currentNode != null && currentIndex != startIndex);
		
		return null;
	}
	
	public boolean remove(Object item) {
		Node node = searchNode(item);
		if(node == null) {
			return false;
		}
		
		node.bury();
		size--;
		return true;
	}
	
	@Override
	public String toString() {
		List<String> output = new ArrayList<>();
		for(Node item : items) {
			if(item != null) {
				output.add(item.toString());
			} else {
				output.add("E");
			}
		}
		return String.join(" | ", output);
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			private int current = 0;

			@Override
			public boolean hasNext() {
				return getNext(false) != null;
			}
			
			private Node getNext(boolean updateCurrent) {
				Node currentNode;
				for(int i = current; i < items.length; i++) {
					currentNode = items[i];
					if(currentNode != null && !currentNode.isTombstone()) {
						if(updateCurrent) {
							current = i + 1;
						}
						return currentNode;
					}
				}
				
				return null;
			}

			@Override
			public T next() {
				Node node = getNext(true);
				if(node == null) {
					throw new NoSuchElementException();
				}
				return node.item;
			}
			
		};
	}
	
	private class Node {
		public T item;
		private boolean isTombstone = false;
		
		public Node(T item) {
			this.item = item;
		}
		
		public boolean isTombstone() {
			return isTombstone;
		}
		
		@Override
		public String toString() {
			if(isTombstone) {
				return "T";
			}
			
			return item.toString();
		}
		
		public void bury() {
			item = null;
			isTombstone = true;
		}
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
}
