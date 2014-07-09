/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

import acm.graphics.*;
import java.util.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
		createController();
		loadDatabase();
		addActionListeners();
//		addMouseListeners();
	    // You fill this in, along with any helper methods //
	}

	private void createController() {
		nameField = new JTextField(35);
		add(new JLabel("Name:"), NORTH);
		add(nameField,NORTH);
		nameField.addActionListener(this);
		graphButton = new JButton("Graph");
		deleteButton = new JButton("Delete");
		clearButton = new JButton("Clear");
		add(graphButton, NORTH);
		add(deleteButton, NORTH);
		add(clearButton, NORTH);
		graph = new NameSurferGraph();
		add(graph);
	}
	
	private void loadDatabase() {
		nameDataBase = new NameSurferDataBase(NAMES_DATA_FILE);
	}
	
	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nameField || e.getSource() == graphButton) {
//			println("Graph: "+ nameField.getText());
			graph.addEntry(nameDataBase.findEntry(nameField.getText().toLowerCase()));
//			println(nameDataBase.findEntry(nameField.getText().toLowerCase()));
		}
		if (e.getSource() == clearButton) {
			graph.clear();
		}
		
		if (e.getSource() == deleteButton) {
			graph.removeEntry(nameDataBase.findEntry(nameField.getText().toLowerCase()));
		}
	}
	
	private NameSurferGraph graph;
	private NameSurferDataBase nameDataBase;
	private JTextField nameField;
	private JButton graphButton;
	private JButton clearButton;
	private JButton deleteButton;

	
	
	
}
