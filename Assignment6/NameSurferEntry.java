/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.graphics.GObject;
import acm.util.*;

import java.awt.Color;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	public NameSurferEntry(String line) {
		int entryNameEnd = line.indexOf(" ");
		entryName = line.substring(0, entryNameEnd);
		
		int rankNameStart = entryNameEnd + 1;
		allRanks = line.substring(rankNameStart);
 		
		int startParse = 0;
		int currentRank = 0;
		for (int i = START_DECADE; i < (START_DECADE + NDECADES*10); i+=10) {
			int endParse = allRanks.indexOf(" ", startParse + 1);
			if (endParse == -1 || endParse > allRanks.length()-2) {
				currentRank = Integer.parseInt(allRanks.substring(startParse));
			} else {
				currentRank = Integer.parseInt(allRanks.substring(startParse, endParse));
			}
			decadeRankMap.put(i, currentRank);
			startParse = endParse + 1;
		}
	}
	/* Method: setColor() */
	/**
	 * sets color associated with this entry.
	 */
	public void setColor(Color colorSet) {
		 lineColor = colorSet;
	}
	
	/* Method: getColor() */
	/**
	 * Returns the color associated with this entry.
	 */
	public Color getColor() {
		return lineColor;
	}

	

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		return entryName;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		if (decadeRankMap.get(decade) != null) {
		return decadeRankMap.get(decade);
		}
		return 0;
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 * 			
	 * example: "Sam [58 69 99 131 168 236 278 380 467 408 466]"
	 */
	public String toString() {		
		return (entryName + " [" + allRanks + "]");
	}
	
	private Color lineColor;
	private String entryName;
	private String allRanks;
//	private int[] rankArray; 
	private Map<Integer, Integer> decadeRankMap = new HashMap<Integer, Integer>();

}

