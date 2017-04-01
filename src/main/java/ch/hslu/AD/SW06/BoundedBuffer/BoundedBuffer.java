package ch.hslu.AD.SW06.BoundedBuffer;

public class BoundedBuffer<T> {
	private final int capacity;
	private int readPos = 0; // tail
	private int writePos = 0; // head
	private int size = 0;
	private T[] buffer;
	
	private Semaphore putSemaphore;
	private Semaphore getSemaphore;
	
	@SuppressWarnings("unchecked")
	public BoundedBuffer(final int capacity) {
		this.capacity = capacity;
		// See http://stackoverflow.com/a/530289/1336014
		this.buffer = (T[]) new Object[capacity]; // c'mon java ... wtf
		
		this.putSemaphore = new Semaphore(capacity);
		this.getSemaphore = new Semaphore(0);
	}

	public boolean empty() {
		return size == 0;
	}
	
	public boolean full() {
		return size == capacity;
	}
	
	public int size() {
		return size;
	}
	
	public void put(final T element) throws InterruptedException {
		// wait for empty slot to put the element
		putSemaphore.acquire();
		// put the element into the internal buffer
		putElement(element);
		// notify all waiting get() calls that a new element is available
		getSemaphore.release();
	}
	
	public void put(final T element, final long timeoutMillis) throws InterruptedException {
		// wait for empty slot to put the element
		putSemaphore.acquire(timeoutMillis);
		// put the element into the internal buffer
		putElement(element);
		// notify all waiting get() calls that a new element is available
		getSemaphore.release();
	}
	
	public T get() throws InterruptedException {
		// wait until an element is available
		getSemaphore.acquire();
		// get the element from the internal buffer
		T element = getElement();
		// notify all waiting put() calls that there is a new empty slot available to put elements.
		putSemaphore.release();
		return element;
	}
	
	public T get(final long timeoutMillis) throws InterruptedException {
		// wait until an element is available
		getSemaphore.acquire(timeoutMillis);
		// get the element from the internal buffer
		T element = getElement();
		// notify all waiting put() calls that there is a new empty slot available to put elements.
		putSemaphore.release();
		return element;
	}
	
	private synchronized void putElement(final T element) {
		buffer[writePos] = element;
		writePos = (writePos + 1) % buffer.length;
		size++;
	}
	
	private synchronized T getElement() {
		T element = buffer[readPos];
		buffer[readPos] = null;
		size--;
		readPos = (readPos + 1) % buffer.length;
		return element;
	}
}
