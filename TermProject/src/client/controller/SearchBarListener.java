package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.view.MainMenu;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 30/03/2019
 * 
 * This class contains the necessary methods to allow a user to search for any item in the inventory by the item's ID or 
 * name by entering it into a text field. 
 */
public class SearchBarListener extends GUIController implements ActionListener {

	
	public SearchBarListener(MainMenu frame, Client user) {
		super(frame,user);
		frame.setSearchBarListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String input = menu.searchBar.getText();
		
		try {
			int id = Integer.parseInt(input);
			System.out.println("here");
			client.getSocketOut().println("3\t" + input);
		}
		catch(NumberFormatException ex) {
			client.getSocketOut().println("2\t" + input);
		}
		finally {
			ArrayList<String> response = client.communicateWithServer();
			if(response.get(0).equals("")) {
				JOptionPane.showMessageDialog(null, "Item could not be located","Error",JOptionPane.ERROR_MESSAGE);
			}
			else {
				String[] line = response.get(0).split("\t");
				JOptionPane.showMessageDialog(null,"Item ID: " + line[0] + "\n\nItem Name: " + line[1] 
											+"\n\nQuantity in Stock: " + line[2] + "\n\nPrice: " + line[3], "Item Found", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
}
