import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import acm.program.*;
import acm.graphics.*;

public class BoxDiagram extends GraphicsProgram {
	
	public void init() {
		nameField = new JTextField(30);
		add(new JLabel("Name"), SOUTH);
		add(nameField, SOUTH);
		nameField.addActionListener(this);
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		clearButton = new JButton("Clear");
		add(addButton, SOUTH);
		add(removeButton, SOUTH);
		add(clearButton, SOUTH);
		
		addActionListeners();
		addMouseListeners();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nameField || e.getSource() == addButton) {
			//add box
			GLabelBox addBox = new GLabelBox(nameField.getText(),BOX_WIDTH,BOX_HEIGHT);
			addBox.addActionListener(this);
//			addBox.setLabel("asdf");
//			addBox.setActionCommand(nameField.getText());
			objMap.put(nameField.getText(), addBox);
			add(addBox, (getWidth()-addBox.getWidth())/2, (getHeight()-addBox.getHeight())/2);
		}
		if (e.getSource() == clearButton) {
				for (String key: objMap.keySet()) {
				remove(objMap.get(key));
			}
		}
		if (e.getSource() == removeButton) {
			gobj = objMap.get(nameField.getText());
			remove(gobj);
		}
	}
	
	
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		gobj = getElementAt(last);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (gobj != null) {
			gobj.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}		
	}
	
	public void mouseClicked(MouseEvent e) {
		if (gobj != null) gobj.sendToFront();
	}
	
	private Map<String, GObject> objMap = new HashMap<String, GObject>();

	private GCompound GLabelBox;
//	private GLabelBox addBox;
	
	private JTextField nameField;
	private JButton addButton;
	private JButton clearButton;
	private JButton removeButton;
	
	private GObject gobj; //object being dragged
	private GPoint last; //last mouse position
	
	private static final double BOX_WIDTH = 120;
    private static final double BOX_HEIGHT = 50;
    
	
}