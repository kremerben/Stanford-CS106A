import acm.program.*;


public class removeDoubleLetter extends ConsoleProgram {

	public void run() {
		while (true) {
			String word = readLine("Enter a word: "); 
			if (word.length() == 0) break; 
			println(removeDoubledLetters(word));
		}
	}
	
	private String removeDoubledLetters(String str) {
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i-1) == str.charAt(i)) {
				str = str.substring(0,i) + str.substring(i+1);
				i--; //check that letter again for triples
			}
		}
		return str;
	}
}
