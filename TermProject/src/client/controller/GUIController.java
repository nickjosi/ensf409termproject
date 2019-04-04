package client.controller;
import java.io.PrintWriter;

import client.view.MainMenu;

public class GUIController {

	String command;
	protected MainMenu menu;
	protected Client client; 
	
	public GUIController(MainMenu frame, Client user) {
		menu = frame;
		client = user;
	}
	
}
