
import acm.util.*;
import acm.program.*;



public class FinalTestQuestion3 extends ConsoleProgram {

	public void run() {
		println("Program to check a word ladder.");
		word = readLine("Enter a sequence of words ending with a blank line.");
		while (!isEnglishWord(word)) {
				word = readLine("That word is not legal. Try Again.");
		}
		int wordlen = word.length();

		while (!word.isEmpty()) {
			if (!isEnglishWord(word) && word.length() != wordlen && !letterDifisOne(word, prevWord)) {
				word = readLine("That word is not legal. Try Again.");
			}
		}
	}
	
	private boolean letterDifisOne(String a, String b) {
		int count = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == b.charAt(i)) {
				count++;
			}
		}
		if (count == 1) {
			return true;
		}
		return false;
	}
	
	
	public boolean isEnglishWord(String str) {
		return true;
	}

	
	private Lexicon lexicon = new Lexicon("english.dat");
	private String word;
	private String prevWord;
	
}