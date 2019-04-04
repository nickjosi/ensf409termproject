package client.controller;

import client.view.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;


public class BackToLoginListener extends GUIController implements ActionListener{
	
	public BackToLoginListener(MainMenu frame, Client user) {
		super(frame,user);
		frame.setBackToLoginListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		menu.showLoginScreen();
		menu.setBackButtonVisibility(false);
		menu.setAddButtonVisibility(false);
		menu.setRemoveButtonVisibility(false);
		menu.setSearchBarVisibility(false);
	}

}
