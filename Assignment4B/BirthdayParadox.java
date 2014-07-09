import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import acm.program.*;
import acm.util.*;
public class BirthdayParadox extends ConsoleProgram {
	
	public void run() {
//		int randBirth = rgen.nextInt(1, 365);
//		ArrayList<Integer> birthdates = new ArrayList<Integer>();
		
		Map<Integer, Boolean> birthdates = new HashMap<Integer, Boolean>();
		println("Estimated chance of having a common birthday:");
		for (int i = LOWER_LIMIT; i <= UPPER_LIMIT; i++){
			double chance = 0.0;
			int birthday = 0;
			double totMatches = 0.0;
			for (int j = 0; j < N_TRIALS; j++) {
				boolean match = false;
				for (int k = 0; k <= i; k++) {
					birthday = rgen.nextInt(1, 365);
					if (birthdates.get(birthday) == null) {
						birthdates.put(birthday, true);
					} else {
						match = true;
					}
				}
				if (match) {
					totMatches++;
				}
				birthdates.clear();
			}
//			println(totMatches);
			chance = (totMatches*100)/N_TRIALS;
			println(i+" people -> "+chance+"%");
		}
				
	}
	
	/* Private constants */
	 private static final int N_TRIALS = 10000;
	 private static final int LOWER_LIMIT = 39;
	 private static final int UPPER_LIMIT = 56;
	/* Instance variables */
	 private RandomGenerator rgen = RandomGenerator.getInstance(); 
}