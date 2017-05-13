package ch.hslu.AD.SW11.Wordproblem;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class LanguageTest {

    /**
     * Rigourous Test :-)
     */
	@Test
    public void testValidWords() {
		String[] expectedValidWords = new String[]{
				"0",
				"010",
				"01110",
				"0111010",
				"0101110",
				"0101010"
		};
		
		for(String word : expectedValidWords) {
			assertTrue(Language.isWord(word));
		}
    }
	
	@Test
    public void testInvalidWords() {
		String[] expectedInvalidWords = new String[]{
				"00",
				"0110",
				"01111",
				"0011010",
				"0100110",
				"0100010"
		};
		
		for(String word : expectedInvalidWords) {
			assertFalse(Language.isWord(word));
		}
    }
}
