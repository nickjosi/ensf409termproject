package client.controller;

import client.view.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;


/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 30/03/2019
 * 
 * This class provides the necessary methods to update the display of the inventory to the user from the click of a button.  
 */
public class ViewInventoryListener implements ActionListener {

	/**
	 * A MainMenu object which is being listened to by this class. 
	 */
	private MainMenu menu;
	
	/**
	 * Constructs a new object of TypeInventoryListener. 
	 * @param frame The MainMenu object the ViewInventoryListener listens to. 
	 */
	public ViewInventoryListener(MainMenu frame) {
		menu = frame;
	}
	

	/**
	 * Sets the JTable object in the MainMenu to a read only DefaultTableModel with the most up to date Inventory information from 
	 * the data base. 
	 * 
	 * @Override
	 */
	public void actionPerformed(ActionEvent e) {
		String [] headers = {"Item ID", "Item Name", "Quantity in Stock", "Price ($)"};
		menu.tableData.setModel(new DefaultTableModel(pullFromDataBase(),headers ) {
			@Override
			public boolean isCellEditable(int row,int col) {
				return false;
			}
		});
		
		
		//TODO Make this listener access the DB and an pull the necessary 
		//inventory data, then change the button text to "Refresh Inventory"
		
		menu.showItemList();
		menu.setBackButtonVisibility(true);
		menu.setRemoveButtonVisibility(true);
		menu.setAddButtonVisibility(true);
		JButton source = (JButton) e.getSource();
		source.setText("Refresh Inventory");	
	}
	
	
	/**
	 * Sends a query to the data base requesting all of the most up to date inventory information. 
	 * @return The 2D-Array of data that contains all the data fields representing Tools in a retail store. 
	 */
	public static Object[][] pullFromDataBase() { //This method will actually communicate with the server to pull info from the database
		Object[][] toolInfo = {{1000,"Knock Bits", 88, 12.67, 8015},{1001, "Widgets", 10, 35.50, 8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1004, "Grommets", 20, 23.45, 8001},{1005,"Wedges",15,10.15,8004}
							  ,{1006, "Grommets", 20, 23.45, 8001},{1007,"Wedges",15,10.15,8004}
							  ,{1008, "Grommets", 20, 23.45, 8001},{1008,"Wedges",15,10.15,8004}
							  ,{1009, "Grommets", 20, 23.45, 8001},{1010,"Wedges",15,10.15,8004}
							  ,{1011, "Grommets", 20, 23.45, 8001},{1012,"Wedges",15,10.15,8004}
							  ,{1013, "Grommets", 20, 23.45, 8001},{1014,"Wedges",15,10.15,8004}
							  ,{1015, "Grommets", 20, 23.45, 8001},{1016,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}
							  ,{1002, "Grommets", 20, 23.45, 8001},{1003,"Wedges",15,10.15,8004}};
		
		return toolInfo;		
	}
	
	
	
}
