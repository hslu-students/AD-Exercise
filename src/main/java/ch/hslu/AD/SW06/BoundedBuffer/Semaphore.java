package ch.hslu.AD.SW06.BoundedBuffer;

public class Semaphore {
	private int sema;
	
	public Semaphore(int init) {
		sema = init;
	}
	
	public synchronized void acquire() throws InterruptedException {
		while(sema == 0) {
			this.wait();
		}
		sema--;
	}
	
	// FIXME(TF): check if wait() was time outed. 
	//            cannot just use a flag for notify because of spurious wakeups.
	public synchronized void acquire(final long timeoutMillis) throws InterruptedException {
		while(sema == 0) {
			this.wait(timeoutMillis);
		}
		sema--;
	}
	
	public synchronized void release() {
		this.notifyAll();
		sema++;
	}
}
