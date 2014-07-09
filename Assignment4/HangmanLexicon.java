/** File: HangmanLexicon.java
 * -------------------------
* This file contains a stub implementation of the HangmanLexicon
* class that you will reimplement for Part III of the assignment. 
* */

import acm.util.*;
import acm.program.*;
import java.util.ArrayList;
import java.io.*;

public class HangmanLexicon {
	
	//String filename = "HangmanLexicon.txt";
	private ArrayList<String> tokens = fileContents("HangmanLexicon.txt");

	private ArrayList<String> fileContents(String filename) {
		BufferedReader rd = null;
		while (rd == null) {
			try {
				rd = new BufferedReader(new FileReader(filename));
			} catch (IOException ex) {
				//throw new IOException(ex);
				//name = readLine("try again?");
			}
		}
		ArrayList<String> tokens = new ArrayList<String>();
		try {
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				tokens.add(line);
			}
			rd.close();
		} catch (IOException ex) {
			//println("oops");
		}
		tokens.add("test");
		return tokens;
	}	
		
	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return tokens.size();
		}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return tokens.get(index);
	};
	

}