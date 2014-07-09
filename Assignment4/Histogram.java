import java.io.*;
import java.util.*;
import acm.util.*;
import acm.program.*;

public class Histogram extends ConsoleProgram {

	public void run(){
		BufferedReader rd = openFileReader("enter filename: ");
		try {
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				int pass = Integer.parseInt(line);
				categorizeNum(pass);
			}			
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		println("00-09: " + singles);
		println("10-19: " + teens);
		println("20-29: " + twenties);
		println("30-39: " + thirties);
		println("40-49: " + fourties);
		println("50-59: " + fifties);
		println("60-69: " + sixties);
		println("70-79: " + seventies);
		println("80-89: " + seventies);
		println("90-99: " + nineties);
		println("  100: " + hundred);
	}
	
	private void categorizeNum(int num) {
		switch (num/10) {
		case 0:
			singles += "*";
			break;
		case 1:
			teens += "*";
			break;
		case 2:
			twenties += "*";
			break;
		case 3:
			thirties += "*";
			break;
		case 4:
			fourties += "*";
			break;
		case 5:
			fifties += "*";
			break;
		case 6:
			sixties += "*";
			break;
		case 7:
			seventies += "*";
			break;
		case 8:
			eighties += "*";
			break;
		case 9:
			nineties += "*";
			break;
		case 10:
			hundred += "*";
			break;
		default:
			break;
		}
	}
	
	private BufferedReader openFileReader(String prompt) {
		BufferedReader rd = null;
		while (rd == null) {
			try {
				String name = readLine(prompt);
				rd = new BufferedReader(new FileReader(name));
			} catch (IOException ex) {
				println("try another");
				//throw new ErrorException(ex);
			}
		}
		return rd;
	}
	
	private String singles = "";
	private String teens = "";
	private String twenties = "";
	private String thirties = "";
	private String fourties = "";
	private String fifties = "";
	private String sixties = "";
	private String seventies = "";
	private String eighties = "";
	private String nineties = "";
	private String hundred = "";
		
}
