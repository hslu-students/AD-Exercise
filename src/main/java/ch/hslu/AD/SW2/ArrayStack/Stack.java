package ch.hslu.AD.SW2.ArrayStack;

public interface Stack<E> {
	public boolean isEmpty();
	public boolean isFull();
	public int size();
	public boolean push(E element) throws StackFullException;
	public E pop();
	public E peek();
}
