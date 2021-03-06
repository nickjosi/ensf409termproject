package client.controller;

import client.view.MainMenu;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * This class provides the functionality of the "Back To Login" button displayed to the user. 
 * 
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 01/04/2019
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
	 * Toggles the display of certain buttons to the User interface so that
	 * the user is returned to the login screen.
	 */
	public void actionPerformed(ActionEvent e) {
		menu.showHomeScreen();
		menu.clearTable();
		menu.clearUsername();
		menu.clearPassword();
		menu.setNorthVisibility(false);
		menu.setSouthVisibility(false);
		menu.getInventoryButton().setText("View Inventory");
	}

}
