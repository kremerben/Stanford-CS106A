import acm.program.*;


public class diamondOutline extends ConsoleProgram {

	public void run() {
		drawDiamondOutline(33);
	}
	
	private void drawDiamondOutline(int size) {
		String lineFirst = "";
		lineFirst = addSpaces(lineFirst, size-1);
		println(lineFirst);	
		// upper middle section
		int rowNumber = 1;
		for (int row = size-1; row > 0; row--) {
//		String lineMiddle = "";
//			lineMiddle = addSpaces(lineMiddle, row-1);
//			lineMiddle = addSpaces(lineMiddle, (2*rowNumber-1));
			println(addSpaces(addSpaces("", row-1), (2*rowNumber-1)));
			rowNumber++;
		}
		// lower middle section
		rowNumber--;
		for (int row = 1; row < size-1; row++) {
			rowNumber--;
//			String lineMiddle = "";
//				lineMiddle = addSpaces(lineMiddle, row);
//				lineMiddle = addSpaces(lineMiddle, (2*(rowNumber)-1));
				println(addSpaces(addSpaces("", row), (2*(rowNumber)-1)));
			}
		
		// start & end row
		if (size > 1) {
		println(lineFirst);
		}
		println(size);
	}	
	
	private String addSpaces(String stringPassed, Integer spaces) {
		for (int i = 0; i < spaces; i++) {
			stringPassed += " ";
		}
		return stringPassed += "*";
	}
	
}
