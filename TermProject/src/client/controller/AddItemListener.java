//***NOTE THIS FUNCTIONALITY WAS NOT USED OR IMPLEMENTED IN THIS MILESTONE***//

package client.controller;

import client.view.MainMenu;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 30/03/2019
 * 
 * This class provides the necessary methods to allow a user to input the information for a new inventory item by entering data into
 * separate text fields at the click of a button. 
 */
public class AddItemListener extends GUIController{

	//TODO Make this method add the item the user inputs to the inventory. If the item already exists or if the item ID's are the same than
	//inform the user they must re-enter. 
	
	public AddItemListener(MainMenu frame, Client user) {
		super(frame,user);
		//frame.setAddItemListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		JTextField idField = new JTextField();
		JTextField nameField = new JTextField();
		JTextField priceField = new JTextField();
		JTextField quantityField = new JTextField();
		
		Object [] message = {"Enter Item ID",idField,"Enter Item Name",nameField,
				"Enter Item's Price",priceField,"Enter Item Quantity", quantityField};

		int option = JOptionPane.showConfirmDialog(null,message,"Add a New Tool to Inventory",JOptionPane.OK_CANCEL_OPTION);
		
		if(option == JOptionPane.OK_OPTION) {
			JOptionPane.showMessageDialog(null, "It worked");
		}
	}

}
