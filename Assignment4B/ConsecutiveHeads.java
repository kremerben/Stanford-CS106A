import acm.program.*;
import acm.util.*;


public class ConsecutiveHeads extends ConsoleProgram {
	
	public void run() {
		int consecutiveHeads = 10; //target
		
		int totalHeads = 0;
		int totalTails = 0;
		int headsCounter = 0;
		int flips = 0;
		while (headsCounter < consecutiveHeads) {
			// true = heads, false = tails
			if (rgen.nextBoolean()) {
				println("heads");
				headsCounter++;
				totalHeads++;
			} else {
				headsCounter = 0;
				println("tails");
				totalTails++;
			}
			flips++;
		}
		println("It took " + flips +" flips to get "+consecutiveHeads+" consecutive heads.");
		println(totalHeads + " Heads");
		println(totalTails + " Tails");
	}

 private RandomGenerator rgen = RandomGenerator.getInstance();
}
