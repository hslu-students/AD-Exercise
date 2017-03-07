package ch.hslu.AD.SW2.ArrayStack;

import java.util.NoSuchElementException;

import ch.hslu.AD.SW2.ArrayStack.StackFullException;

public class ArrayStack<T> implements Stack<T> {
	private int index = 0;
	private T[] stack;

	@SuppressWarnings("unchecked")
	public ArrayStack(int size) {
		// See http://stackoverflow.com/a/530289/1336014
		this.stack = (T[]) new Object[size]; // c'mon java ... wtf
	}
	
	public boolean isEmpty() {
		return index == 0;
	}
	
	public boolean isFull() {
		return index == stack.length;
	}
	
	public int size() {
		return stack.length;
	}

	public boolean push(T element) throws StackFullException {
		if(isFull()) {
			throw new StackFullException(stack.length);
		}
		
		stack[index++] = element;
		return true;
	}
	
	public T pop() {
		if(isEmpty()) {
			throw new NoSuchElementException("Stack is empty"); // uncool ... there is no point in unchecked exceptions ;)
		}

		T element = stack[--index];
		stack[index] = null; // remove element from stack to allow GC to delete object
		return element;
	}
	
	public T peek() {
		if(isEmpty()) {
			return null;
		}
		
		return stack[index - 1];
	}
}