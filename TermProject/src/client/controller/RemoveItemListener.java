package client.controller;

import client.view.MainMenu;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Carter Shaul/Nick Park
 * @version 2
 * @since 30/03/2019
 * 
 * This class provides the necessary methods to allow a user to remove a specified item from the inventory.   
 */
public class RemoveItemListener extends GUIController {

		
	/**
	 * Constructs a new object of type RemoveItemListener. 
	 * @param frame The MainMenu object being listened to by this class. 
	 * @param user The client interacting with the MainMenu object. 
	 */
	public RemoveItemListener(MainMenu frame, Client user) {
		super(frame,user);
		frame.setRemoveItemListener(this);	
	}
	
	/**
	 * Removes the user selected items from the inventory. 
	 */
	public void actionPerformed(ActionEvent e) {
		int [] indices = menu.getTable().getSelectedRows();

		if(indices.length != 0) {
			int selection = JOptionPane.showConfirmDialog(null, "Are you sure you would like to remove these " + menu.getTable().getSelectedRowCount() + " item(s)","Confirmation", JOptionPane.YES_NO_OPTION);
		
			if(selection == JOptionPane.YES_OPTION && indices.length != 0) {
						
				int rowCorrection = 0;

				for(int i: indices) {
					client.getSocketOut().println("4\t" + menu.getTable().getValueAt(i-rowCorrection, 1));
					((DefaultTableModel)menu.getTable().getModel()).removeRow(i-rowCorrection);
					rowCorrection++;
				}

				((DefaultTableModel)menu.getTable().getModel()).fireTableRowsDeleted(indices[0], indices[indices.length - 1]);
		
			}		
		}	
	}
	
}
