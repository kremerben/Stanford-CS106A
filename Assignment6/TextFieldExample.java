import java.awt.event.*;
import javax.swing.*;
import acm.program.*;



public class TextFieldExample extends ConsoleProgram {

	public void init(){
		setFont("Courier 24");
		nameField = new JTextField(15);
	//	nameField.setActionCommand("name"); //adds name to field name - see below
		add(new JLabel("Name"), SOUTH);
		add(nameField, SOUTH);
		nameField.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
	//	if (e.getActionCommand().equals("name")) { //another way to trace the text field
		if (e.getSource() == nameField) {
			println("Hi " + nameField.getText());
		}
	}
	
	private JTextField nameField;
	
}
