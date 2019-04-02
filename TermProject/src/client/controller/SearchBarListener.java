package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 30/03/2019
 * 
 * This class contains the necessary methods to allow a user to search for any item in the inventory by the item's ID or 
 * name by entering it into a text field. 
 */
public class SearchBarListener implements ActionListener {

	//TODO make this method search through the inventory and return the result of the item to the user if it exists and
	//inform them if the item does not exist. 
	
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "It Works");
	}
	
}
