import acm.program.*;
import java.util.*;

public class NameCounter extends ConsoleProgram {
	
	public void run() {
		Map<String, Integer> names = new HashMap<String, Integer>();
		while(true) {
			String name = readLine("Enter name: ");
			if (name.isEmpty()) break;
			if (names.get(name) == null) {
				names.put(name, 1);
			} else {
				names.put(name, (names.get(name) + 1));
			}
		}
		for (String name: names.keySet()) {
			println("Entry [" + name + "] has count " + names.get(name));
		}
		
	}

}
