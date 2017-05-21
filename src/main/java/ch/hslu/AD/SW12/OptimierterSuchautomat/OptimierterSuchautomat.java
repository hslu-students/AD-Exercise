package ch.hslu.AD.SW12.OptimierterSuchautomat;

public class OptimierterSuchautomat {
	public static int search(final String a) {
		int i = 0;
		String state = "";
		
		do {
			char c = a.charAt(i);
			switch(state) {
				case "":
					if(c == 'A') {
						state = "A";
					}
					break;
				case "A":
					if(c == 'N') {
						state = "AN";
					} else {
						state = "";
					}
					break;
				case "AN": 
					if(c == 'A') {
						state = "ANA";
					} else {
						state = "";
					}
					break;
				case "ANA":
					if(c == 'N') {
						state = "ANAN";
					} else {
						state = "";
					}
					break;
				case "ANAN":
					if(c == 'A') {
						state = "ANANA";
					} else {
						state = "";
					}
					break;
				case "ANANA":
					if(c == 'S') {
						state = "ANANAS";
					} else {
						state = "";
					}
					break;
			}
			i++;
		} while((state != "ANANAS") && i < a.length());
		
		if(state == "ANANAS") {
			return i - "ANANAS".length();
		}
		
		return -1;
	}
}
