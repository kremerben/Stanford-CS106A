import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import acm.program.*;
import acm.util.*;
import java.util.*;
import java.io.*;


public class FlightPlanner extends ConsoleProgram {

	public void run() {
		setupDatabase(FLIGHTS_DATA_FILE);
			println("Welcome to Flight Planner!");
			println("Here's a list of all the cities in our database:");
		listAllCities();
			println("Let's plan a round-trip route!");
		getStartCity();
		planTrip();	
	}
	
	private void planTrip() {
		String nextCity = "";
		String currentCity = startCity;
		String route = "";
		route += startCity;
		while (!nextCity.equalsIgnoreCase(startCity)) {
			println("From " + currentCity + " you can fly directly to:");
			nextCity = listPossibleToCities(currentCity);
			route += (" -> " + nextCity);
			currentCity = nextCity;
		}
		println("The route you've chosen is:");
		println(route);
	}
	
	public String listPossibleToCities(String city) {
		ArrayList<String> tempCities = new ArrayList<String>();
		tempCities = allEntries.get(city).getToCities();
//		println("made it"+ tempCities.size());
		for (int i = 0; i < tempCities.size(); i++){
//			println("made it"+ i);
			println(tempCities.get(i));
		}
		String theNextCity = "";
		while (true) {
			theNextCity = readLine("Where do you want to go from "+ city +"? ");
			if (tempCities.contains(theNextCity)) break;		
			println("You can't get to "+theNextCity+" by a direct flight from " + city+".");
		}
		return theNextCity;
	}

	public void getStartCity() {
		while (startCity == null) {
			startCity = readLine("Enter the starting city: ");
			if (!allEntries.containsKey(startCity)){
				startCity = null;
				println("Please choose from the following list:");
				listAllCities();
			}
		}
	}
	
	public void setupDatabase(String filename) {
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
//			boolean emptyLines = false;
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				if (line.length() > 0) {
					FlightCities entry = parseLine(line);
					allEntries.put(entry.getOrigCityName(), entry);
				}
			}
			rd.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}

	private FlightCities parseLine(String line) {
		int endFromName = line.indexOf(" -> ");
		ArrayList<String> toCityNames = new ArrayList<String>();
		String fromCityName = line.substring(0, endFromName);
		if (!fromCities.contains(fromCityName)) {
		fromCities.add(fromCityName);
		}
		String toCity = line.substring((line.indexOf(" ", endFromName+3))+1);
		if (allEntries.containsKey(fromCityName)) {
			toCityNames = allEntries.get(fromCityName).getToCities();
		} 
		toCityNames.add(toCity);

		return (new FlightCities(fromCityName, toCityNames));
	}
	
	private void listAllCities() {
		for (int i=0; i< fromCities.size(); i++){
			println(fromCities.get(i));
		}
	}
	
	private String startCity = null;
//	private ArrayList<String> toCities = new ArrayList<String>();
	private ArrayList<String> fromCities = new ArrayList<String>();
	private Map<String, FlightCities> allEntries = new HashMap<String, FlightCities>();

	/** The name of the file containing the data */
	public static final String FLIGHTS_DATA_FILE = "flights.txt";
	
}
