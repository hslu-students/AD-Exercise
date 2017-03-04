package ch.hslu.AD.SW2.RingBufferQueue;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.Collection;
import java.util.Iterator;

public class RingBufferQueue<ElementType> implements Collection<ElementType> {
	private int size = 0;
	private int readPos = 0;
	private int writePos = 0;
	private int usedSize = 0;
	private ElementType[] buffer;
	
	public RingBufferQueue(int size) {
		this.size = size;
		
		// See http://stackoverflow.com/a/530289/1336014
		@SuppressWarnings("unchecked")
		final ElementType[] buffer = (ElementType[]) new Object[size];
		this.buffer = buffer;
	}
	
	public boolean isEmpty() {
		return usedSize == 0;
	}
	
	public boolean isFull() {
		return usedSize == size;
	}
	
	public int size() {
		return this.size;
	}
	
	public int used() {
		return usedSize;
	}

	@Override
	public boolean add(ElementType arg0) {
		return write(arg0);
	}
	
	public boolean write(ElementType element) {
		if(usedSize >= size) { // buffer is full -> we do not overwrite.
			throw new BufferOverflowException();
		}
		
		buffer[writePos] = element;
		writePos = (writePos + 1) % size;
		usedSize++;
		return true;
	}
	
	public ElementType read() {
		if(isEmpty()) { // buffer is empty -> we cannot read.
			throw new BufferUnderflowException();
		}
		
		ElementType element = buffer[readPos];
		buffer[readPos] = null;
		usedSize--;
		readPos = (readPos + 1) % size;
		return element;
	}

	@Override
	public boolean addAll(Collection<? extends ElementType> arg0) {
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
		for(ElementType element : this) {
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
	public Iterator<ElementType> iterator() {
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
		Iterator<ElementType> it = new RingBufferQueueIterator();
		//int index = 0;
		while(it.hasNext()) {
			ElementType element = it.next();
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
	
	private class RingBufferQueueIterator implements Iterator<ElementType> {
		private int readPos = 0;
		@Override
		public boolean hasNext() {
			return readPos < size;
		}

		@Override
		public ElementType next() {
			return buffer[readPos++];
		}
		
	}
}