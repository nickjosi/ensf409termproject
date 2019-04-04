package client.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;

import client.view.MainMenu;

public class CloseListener extends WindowAdapter {

	private MainMenu menu;
	private Client client;
	
	public CloseListener(MainMenu frame, Client user) {
		menu = frame;
		client = user;
		frame.setCloseWindowListener(this);
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		client.getSocketOut().println("7");
	}

}
