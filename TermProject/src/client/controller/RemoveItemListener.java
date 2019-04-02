package client.controller;

import client.view.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 30/03/2019
 * 
 * This class provides the necessary methods to allow a user to remove a specified item from the inventory.   
 */
public class RemoveItemListener implements ActionListener {

	/**
	 * A MainMenu object which is being listened to by this class. 
	 */
	private MainMenu menu;
	
	
	/**
	 * Constructs a new object of type RemoveItemListener. 
	 * @param frame The MainMenu object being listened to by this class. 
	 */
	public RemoveItemListener(MainMenu frame) {
		menu = frame; 
	}
	
	public void actionPerformed(ActionEvent e) {
		int [] indices = menu.tableData.getSelectedRows();

		if(indices.length != 0) {
			int selection = JOptionPane.showConfirmDialog(null, "Are you sure you would like to remove these " + menu.tableData.getSelectedRowCount() + " item(s)","Confirmation", JOptionPane.YES_NO_OPTION);
		
			if(selection == JOptionPane.YES_OPTION && indices.length != 0) {
						
				int rowCorrection = 0;
				try {
				for(int i: indices) {
					((DefaultTableModel)menu.tableData.getModel()).removeRow(i-rowCorrection);
					rowCorrection++;
				}
				}
				catch(NullPointerException ex) {
					System.out.println("Exception 1");
				}
				
				try {
				((DefaultTableModel)menu.tableData.getModel()).fireTableRowsDeleted(indices[0], indices[indices.length - 1]);
				}
				catch(NullPointerException ex2) {
					System.out.println("Exception 2");
				}
			}
			
			}	
	}
	
	public void removeFromDataBase() {
		//TODO do something to remove an entry from the DataBase  
	}
}
