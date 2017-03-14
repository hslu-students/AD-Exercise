package ch.hslu.AD.SW04.HashTable;

import java.lang.reflect.Array;

public class BucketHashSet<T> {
	private static int DEFAULT_ARRAY_SIZE = 42;
	
	private Node[] items;
	private int size = 0;
	
	public BucketHashSet() {
		this(DEFAULT_ARRAY_SIZE);
	}
	
	public BucketHashSet(int size) {
		@SuppressWarnings("unchecked")
		Node[] items = Node[].class.cast(Array.newInstance(Node.class, size));
		this.items = items;
	}
	
	public int size() {
		return size;
	}
	
	public boolean add(T item) {
		int index = item.hashCode() % items.length;
		
		// get node at desired index
		Node node = items[index];
		Node newNode = new Node(item);
		
		if(node != null) {
			newNode.link(node);
		}
		items[index] = newNode;
		size++;
		return true;
	}
	
	public boolean contains(T item) {
		int index = item.hashCode() % items.length;
		Node node = items[index];
		
		while(node != null) {
			if(node.getElement().equals(item)) {
				return true;
			}
			node = node.next();
		}
		
		return false;
	}
	
	public boolean remove(T item) {
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
	
	private class Node {
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
}
