package ch.hslu.AD.SW06.HorseRace;

public class Latch implements Sync {
	
	private int count;
	
	public Latch() {
		this.count = 1;
	}

	@Override
	public synchronized void acquire() throws InterruptedException {
		while(count > 0) {
			this.wait();
		}
	}

	@Override
	public synchronized void release() {
		this.notifyAll();
		count--;
	}

}
