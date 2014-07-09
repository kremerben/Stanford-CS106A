
import java.util.*;
import acm.program.*;


public class UniqueNames extends ConsoleProgram {
	
	public void run(){
		while (true) {
			String name = readLine("Enter name: ");
			if (name.isEmpty()) break;
			if (!names.contains(name)) {
				names.add(name);
			}
		}
		println("Unique name list contains:");
		for (int i = 0; i < names.size(); i++) {
			println(names.get(i));
		}
	}
		
	private ArrayList<String> names = new ArrayList<String>();

}
