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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<ElementType> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<ElementType> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ElementType set(int index, ElementType element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementType> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
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
}