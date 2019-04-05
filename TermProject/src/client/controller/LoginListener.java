package client.controller;

import java.awt.event.ActionEvent;

import client.view.MainMenu;

public class LoginListener extends GUIController {

	public LoginListener(MainMenu frame, Client user) {
		super(frame, user);
		menu.setLoginListener(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		menu.setNorthVisibility(true);
		menu.setSouthVisibility(true);
		menu.showTableScreen();
		
	}
}
