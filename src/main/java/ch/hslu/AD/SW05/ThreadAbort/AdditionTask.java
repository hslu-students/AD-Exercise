package ch.hslu.AD.SW05.ThreadAbort;

public class AdditionTask implements Runnable {
	private int rangeBegin;
	private int rangeEnd;
	private Thread runThread;
	private boolean isStopped = false;
	
	public AdditionTask(int begin, int end) {
		rangeBegin = begin;
		rangeEnd = end;
	}
	
	@Override
	public void run() {
		this.runThread = Thread.currentThread();
		
		long sum = 0;
		
		for(int i = this.rangeBegin; i <= this.rangeEnd && !isStopped; i++) {
			sum += i;
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				break;
			}
		}
		
		if (!isStopped) {
			System.out.println(runThread.getName() + ": SUM" + (this.rangeBegin - this.rangeEnd) + " -> " + sum);
		}
		else {
			System.out.println(runThread.getName() + ": interrupted.");
		}
	}
	
	public void stop() {
		isStopped = true;
		
		if(runThread != null) {
			runThread.interrupt();
		}
	}
}
