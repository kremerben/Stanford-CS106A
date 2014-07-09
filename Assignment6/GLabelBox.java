import java.awt.Color;

import acm.graphics.*;
import acm.util.RandomGenerator;


public class GLabelBox extends GCompound {

	public GLabelBox(String labelName, Double boxWidth, Double boxHeight) {
		outline = new GRect(boxWidth, boxHeight);
		add(outline);
		outline.setFilled(true);
		outline.setColor(rgen.nextColor());
//		outline.setColor(rgen.nextColor());
		outline.setFillColor(Color.white);
		label = new GLabel(labelName);
		add(label, (boxWidth-label.getWidth())/2, (boxHeight-label.getHeight()));
	}
	private GRect outline;
	private GLabel label;
	
	private RandomGenerator rgen = RandomGenerator.getInstance();

}
