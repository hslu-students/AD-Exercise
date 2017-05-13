package ch.hslu.AD.SW11.Wordproblem;

public class Language {
	public static boolean isWord(final String string) {
		int zeroCount = 0;
		int oneCount = 0;
		
		for(char c : string.toCharArray()) {
			if(c == '0') {
				if(zeroCount > 0) { // we've got two subsequent zeros
					return false;
				}
				else if(oneCount != 0 && (oneCount % 2) != 1) { // even group of ones before zero
					return false;
				}
				oneCount = 0;
				zeroCount++;
			}
			else if(c == '1') {
				zeroCount = 0;
				oneCount++;
			}
			else {
				return false; // invalid character
			}
		}
		
		boolean endingDoubleOne = oneCount != 0 && (oneCount % 2) != 1;
		return !endingDoubleOne;
	}
}
