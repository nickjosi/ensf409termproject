package client.controller;

import java.awt.event.ActionListener;

import client.view.MainMenu;

/**
 * This class provides the inherited methods implemented from ActionListener to all of its 
 * sub classes, as well as providing access to the MainMenu object a Client is interacting with and the Client themselves. 
 *
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 02/04/2019
 */
public abstract class GUIController implements ActionListener  {

	/**
	 * The MainMenu object the client is interacting with. 
	 */
	protected MainMenu menu;
	
	/**
	 * The Client interacting with the user interface. 
	 */
	protected Client client; 
	
	/**
	 * Constructs a new object of type GUIController. 
	 * @param frame The MainMenu object the user is interacting with. 
	 * @param user The client interacting with the user interface. 
	 */
	public GUIController(MainMenu frame, Client user) {
		menu = frame;
		client = user;
	}
	
}
