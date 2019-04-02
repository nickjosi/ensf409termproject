package client.controller;

import client.view.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BackToLoginListener implements ActionListener{

	private MainMenu menu;
	
	public BackToLoginListener(MainMenu frame) {
		menu = frame; 
	}
	
	public void actionPerformed(ActionEvent e) {
		menu.showLoginScreen();
		menu.setBackButtonVisibility(false);
		menu.setAddButtonVisibility(false);
		menu.setRemoveButtonVisibility(false);
	}
}
