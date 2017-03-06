package ch.hslu.AD.SW2.SinglyLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T>  implements List<T> {
	private int size = 0;
	private Node<T> head;
	
	public SinglyLinkedList() {
		head = new Node<>(null);
	}
	
	public int size() {
		return this.size;
	}

	@Override
	public boolean add(T element) {
		add(0, element);
		return true;
	}

	@Override
	public void add(int index, T element) {
		int currentIndex = 0;
		Node<T> currentNode = head;
		
		while(currentNode.hasNext() && currentIndex < index) {
			currentNode = currentNode.next();
			currentIndex++;
		}
		
		Node<T> node = new Node<>(element);
		node.link(currentNode.next());
		currentNode.link(node);
		size++;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for(T element : c) {
			add(element);
		}
		
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return false;
	}

	@Override
	public void clear() {
		size = 0;
		head = null;
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	
	private Node<T> getNode(int index) {
		if(index < 0 || index >= size()) {
			return null;
		}
		
		int currentIndex = 0;
		Node<T> current = head;
		while(currentIndex < index && current.hasNext()) {
			current = current.next();
			currentIndex++;
		}
		return current.next();
	}

	@Override
	public T get(int index) {
		Node<T> node = getNode(index);
		if(node == null) {
			throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size()));
		}
		return node.getElement();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		return size == 0 && head == null;
	}

	@Override
	public Iterator<T> iterator() {
		return new SinglyLinkedListIterator(head);
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator() {
		return new SinglyLinkedListListIterator(null, head, 0, 0);
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		Node<T> currentNode = head;
		Node<T> previousNode = head;
		
		while(currentNode.hasNext()) {
			currentNode = currentNode.next();
			if(o.equals(currentNode.getElement())) {
				previousNode.link(currentNode.next());
				size--;
				return true;
			}
			previousNode = currentNode;
		}
		return false;
	}

	@Override
	public T remove(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size()));
		}
		
		Node<T> currentNode = head;
		Node<T> previousNode = head;
		int currentIndex = 0;
		while(currentNode.hasNext() && currentIndex <= index) {
			previousNode = currentNode;
			currentNode = currentNode.next();
			currentIndex++;
		}
		T element = currentNode.getElement();
		previousNode.link(currentNode.next());
		size--;
		return element;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T set(int index, T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		Object[] o = new Object[size];
		int currentIndex = 0;
		for(T element : this) {
			o[currentIndex++] = element;
		}
		return o;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
	
	private class SinglyLinkedListIterator implements Iterator<T> {

		private Node<T> previous;
		private Node<T> current;
		
		public SinglyLinkedListIterator(Node<T> current) {
			this.current = current;
		}
		
		@Override
		public boolean hasNext() {
			return current.hasNext();
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			previous = current;
			current = current.next();
			return current.getElement();
		}
	}
	
	private class SinglyLinkedListListIterator implements ListIterator<T> {
		
		private Node<T> previous;
		private Node<T> current;
		private int previousIndex = 0;
		private int currentIndex = 0;
		
		public SinglyLinkedListListIterator(Node<T> previous, Node<T> current, int previousIndex, int currentIndex) {
			this.previous = previous;
			this.current = current;
			this.previousIndex = previousIndex;
			this.currentIndex = currentIndex;
		}
		
		@Override
		public void add(T arg0) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {
			return current.hasNext();
		}

		@Override
		public boolean hasPrevious() {
			return previous != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			previous = current;
			previousIndex = currentIndex++;
			current = current.next();
			return current.getElement();
		}

		@Override
		public int nextIndex() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			return currentIndex + 1;
		}

		@Override
		public T previous() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			
			return previousIndex;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T arg0) {
			Node<T> node = new Node<>(arg0);
			node.link(current.next());
			previous.link(node);
			current = node;
		}
		
	}
}