package ch.hslu.AD.SW2.ArrayStack;

import java.util.NoSuchElementException;

import ch.hslu.AD.SW2.ArrayStack.StackFullException;

public class ArrayStack<ElementType> implements Stack<ElementType> {
	private int size = 0;
	private int index = 0;
	private ElementType[] stack;
	
	public ArrayStack(int size) {
		this.size = size;
		
		// See http://stackoverflow.com/a/530289/1336014
		@SuppressWarnings("unchecked")
		final ElementType[] stack = (ElementType[]) new Object[size];
		this.stack = stack;
	}
	
	public boolean isEmpty() {
		return index == 0;
	}
	
	public boolean isFull() {
		return index == size;
	}
	
	public int size() {
		return this.size;
	}

	public boolean push(ElementType element) throws StackFullException {
		if(isFull()) {
			throw new StackFullException(size);
		}
		
		stack[index++] = element;
		return true;
	}
	
	public ElementType pop() {
		if(isEmpty()) {
			throw new NoSuchElementException("Stack is empty");
		}

		return stack[--index]; // it's not really necessary to actually remove the element from the stack.
	}
	
	public ElementType peek() {
		if(isEmpty()) {
			return null;
		}
		
		return stack[index - 1];
	}
}