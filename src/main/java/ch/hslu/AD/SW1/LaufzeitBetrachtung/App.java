package ch.hslu.AD.SW1.LaufzeitBetrachtung;

/**
 * Hello world!
 *
 */
public class App 
{
	public static int task1Calls = 0;
	public static int task2Calls = 0;
	public static int task3Calls = 0;
	
    public static void main( String[] args ) {
    	int prevTask1Calls = 1;
    	int prevTask2Calls = 1;
    	int prevTask3Calls = 1;
    	long prevDuration = 1;
    	
    	for(int n = 1; n < 10000; n *= 2) {
    		long startTime = System.currentTimeMillis();
        	task(n);
        	long stopTime = System.currentTimeMillis();
        	
        	long duration = stopTime - startTime;
        	
        	System.out.println(String.format("N: %d (duration: %d ms, task1: %d, task2: %d, task3: %d", 
        			n, duration, task1Calls, task2Calls, task3Calls));
        	
        	// check if doubling N results in quadrupling task method calls
        	System.out.println(String.format("  check quotient of previous call measurements: task1: %d, task2: %d, task3: %d", 
        			task1Calls / prevTask1Calls, task2Calls / prevTask2Calls, task3Calls / prevTask3Calls));
        	System.out.println(String.format("  check duration quotient: %d", duration / prevDuration));
        	
        	prevDuration = duration;
        	prevTask1Calls = task1Calls;
        	prevTask2Calls = task2Calls;
        	prevTask3Calls = task3Calls;
    	}
    }
    
    /**
     * 
     * Rechenzeit von task(n) => T = f(n) ~ 4 + 3*n + 2*n**2
     * 
     */
    public static void task(final int n) {
    	task1Calls = 0;
    	task2Calls = 0;
    	task3Calls = 0;
    	task1(); task1(); task1(); task1();  // T ~ 4
    	for(int i = 0; i < n; i++) {         // aeussere Schleife: n-mal
    		task2(); task2(); task2();       // T ~ n*3
    		for(int j = 0; j < n; j++) {     // innere Schleife: n-mal
    			task3(); task3();            // T ~ n*n*2
    		}
    	}
    }
    
    public static void task1() {
    	// Solve P vs NP Problem
    	task1Calls += 1;
    	try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void task2() {
    	// Calculate PI to the last possible digit
    	task2Calls += 1;
    	try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void task3() {
    	// Satisfyingly work with Java
    	task3Calls += 1;
    	try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}