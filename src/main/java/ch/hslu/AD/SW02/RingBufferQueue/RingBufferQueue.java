package ch.hslu.AD.SW02.RingBufferQueue;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.Collection;
import java.util.Iterator;

public class RingBufferQueue<T> implements Collection<T> {
	private int readPos = 0; // tail
	private int writePos = 0; // head
	private int usedSize = 0;
	private T[] buffer;
	
	@SuppressWarnings("unchecked")
	public RingBufferQueue(int size) {
		// See http://stackoverflow.com/a/530289/1336014
		this.buffer = (T[]) new Object[size]; // c'mon java ... wtf
	}
	
	public boolean isEmpty() {
		return usedSize == 0;
	}
	
	public boolean isFull() {
		return usedSize == buffer.length;
	}
	
	public int size() {
		return buffer.length;
	}
	
	public int used() {
		return usedSize;
	}

	@Override
	public boolean add(T arg0) {
		return enqueue(arg0);
	}
	
	public boolean enqueue(T element) {
		if(isFull()) { // buffer is full -> we do not overwrite.
			throw new BufferOverflowException();
		}
		
		buffer[writePos] = element;
		writePos = (writePos + 1) % buffer.length;
		usedSize++;
		return true;
	}
	
	public T dequeue() {
		if(isEmpty()) { // buffer is empty -> we cannot read.
			throw new BufferUnderflowException();
		}
		
		T element = buffer[readPos];
		buffer[readPos] = null;
		usedSize--;
		readPos = (readPos + 1) % buffer.length;
		return element;
	}

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		return false;
	}

	@Override
	public void clear() {
		for(int i = 0; i < buffer.length; i++) {
			buffer[i] = null;
		}
		readPos = 0;
		writePos = 0;
		usedSize = 0;
	}

	@Override
	public boolean contains(Object arg0) {
		for(T element : this) {
			if(element != null && element.equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new RingBufferQueueIterator();
	}

	@Override
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException("Remove operation is not supported");
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Remove operation is not supported");
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Retain operation is not supported");
	}

	@Override
	public Object[] toArray() {
		return buffer.clone();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("| ");
		//int index = 0;
		for(T element : this) {
			if(element == null) {
				str.append(' ');
			} else {
				str.append(element.toString());
			}
			
			/*if(readPos == index) {
				str.append(" (R)");
			}
			
			if(writePos == index) {
				str.append(" (W)");
			}*/
			
			str.append(" | ");
			//index++;
		}
		return str.toString();
	}
	
	private class RingBufferQueueIterator implements Iterator<T> {
		private int readPos = 0;
		@Override
		public boolean hasNext() {
			return readPos < buffer.length;
		}

		@Override
		public T next() {
			return buffer[readPos++];
		}
		
	}
}