package ch.hslu.AD.SW06.HorseRace;

public interface Sync {
	public void acquire() throws InterruptedException;
	
	public void release();
}
