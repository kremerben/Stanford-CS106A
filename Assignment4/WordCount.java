import java.io.*;
import java.util.*;
import acm.util.*;
import acm.program.*;



public class WordCount extends ConsoleProgram {

	
	public void run(){
		BufferedReader rd = openFileReader("enter filename: ");
		try {
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				//println(line);
				//count lines
				lines++;
				//println("Lines: " + lines);
				//println("File: " + fileName);
				//count characters
				chs += line.length();
				//println("Characters: " + chs);
				//count words
				StringTokenizer tokenizer = new StringTokenizer(line, " '");
				while (tokenizer.hasMoreTokens()) {
					String token = tokenizer.nextToken();
					words++;
					//println("Words: " + words + " " + token);
				}
			}

			rd.close();
		} catch (IOException ex) {
			println("oops errorrr");
		}
		
		
		println("Total Lines: " + lines);
		println("Total Words: " + words);
		println("Total Characters: " + chs);
	}
	
	private BufferedReader openFileReader(String prompt) {
		BufferedReader rd = null;
		while (rd == null) {
			try {
				String name = readLine(prompt);
				rd = new BufferedReader(new FileReader(name));
				println("File: " + name);
			} catch (IOException ex) {
				println("can't open that one");
			}
		}
		return rd;
	}
	
	private String fileName = "";
	//private Boolean keeprunning = true;
	private int lines = 0;
	private int words = 0;
	private int chs = 0;
	
}
