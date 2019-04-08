package client.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.view.MainMenu;

public class LoginListener extends GUIController {

	public LoginListener(MainMenu frame, Client user) {
		super(frame, user);
		menu.setLoginListener(this);
	}

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
		if(response.get(0).equals("success")) {
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
