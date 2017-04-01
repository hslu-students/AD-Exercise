package ch.hslu.AD.SW06.WaitPoolDemo;

public class DemoWaitPool_C {
	
	private static final Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		synchronized (lock) {
			MyTask waiter = new MyTask(lock);
			new Thread(waiter).start();
			Thread.sleep(1000);
			
			lock.notify();
		}
	}
}
