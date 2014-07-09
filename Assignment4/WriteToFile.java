/**
 * WriteToFile Program
 * @author kremerdesign
 *
 * This program opens a file for the user, 
 * shows what is inside, and asks if the 
 * user would like to add anything.
 */

import java.awt.*;
import java.util.*;
import acm.program.*;
import java.io.*;
import acm.util.*;
import java.util.ArrayList;

public class WriteToFile extends ConsoleProgram {

	public void run(){
		while (filename.isEmpty()) {
			filename = readLine("Enter filename ");
		}
		lines = fileContents(filename);
		if (lines.size() > 0){
			println("Your file contains: ");
		} else {
			println("New file, time to add content.");
		}

		String addMore = readLine("Would you like to add a line? yes/no ");
		if (addMore.equalsIgnoreCase("yes")) {
			while (true) {
				String addLine = readLine("Type a line to add or press return to finish: ");
				if (addLine.isEmpty()) break;
				// code to add text here
				lines.add(addLine);
				println("Added: " + addLine);				
			}
		}
		try {
			PrintWriter wr = new PrintWriter(new FileWriter(filename));
			for (int i = 0;i < lines.size();i++) {
				wr.println(lines.get(i));
			}
			wr.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		
		println("Your file now contains:");
		for (int i = 0;i < lines.size();i++) {
			println(lines.get(i));
		}
	}
	
	private ArrayList<String> fileContents(String filename) {
		BufferedReader rd = openFileReader(filename);
		ArrayList<String> tokens = new ArrayList<String>();
		try {
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
//				println("addinglines");
				println(line);
				tokens.add(line);
			}
			rd.close();
		} catch (IOException ex) {
			println("oops");
		}
		return tokens;
	}
	
	private BufferedReader openFileReader(String name){
		BufferedReader rd = null;
		while (rd == null) {
			try {
				rd = new BufferedReader(new FileReader(name));
			} catch (IOException ex) {
				// if file does not exist, create a new one
				try {
					PrintWriter wr = new PrintWriter(new FileWriter(name));
					wr.close();
				} catch (IOException ex1) {
					throw new ErrorException(ex1);
				}
			}
		}
		return rd;
	}

	private String filename = "";
	private ArrayList<String> lines = new ArrayList<String>();

}
