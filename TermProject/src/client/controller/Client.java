package client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import client.view.MainMenu;

/**
 * Client class for playing the TicTacToe game.
 * 
 * @author NickPark
 * @version 1.0
 * @since March 20, 2019
 */
public class Client {
	/**
	 * The Socket object which is the endpoint for the communication
	 * with the server.
	 */
	private Socket socket;
	/**
	 * The PrintWriter object which writes to the socket output 
	 * stream to communicate with the server.
	 */
	private PrintWriter socketOut;
	/**
	 * The BufferedReader object which reads the socket input
	 * stream to receive data from the server.
	 */
	private BufferedReader socketIn;
	/**
	 * The BufferedReader object which reads user input.
	 */
	private BufferedReader stdIn;
	/**
	 * The MainMenu object which is displayed to the client.
	 */
	private MainMenu menu;
	/**
	 * The GUIController object which manages all client interaction with the MainMenu
	 */
	private GUIController menuController;

	
	/**
	 * Constructs a Client for a Server with the specified server
	 * name and port.
	 * @param serverName name of the Server to be connected to
	 * @param portNumber port number of the Server to be connected to
	 */
	public Client(String serverName, int portNumber,MainMenu frame) {
		try {
			socket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter((socket.getOutputStream()), true);
			menu = frame;
		} catch (IOException e) {
			System.out.println("Unable to create new client.");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Gets input from the user, sends the input to the Server, 
	 * and prints out the response from the Server.
	 */
	public ArrayList<String> communicateWithServer() {
		ArrayList<String> response = new ArrayList<String>();
		try {
				String read = "";
		
				while (true) {
		
					read = socketIn.readLine();
					if(read.contains("\0")) {
						read = read.replace("\0","");
						response.add(read);
						break;
					}
					if(read.equals("QUIT")) {
						break;
					}
					response.add(read);
				}
			}
			catch (Exception e) {
				System.out.println("Error communicating with server.");
				e.printStackTrace();
//				System.out.println(e.printStackTrace());
				try {
					stdIn.close();
					socket.close();
				}
				catch(IOException ex) {
					System.out.println("Unable to close IO streams.");
				}
			}
		for(String s: response) {
			System.out.println(s);
		}
		return response;
	}
	
	
	/**
	 * Gets the handle on the output PrintWriter object. 
	 * @return The PrintWriter object used to output information to the socket. 
	 */
	public PrintWriter getSocketOut() {
		return socketOut;
	}

	public static void main(String[] args) throws IOException {
		MainMenu frame = new MainMenu();
		
		Client client = new Client("localhost", 9898,frame);
		GUIController search = new SearchBarListener(frame,client);
		GUIController view = new ViewInventoryListener(frame,client);
		GUIController back = new BackToLoginListener(frame,client);
		GUIController add = new AddItemListener(frame,client);
		GUIController remove = new RemoveItemListener(frame,client);
		GUIController decrease = new DecreaseItemListener(frame,client);
		
		CloseListener close = new CloseListener(frame,client);
		
	}
}
