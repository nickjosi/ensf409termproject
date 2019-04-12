package client.controller;

import client.view.MainMenu;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 * This class provides the necessary methods to update the display of the inventory to the user from the click of a button.  
 * 
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 30/03/2019
 */
public class ViewInventoryListener extends GUIController {
	
	/**
	 * Constructs a new object of TypeInventoryListener. 
	 * @param frame The MainMenu object the ViewInventoryListener listens to.
	 * @param user the Client
	 */
	public ViewInventoryListener(MainMenu frame,Client user) {
		super(frame,user);
		frame.setViewInventoryListener(this);
	}

	/**
	 * Sets the JTable object in the MainMenu to a read only DefaultTableModel with the most up to date Inventory information from 
	 * the data base.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String [] headers = {"Item ID", "Item Name", "Quantity in Stock", "Price ($)"};
		
		
		menu.getTable().setModel(new DefaultTableModel(headers,0) {
			@Override
			public boolean isCellEditable(int row,int col) {
				return false;
			}
		});
		

		client.getSocketOut().println("1");
		menu.showItemList();
		menu.updateTable(client.communicateWithServer());
		menu.getInventoryButton().setText("Refresh Inventory");	
	}
	
}
