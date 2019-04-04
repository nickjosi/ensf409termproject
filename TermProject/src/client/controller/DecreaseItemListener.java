package client.controller;

import client.view.MainMenu;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * 
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 01/04/2019
 * 
 * This class provides the functionality for a user to decrease an items quantity that currently exists
 * in their inventory. 
 */
public class DecreaseItemListener extends GUIController {

	/**
	 * Constructs a new object of type DecreaseItemListener. 
	 * @param frame The MainMenu object being listened to by this class. 
	 */
	public DecreaseItemListener(MainMenu frame, Client user) {
		super(frame,user);
		frame.setDecreaseItemListener(this);	
	}
	
	
	/**
	 * Grabs the item currently highlighted by the user and decreases its quantity by -1 at every click of the button. 
	 * The table visually updates, as well as the inventory itself with the new quantity.  
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int row = menu.getTable().getSelectedRow();
		
		if(row != -1) {
			String name = (String)menu.getTable().getValueAt(row, 1); //Get the name of the item selected
			int quantity = Integer.parseInt((String)menu.getTable().getValueAt(row, 2)); //Get current value of quantity in stock
			client.getSocketOut().println("5\t" + name); //Send request to server 
			ArrayList<String> result = client.communicateWithServer();
			
			if(result.get(1).equals("true")){
				System.out.println("here");
				menu.getTable().setValueAt(Integer.toString(quantity-1), row, 2);
			}
			else {
				JOptionPane.showMessageDialog(null, "Not enough of this item remains in stock","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}

