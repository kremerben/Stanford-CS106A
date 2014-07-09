import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

public class DrawnX extends GCompound {

	public DrawnX() {
		//Double?
		GLine drawLeft = new GLine(-5, -5,+5,+5);
		GLine drawRight = new GLine(+5,  -5, -5, +5);
		add(drawLeft);
		add(drawRight);
	}

}