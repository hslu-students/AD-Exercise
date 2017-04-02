package ch.hslu.AD.SW06.Semaphore;

public class Semaphore {
	private int permits;
	private int permitLimit;
	
	/**
	 * Creates a Semaphore with an upper limit.
	 * 
	 * @param permits initial permits
	 * @param limit maximum permits to release
	 * @throws IllegalArgumentException
	 */
	public Semaphore(final int permits, final int limit) throws IllegalArgumentException {
		if(permits > limit) {
			throw new IllegalArgumentException("limit cannot be greater than the initial permits");
		}
		
		if(permits < 0 || limit < 0) {
			throw new IllegalArgumentException("permits and limit mustn't be negative");
		}
		
		this.permits = permits;
		this.permitLimit = limit;
	}
	
	public Semaphore() {
		this(0, 0);
	}
	
	public synchronized void acquire() throws InterruptedException {
		acquire(1);
	}
	
	public synchronized void acquire(final int permits) throws InterruptedException, IllegalArgumentException {
		if(permits < 0) {
			throw new IllegalArgumentException("the permits to acquire mustn't be negative");
		}
		
		if(permits > permitLimit) {
			throw new IllegalArgumentException("the permits to acquire mustn't be greater than the permit limit");
		}
		
		while(permits > this.permits) {
			this.wait();
		}
		
		this.permits -= permits;
	}
	
	public synchronized void release(final int permits) {
		if(permits < 0) {
			throw new IllegalArgumentException("the permits to release mustn't be negative");
		}
		
		if(this.permits + permits > permitLimit) {
			throw new IllegalArgumentException("the permits to release and the currently available permits mustn't be greater than the permit limit");
		}
		
		this.notifyAll();
		this.permits += permits;
	}
}
