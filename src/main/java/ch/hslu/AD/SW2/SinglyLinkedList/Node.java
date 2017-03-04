package ch.hslu.AD.SW2.SinglyLinkedList;

public class Node<ElementType> {
	private ElementType element;
	private Node<ElementType> next;
	
	public Node(ElementType element) {
		this.element = element;
	}
	
	public ElementType getElement() {
		return element;
	}
	
	public void link(Node<ElementType> next) {
		this.next = next;
	}
	
	public boolean hasNext() {
		return next != null;
	}
	
	public Node<ElementType> next() {
		return next;
	}
}
