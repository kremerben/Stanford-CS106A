import acm.program.*;



public class PiSeries extends ConsoleProgram {
	
	public void run(){
		double pi = 0.0;
		double currentTerm = 1.0;
//		double currentI = 1.0;
		double currentDenom = 1.0;
		boolean subtract = false;
		while(currentTerm > TERM_THRESHOLD) {
			if (subtract) {
				pi -= currentTerm;
			} else {
				pi += currentTerm;
			}
			subtract = !subtract;
			currentDenom += 2;
			currentTerm = 1 / currentDenom;
		}
		pi *= 4;
		println("pi is approximately " + pi);
		println(currentDenom);
	}
	
	private static final double TERM_THRESHOLD = .000001;
}