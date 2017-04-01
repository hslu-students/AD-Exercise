package ch.hslu.AD.SW06.WaitPoolDemo;

import java.util.logging.Logger;

public class MyTask implements Runnable {
	
	private static final Logger LOG = Logger.getLogger("MyTask");
	private final Object lock;
	
	public MyTask(final Object lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		LOG.info("warten ...");
		synchronized(lock) {
			try {
				lock.wait();
			} catch(InterruptedException ex) {
				return;
			}
		}
		LOG.info("... aufgewacht");
	}
}
