import java.util.HashMap;
import java.util.Map;
import acm.program.*;
import java.util.*;

/*
 * File: FlightCities.java
 * =------------
 * Keeps track of all the info for each city including name
 * and cities it connects to
 */
public class FlightCities {

	//Constructor
	public FlightCities(String origCity, ArrayList<String> toCities) {
		origCityName = origCity;
		toCitiesNames = toCities;
	}
	
	public String getOrigCityName() {
		return origCityName;
	}
	
	public ArrayList<String> getToCities() {
		return toCitiesNames;
	}
	
	public String toString() {
		if (toCitiesNames.isEmpty()) {
			return (origCityName + " flies nowhere.");
		}
		String allToCities = "[";
		for (int i = 0; i < toCitiesNames.size(); i++) {
			allToCities += toCitiesNames.get(i);
			if (i < toCitiesNames.size()-1) {
				allToCities += " ";
			}
		}
		return (origCityName + " flies to " + allToCities + "]");
	}
	
	private String origCityName;
	private ArrayList<String> toCitiesNames = new ArrayList<String>();
	
}
