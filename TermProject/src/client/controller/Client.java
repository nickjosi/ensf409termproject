package client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
	 * Constructs a Client for a Server with the specified server
	 * name and port.
	 * @param serverName name of the Server to be connected to
	 * @param portNumber port number of the Server to be connected to
	 */
	public Client(String serverName, int portNumber) {
		try {
			socket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter((socket.getOutputStream()), true);
		} catch (IOException e) {
			System.out.println("Unable to create new client.");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Gets input from the user, sends the input to the Server, 
	 * and prints out the response from the Server.
	 */
	public void communicateWithServer() {

		try {
			while(true)
			{
				String read = "";

				while (true) {

					read = socketIn.readLine();
					if(read.contains("\0")) {
						read = read.replace("\0", "");
						System.out.println(read);
						break;
					}

					if(read.equals("QUIT")) {
						return;
					}

					System.out.println(read);
				}

				read = stdIn.readLine();
				socketOut.println(read);
				socketOut.flush();
				
			}
		} catch (IOException e) {
			System.out.println("Error communicating with server.");
			System.out.println(e.getMessage());
		} finally {
			try {
				stdIn.close();
				socket.close();
			} catch(IOException e) {
				System.out.println("Unable to close IO streams.");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Client client = new Client("localhost", 9898);
		client.communicateWithServer();
	}
}
