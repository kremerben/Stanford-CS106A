/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		createController();
		createDatabase();
		addActionListeners();
	}

	private void createController() {
		//NORTH Controllers
		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(new JLabel("Name:"), NORTH);
		add(nameField,NORTH);
		//		nameField.addActionListener(this);
		addButton = new JButton("Add");
		add(addButton, NORTH);
		deleteButton = new JButton("Delete");
		add(deleteButton, NORTH);
		lookupButton = new JButton("Lookup");
		add(lookupButton, NORTH);

		//WEST controllers
		statusField = new JTextField(TEXT_FIELD_SIZE);
		add(statusField,WEST);
		statusField.addActionListener(this);
		chgStatusButton = new JButton("Change Status");
		add(chgStatusButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		add(pictureField,WEST);
		pictureField.addActionListener(this);
		chgPictureButton = new JButton("Change Picture");
		add(chgPictureButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		friendField = new JTextField(TEXT_FIELD_SIZE);
		add(friendField,WEST);
		friendField.addActionListener(this);
		addFriendButton = new JButton("Add Friend");
		add(addFriendButton, WEST);

		fpCanvas = new FacePamphletCanvas();
		add(fpCanvas);
	}

	private void createDatabase() {
		fpDatabase = new FacePamphletDatabase();	
	}

	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked or interactors are used, so you will have to add code
	 * to respond to these actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nameField) {
			fpCanvas.showMessage("nameField: "+ nameField.getText());
		}
		// ADD USER button
		if (e.getSource() == addButton) {
			if (!nameField.getText().isEmpty()) {
				if (fpDatabase.containsProfile(nameField.getText())) {
					fpCanvas.showMessage(nameField.getText() + " already exists");
				} else {
					fpDatabase.addProfile(new FacePamphletProfile(nameField.getText()));
					fpCanvas.showMessage("added:" + fpDatabase.getProfile(nameField.getText()).toString());
				}
				currentUser = fpDatabase.getProfile(nameField.getText());
			}
			fpCanvas.displayProfile(currentUser);
		}
		// DELETE USER button
		if (e.getSource() == deleteButton) {
			if (!nameField.getText().isEmpty()) {
				if (fpDatabase.containsProfile(nameField.getText())) {
					fpDatabase.deleteProfile(nameField.getText());
					fpCanvas.showMessage("removed: " + nameField.getText());
					currentUser = null;
				} else {
					fpCanvas.showMessage("user does not exist");
				}
			}
		}
		// LOOKUP USER button
		if (e.getSource() == lookupButton) {
			if (!nameField.getText().isEmpty()) {
				if (fpDatabase.containsProfile(nameField.getText())) {
					currentUser = fpDatabase.getProfile(nameField.getText());
					fpCanvas.showMessage("lookup found: "+ currentUser.toString());
					//fpCanvas.displayProfile(currentUser);
				} else {
					fpCanvas.showMessage("user does not exist");
				}			
			}
			fpCanvas.displayProfile(currentUser);
		}
		// CHANGE STATUS button
		if (e.getSource() == statusField || e.getSource() == chgStatusButton) {
			if (!statusField.getText().isEmpty()) {
				if (!currentUser.equals(null)) {
					currentUser.setStatus(statusField.getText());
					fpCanvas.showMessage("statusField for: "+ currentUser.getName() + " is " + currentUser.getStatus());
				} else {
					fpCanvas.showMessage(currentUser + " doesnt exist");
				}
			}
			fpCanvas.displayProfile(currentUser);
		}
		// CHANGE PICTURE button
		if (e.getSource() == pictureField || e.getSource() == chgPictureButton) {
			if (!pictureField.getText().isEmpty()) {
				if (!currentUser.equals(null)) {
					//GImage imageName = null;
					try {
						GImage imageName = new GImage(("images/" + pictureField.getText()));
						currentUser.setImage(imageName);
					} catch (ErrorException ex) {
						fpCanvas.showMessage(pictureField.getText() + " is not in the images directory");
					}
					//fpCanvas.displayProfile(currentUser);
					fpCanvas.showMessage("pictureField for: "+ currentUser.getName() + " is " + currentUser.getImage().toString());
				} else {
					fpCanvas.showMessage(currentUser + " doesnt exist");
				}
				fpCanvas.displayProfile(currentUser);
			}
		}

		// ADD FRIEND button
		if (e.getSource() == friendField || e.getSource() == addFriendButton) {
			if (!friendField.getText().isEmpty()) {
				if (!currentUser.equals(null)) {
					fpCanvas.showMessage("friendField: "+ friendField.getText());
					if (!currentUser.getName().equalsIgnoreCase(friendField.getText())) {
						if (fpDatabase.containsProfile(friendField.getText())) {
							if (currentUser.addFriend(friendField.getText())) {
								fpDatabase.getProfile(friendField.getText()).addFriend(currentUser.getName());
								fpCanvas.showMessage("added Friend:" + friendField.getText());
							} else {
								fpCanvas.showMessage("friend already added");
							}
						} else {
							fpCanvas.showMessage(friendField.getText() + " doesnt exist");
						}
					} else {
						fpCanvas.showMessage("you cant add yourself");
					}
				} else {
					fpCanvas.showMessage(currentUser + " doesnt exist");
				}
			}
		}
		fpCanvas.displayProfile(currentUser);
	}


	private FacePamphletProfile currentUser;
	private JTextField nameField;
	private JButton addButton;
	private JButton deleteButton;
	private JButton lookupButton;
	private JTextField statusField;
	private JButton chgStatusButton;
	private JTextField pictureField;
	private JButton chgPictureButton;
	private JTextField friendField;
	private JButton addFriendButton;

	private FacePamphletCanvas fpCanvas;
	private FacePamphletDatabase fpDatabase;


}
