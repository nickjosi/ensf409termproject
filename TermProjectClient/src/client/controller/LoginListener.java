package client.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.view.MainMenu;

/**
 * @author Carter Shaul/Nick Park
 * @version 2
 * @since 30/03/2019
 * 
 * This class provides the necessary methods for user login functionality.
 */
public class LoginListener extends GUIController {

	/**
	 * Constructs a new object of type LoginListener. 
	 * @param frame The MainMenu object being listened to by this class. 
	 * @param user The client interacting with the MainMenu object. 
	 */
	public LoginListener(MainMenu frame, Client user) {
		super(frame, user);
		menu.setLoginListener(this);
	}

	/**
	 * Checks if the username and password entered by the user are valid
	 * with the database table, and grants access accordingly.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String username = menu.getUsername();
		String password = menu.getPassword();
		if(username.equals("") || password.contentEquals("")) {
			JOptionPane.showMessageDialog(null, "The username and/or password you've entered is incorrect.",
					"Login failed",JOptionPane.ERROR_MESSAGE);
			return;
		}
		client.getSocketOut().println("6\t" + username + "\t" + password);
		
		ArrayList<String> response = client.communicateWithServer();
		if(response.get(0).equals("Login success")) {
			menu.setNorthVisibility(true);
			menu.setSouthVisibility(true);
			menu.showTableScreen();
		}
		else {
			JOptionPane.showMessageDialog(null, "The username and/or password you've entered is incorrect.",
					"Login failed",JOptionPane.ERROR_MESSAGE);
		}	
	}
	
}
