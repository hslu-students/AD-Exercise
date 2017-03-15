package ch.hslu.AD.SW04.HashTable;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class BucketHashSet<T> implements Set<T> {
	private static int DEFAULT_ARRAY_SIZE = 42;
	
	private Node[] items;
	private int size = 0;
	
	public BucketHashSet() {
		this(DEFAULT_ARRAY_SIZE);
	}

	public BucketHashSet(int size) {
		this.initialize(size);
	}

	@SuppressWarnings("unchecked")
	final public void initialize(int size) {
		this.items = Node[].class.cast(Array.newInstance(Node.class, size));
		this.size = 0;
	}
	
	final public void clear() {
		this.initialize(items.length);
	}
	
	final public int size() {
		return size;
	}
	
	final public boolean add(T item) {
		int index = item.hashCode() % items.length;
		Node node = items[index];
		
		if(containsInBucket(node, item)) {
			return true;
		}
		
		Node newNode = new Node(item);
		
		if(node != null) {
			newNode.link(node);
		}
		items[index] = newNode;
		size++;
		return true;
	}
	
	final public boolean contains(Object item) {
		int index = item.hashCode() % items.length;
		Node node = items[index];
		
		return containsInBucket(node, item);
	}
	
	final public boolean containsInBucket(Node head, Object item) {
		Node node = head;
		
		while(node != null) {
			if(node.getElement().equals(item)) {
				return true;
			}
			node = node.next();
		}
		
		return false;
	}
	
	final public boolean remove(Object item) {
		int index = item.hashCode() % items.length;
		Node prevNode = null;
		Node node = items[index];
		
		while(node != null) {
			if(node.getElement().equals(item)) {
				if(prevNode == null) { // if item to remove is the first in the bucket.
					items[index] = node.next();
				} else {
					prevNode.link(node.next());
				}
				size--;
				return true;
			}
			prevNode = node;
			node = node.next();
		}
		
		return false;
	}
	
	final private class Node {
		private T element;
		private Node next;
		
		public Node(T element) {
			this.element = element;
		}
		
		public T getElement() {
			return element;
		}
		
		public void link(Node next) {
			this.next = next;
		}
		
		public Node next() {
			return next;
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
	public Iterator<T> iterator() {
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
		return null;
	}
}
