import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;

import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;


public class FinalTestNumber2 extends GraphicsProgram {

	public void run() {

		northbutton = new JButton("North");
		southbutton = new JButton("South");
		eastbutton = new JButton("East");
		westbutton = new JButton("West");
		resetbutton = new JButton("Reset");
		add(northbutton, SOUTH);
		add(southbutton, SOUTH);
		add(westbutton, SOUTH);
		add(eastbutton, SOUTH);
		add(resetbutton, SOUTH);
		northbutton.addActionListener(this);
		southbutton.addActionListener(this);
		eastbutton.addActionListener(this);
		westbutton.addActionListener(this);
		resetbutton.addActionListener(this);
		
		centerX = getWidth()/2;
		centerY = getHeight()/2;

		cross = new DrawnX();
		cross.addActionListener(this);
		add(cross, centerX, centerY);
		addActionListeners();
		addMouseListeners();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == northbutton) {
			cross.move(0, -20);
			redline = new GLine(cross.getX(), cross.getY(), cross.getX(), cross.getY()+20 );
		} else if (e.getSource() == southbutton) {
			cross.move(0, 20);
			redline = new GLine(cross.getX(), cross.getY(), cross.getX(), cross.getY()-20 );
		} else if (e.getSource() == eastbutton) {
			cross.move(20, 0);
			redline = new GLine(cross.getX(), cross.getY(), cross.getX()-20, cross.getY() );
		} else if (e.getSource() == westbutton) {
			cross.move(-20, 0);
			redline = new GLine(cross.getX(), cross.getY(), cross.getX()+20, cross.getY() );
		} else {
		}
		redline.setColor(Color.RED);
		objMap.put(count+"", redline);
		count++;
		add(redline);
		
		if (e.getSource() == resetbutton) {
			for (String key: objMap.keySet()) {
				remove(objMap.get(key));
			}
			remove(cross);
			add(cross, centerX, centerY);
		}
	}
	
	private Map<String, GObject> objMap = new HashMap<String, GObject>();
	private Integer count = 1;
	private double centerX;
	private double centerY;

	private DrawnX cross; //object being dragged
	private GLine redline;

	public JButton northbutton;
	private JButton southbutton;
	private JButton eastbutton;
	private JButton westbutton;
	private JButton resetbutton;

}


