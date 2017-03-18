package ch.hslu.AD.SW04.HashTable;

import ch.hslu.AD.SW02.ArrayStack.ArrayStack;
import ch.hslu.AD.SW02.ArrayStack.StackFullException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class SimpleStackPerf {

	final private static int MAX_PERF_CYCLES = 10; 
	final private static int MAX_ITEM_SAMPLES = 10000000;
	final private static int MAX_STACK_SIZE = MAX_ITEM_SAMPLES;
	
	private static Integer[] getItems(int amount) {
		Integer[] items = new Integer[amount];
		
		for(int i = 0; i < amount; i++) {
			items[i] = i;
		}
		
		return items;
	}
	
	public static interface StackAdder {
		public void add(Integer x);
	}

	public static void main(String[] args) {
		List<MeasurementReport> reports = new ArrayList<>(); 
		
		reports.add(doPerfMeasurementArrayStack(MAX_STACK_SIZE, MAX_PERF_CYCLES, MAX_ITEM_SAMPLES));
		reports.add(doPerfMeasurementStack(MAX_STACK_SIZE, MAX_PERF_CYCLES, MAX_ITEM_SAMPLES));
		reports.add(doPerfMeasurementDeque(new ArrayDeque<Integer>(MAX_STACK_SIZE), MAX_PERF_CYCLES, MAX_ITEM_SAMPLES));
		reports.add(doPerfMeasurementDeque(new ConcurrentLinkedDeque<Integer>(), MAX_PERF_CYCLES, MAX_ITEM_SAMPLES));
		reports.add(doPerfMeasurementDeque(new LinkedBlockingDeque<Integer>(MAX_STACK_SIZE), MAX_PERF_CYCLES, MAX_ITEM_SAMPLES));
		reports.add(doPerfMeasurementDeque(new LinkedList<Integer>(), MAX_PERF_CYCLES, MAX_ITEM_SAMPLES));
		
		Collections.sort(reports);
		
		System.out.println("======== Rankings ========");
		for(int i = 0; i < reports.size(); i++) {
			MeasurementReport report = reports.get(i);
			System.out.println(String.format("%d. %s: %f", i + 1, report.name, report.average));
		}
	}
	
	final public static MeasurementReport doPerfMeasurementArrayStack(int stackSize, int cycles, int sampleSize) {
		Integer[] samples = getItems(sampleSize);
		
		long[] durations = new long[cycles];
		for(int i = 0; i < cycles; i++) {
			ArrayStack<Integer> stack = new ArrayStack<Integer>(stackSize);
			durations[i] = doPerfCycle((x) -> {
				try {
					stack.push(x);
				} catch (StackFullException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, samples, sampleSize);
		}
		
		return report(ArrayStack.class.getName(), durations, cycles);
	}
	
	final public static MeasurementReport doPerfMeasurementStack(int stackSize, int cycles, int sampleSize) {
		Integer[] samples = getItems(sampleSize);
		
		long[] durations = new long[cycles];
		for(int i = 0; i < cycles; i++) {
			Stack<Integer> stack = new Stack<Integer>();
			durations[i] = doPerfCycle((x) -> stack.push(x), samples, sampleSize);
		}
		
		return report(Stack.class.getName(), durations, cycles);
	}
	
	final public static MeasurementReport doPerfMeasurementDeque(Deque<Integer> deque, int cycles, int sampleSize) {
		Integer[] samples = getItems(sampleSize);
		
		long[] durations = new long[cycles];
		for(int i = 0; i < cycles; i++) {
			deque.clear();
			durations[i] = doPerfCycle((x) -> deque.push(x), samples, sampleSize);
		}
		
		return report(deque.getClass().getName(), durations, cycles);
	}
	
	final public static long doPerfCycle(StackAdder adder, Integer[] samples, int sampleSize) {
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < sampleSize; i++) {
			adder.add(samples[i]);
		}
		
		return System.currentTimeMillis() - startTime;
	}
	
	final public static MeasurementReport report(String title, long durations[], int durationSize) {
		System.out.println(String.format("======== Perf for: %s ========", title));
		
		// calculate duration and average
		long duration = 0;
		for(int i = 0; i < durationSize; i++) {
			System.out.println(String.format("Cycle %d time: %d", i, durations[i]));
			duration += durations[i]; 
		}
		
		float averageDuration = duration / (float) durationSize;
		
		System.out.println(String.format("==> Average cycle time for %s: %f" , title, averageDuration));
		System.out.println("========");
		
		return new MeasurementReport(title, averageDuration);
	}
	
	public static class MeasurementReport implements Comparable<MeasurementReport> {
		public String name;
		public float average;
		
		public MeasurementReport(String name, float average) {
			this.name = name;
			this.average = average;
		}

		@Override
		public int compareTo(MeasurementReport o) {
			if(this.average < o.average) {
				return -1;
			}
			if(this.average > o.average) {
				return 1;
			}
			return 0;
		}
	}
}
