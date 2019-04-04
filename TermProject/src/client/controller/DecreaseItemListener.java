package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.view.MainMenu;

public class DecreaseItemListener extends GUIController implements ActionListener {

	/**
	 * Constructs a new object of type DecreaseItemListener. 
	 * @param frame The MainMenu object being listened to by this class. 
	 */
	public DecreaseItemListener(MainMenu frame, Client user) {
		super(frame,user);
		frame.setDecreaseItemListener(this);	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int row = menu.tableData.getSelectedRow();
		
		if(row != -1) {
			String name = (String)menu.tableData.getValueAt(row, 1); //Get the name of the item selected
			int quantity = Integer.parseInt((String)menu.tableData.getValueAt(row, 2)); //Get current value of quantity in stock
			client.getSocketOut().println("5\t" + name); //Send request to server 
			ArrayList<String> result = client.communicateWithServer();
			
			if(result.get(1).equals("true")){
				System.out.println("here");
				menu.tableData.setValueAt(Integer.toString(quantity-1), row, 2);
			}	
		}
	}
	
}

