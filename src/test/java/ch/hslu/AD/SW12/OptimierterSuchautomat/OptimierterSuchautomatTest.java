package ch.hslu.AD.SW12.OptimierterSuchautomat;


import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class OptimierterSuchautomatTest {

    /**
     * Rigourous Test :-)
     */
	@Test
    public void testValidStrings() {
		String[] expectedValidStrings = new String[]{
				"ANANAS",
				"BANANAS",
				"ANANASB",
				"ABANANASB",
				"ANABBANANASC"
		};
		
		for(String string : expectedValidStrings) {
			assertTrue(OptimierterSuchautomat.search(string) >= 0);
		}
    }
	
	@Test
    public void testInvalidStrings() {
		String[] expectedInvalidStrings = new String[]{
				"ANANAB",
				"BANANA",
				"ANANABS",
				"BANANABS",
				"FANAFA"
		};
		
		for(String string : expectedInvalidStrings) {
			assertTrue(OptimierterSuchautomat.search(string) < 0);
		}
    }
}
