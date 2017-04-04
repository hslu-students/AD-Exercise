package ch.hslu.AD.SW06.HorseRace;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Turf {
	
	private static final Logger LOG = Logger.getLogger("RaceHorse");
	
	private static final int STARTER_BOXES = 5; 

	public static void main(String[] args) {
		Sync starterBox = new Latch();
		List<Thread> horses = new ArrayList<>();
		
		for(int i = 1; i < 1 + STARTER_BOXES; i++) {
			Thread t = new Thread(new RaceHorse(starterBox), "Horse " + i);
			horses.add(t);
			t.start();
		}
		
		LOG.info("Pferde bereit machen ...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOG.info("Start ...");
		starterBox.release();
		
		LOG.info("Pruefen ob Startabbruch notwendig ...");
		//if(new Random().nextInt(2) == 0) {
		if(false) {
			LOG.info("Fehlstart - Startabbruch notwendig.");
			horses.stream().forEach(t -> t.interrupt());
		}
	}

}
