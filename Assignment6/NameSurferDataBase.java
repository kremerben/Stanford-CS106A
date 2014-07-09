import java.io.*;
import java.util.HashMap;
import java.util.Map;

import acm.util.*;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = rd.readLine();
//				String line = "Sam [58 69 99 131 168 236 278 380 467 408 466]";
				if (line == null) break;
				//Album album = parseline(line);
				//inventory.put(album.getAlbumName(), album);
				NameSurferEntry entry = new NameSurferEntry(line);
				allEntries.put(entry.getName().toLowerCase(), entry);

			}
			rd.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		if (allEntries.containsKey(name)) {
			return allEntries.get(name);
		}
		return null;
	}
	
	
	private Map<String, NameSurferEntry> allEntries = new HashMap<String, NameSurferEntry>();
	
}

