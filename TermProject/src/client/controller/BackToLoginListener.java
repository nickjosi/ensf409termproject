package client.controller;

import client.view.MainMenu;
import java.awt.event.ActionEvent;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 01/04/2019
 *
 * This class provides the functionality of the "Back To Login" button displayed to the user. 
 */
public class BackToLoginListener extends GUIController{
	
	/**
	 * Constructs a new object of type BackToLoginListener
	 * @param frame The MainMenu object the user is interacting with. 
	 * @param user The client interacting with the user interface. 
	 */
	public BackToLoginListener(MainMenu frame, Client user) {
		super(frame,user);
		frame.setBackToLoginListener(this);
	}
	
	/**
	 * Toggles the display of certain buttons to the User interface depending which screen they are currently viewing
	 * (ie. Login screen or inventory screen).
	 */
	public void actionPerformed(ActionEvent e) {
		menu.showHomeScreen();
		menu.setNorthVisibility(false);
		menu.setSouthVisibility(false);
		menu.getInventoryButton().setText("View Inventory");
	}

}
