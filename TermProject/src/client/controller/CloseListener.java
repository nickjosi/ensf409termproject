package client.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import client.view.MainMenu;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 01/04/2019
 * 
 * This class sends the necessary information to the server to inform it that a client has closed(disconnected) their
 * user interface and quit the application. 
 */
public class CloseListener extends WindowAdapter {

	/**
	 * The MainMenu object the client is interacting with. 
	 */
	private MainMenu menu;
	
	/**
	 * The Client interacting with the user interface. 
	 */
	private Client client;
	
	/**
	 * Constructs a new object of type CloseListener. 
	 * @param frame The MainMenu object the user is interacting with. 
	 * @param user The Client interacting with the user interface.
	 */
	public CloseListener(MainMenu frame, Client user) {
		menu = frame;
		client = user;
		menu.setCloseWindowListener(this);
	}
	
	/**
	 * Sends a message to the server indicating a client has quit the application. 
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		client.getSocketOut().println("7");
	}

}
