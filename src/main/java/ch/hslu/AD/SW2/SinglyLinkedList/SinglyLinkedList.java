package ch.hslu.AD.SW2.SinglyLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<ElementType>  implements List<ElementType> {
	private int size = 0;
	private Node<ElementType> head;
	
	public SinglyLinkedList() {
		head = new Node<>(null);
	}
	
	public int size() {
		return this.size;
	}

	@Override
	public boolean add(ElementType element) {
		add(size(), element);
		return true;
	}

	@Override
	public void add(int index, ElementType element) {
		int currentIndex = 0;
		Node<ElementType> currentNode = head;
		
		while(currentNode.hasNext() && currentIndex < index) {
			currentNode = currentNode.next();
			currentIndex++;
		}
		
		Node<ElementType> node = new Node<>(element);
		node.link(currentNode.next());
		currentNode.link(node);
		size++;
	}
	
	private Node<ElementType> lastNode() {
		if(head == null) {
			return null;
		}
		
		Node<ElementType> currentNode = head;
		while(currentNode.hasNext()) {
			currentNode = currentNode.next();
		}
		return currentNode;
	}

	@Override
	public boolean addAll(Collection<? extends ElementType> c) {
		Node<ElementType> leftNode = lastNode();
		for(ElementType element : c) {
			Node<ElementType> node = new Node<>(element);
			leftNode.link(node);
			leftNode = node;
			size++;
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends ElementType> c) {
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
	
	private Node<ElementType> getNode(int index) {
		if(index < 0 || index >= size()) {
			return null;
		}
		
		int currentIndex = 0;
		Node<ElementType> current = head;
		while(currentIndex < index && current.hasNext()) {
			current = current.next();
			currentIndex++;
		}
		return current.next();
	}

	@Override
	public ElementType get(int index) {
		Node<ElementType> node = getNode(index);
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
	public Iterator<ElementType> iterator() {
		return new SinglyLinkedListIterator(head);
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<ElementType> listIterator() {
		return new SinglyLinkedListListIterator(null, head, 0, 0);
	}

	@Override
	public ListIterator<ElementType> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		Node<ElementType> currentNode = head;
		Node<ElementType> previousNode = head;
		
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
	public ElementType remove(int index) {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size()));
		}
		
		Node<ElementType> currentNode = head;
		Node<ElementType> previousNode = head;
		int currentIndex = 0;
		while(currentNode.hasNext() && currentIndex <= index) {
			previousNode = currentNode;
			currentNode = currentNode.next();
			currentIndex++;
		}
		ElementType element = currentNode.getElement();
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
	public ElementType set(int index, ElementType element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<ElementType> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		Object[] o = new Object[size];
		int currentIndex = 0;
		for(ElementType element : this) {
			o[currentIndex++] = element;
		}
		return o;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
	
	private class SinglyLinkedListIterator implements Iterator<ElementType> {

		private Node<ElementType> previous;
		private Node<ElementType> current;
		
		public SinglyLinkedListIterator(Node<ElementType> current) {
			this.current = current;
		}
		
		@Override
		public boolean hasNext() {
			return current.hasNext();
		}

		@Override
		public ElementType next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			previous = current;
			current = current.next();
			return current.getElement();
		}
	}
	
	private class SinglyLinkedListListIterator implements ListIterator<ElementType> {
		
		private Node<ElementType> previous;
		private Node<ElementType> current;
		private int previousIndex = 0;
		private int currentIndex = 0;
		
		public SinglyLinkedListListIterator(Node<ElementType> previous, Node<ElementType> current, int previousIndex, int currentIndex) {
			this.previous = previous;
			this.current = current;
			this.previousIndex = previousIndex;
			this.currentIndex = currentIndex;
		}
		
		@Override
		public void add(ElementType arg0) {
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
		public ElementType next() {
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
		public ElementType previous() {
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
		public void set(ElementType arg0) {
			Node<ElementType> node = new Node<>(arg0);
			node.link(current.next());
			previous.link(node);
			current = node;
		}
		
	}
}