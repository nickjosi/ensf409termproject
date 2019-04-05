package client.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.view.MainMenu;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 30/03/2019
 * 
 * This class contains the necessary methods to allow a user to search for any item in the inventory by the item's ID or 
 * name by entering it into a text field. 
 */
public class SearchBarListener extends GUIController {


	/**
	 * Constructs a new object of type SearchBarListener
	 * @param frame The MainMenu object the user is interacting with.
	 * @param user The client interacting with the user interface. 
	 */
	public SearchBarListener(MainMenu frame, Client user) {
		super(frame,user);
		frame.setSearchBarListener(this);
	}
	
	
	/**
	 * Sends the information taken from the TextField to the server to perform a search on the item specified. 
	 * If the item doesn't exist an error message is displayed informing the user, otherwise all of the items information
	 * is displayed. 
	 */
	public void actionPerformed(ActionEvent e) {
		String input = menu.searchBar.getText();
		
		try {
			Integer.parseInt(input);
			client.getSocketOut().println("3\t" + input);
		}
		catch(NumberFormatException ex) {
			client.getSocketOut().println("2\t" + input);
		}
		finally {
			JPanel info = new JPanel();
			ArrayList<String> toolResponse = client.communicateWithServer();
			if(toolResponse.get(0).equals("")) {
				JOptionPane.showMessageDialog(null, "Item not found in inventory.","Error",JOptionPane.ERROR_MESSAGE);
			}
			else {
				String[] line = toolResponse.get(0).split("\t");
				JLabel toolInfo = new JLabel("<html><table>"
						+ "<tr>"
						+ "<td><b>Item Name:</b></td>" 			+ "<td>"+line[1]+"</td>"
						+ "</tr><tr>"
						+ "<td><b>Item ID:</b></td>" 			+ "<td>"+line[0]+"</td>"
						+ "</tr><tr>"
						+ "<td><b>Quantity in Stock:</b></td>" 	+ "<td>"+line[2]+"</td>"
						+ "</tr><tr>"
						+ "<td><b>Price:</b></td>" 				+ "<td>"+line[3]+"</td>"
						+ "</tr><tr>"
						+ "<td> </td>"							+ "<td> </td>"
						+ "</tr><tr>"
						+ "<td><b>Supplier:</b></td>" 			+ "<td>"+line[4]+"</td>" 
						+ "</tr><tr>"
						+ "<td><b>Supplier ID:</b></td>" 		+ "<td>"+line[5]+"</td>" 
						+ "</tr><tr>"
						+ "<td><b>Contact Name:</b></td>" 		+ "<td>"+line[6]+"</td>"
						+ "</tr><tr>"
						+ "<td><b>Address:</b></td>" 			+ "<td>"+line[7]+"</td>"
						+ "</tr></table></html>");

				info.add(toolInfo);
									
				JOptionPane.showMessageDialog(null,info,"Item Information", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
}
