package ch.hslu.AD.SW05.ThreadAbort;

import java.util.ArrayList;
import java.util.List;

public class Playground {
	public static void main(String[] args) throws InterruptedException {
		List<AdditionTask> tasks = new ArrayList<>();
		List<Thread> threads = new ArrayList<Thread>();
		
		for(int i = 0; i < 10; i++) {
			AdditionTask task = new AdditionTask(i * 10, i * 100);
			tasks.add(task);
			threads.add(new Thread(task));
		}
		
		threads.stream().forEach((t) -> t.start());
		Thread.sleep(1000);
		tasks.stream().forEach((t) -> t.stop());
	}
}
