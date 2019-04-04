package server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Accepts and connects to new clients, creates a DatabaseController 
 * object for each client, and creates a new thread for the interaction
 * with that client to run on.
 * 
 * @author Nick Park and Carter Shaul
 * @version 1.0
 * @since March 29, 2019
 */
public class Server {
	/**
	 * The ServerSocket object which waits for requests to come in over
	 * the network
	 */
	private ServerSocket serverSocket;
	/**
	 * The ExecutorService object that manages the multiple threads and
	 * allows for multiple clients to use the system
	 */
	private ExecutorService pool;

	
	/**
	 * Constructs and Server with the specified port number.
	 * @param portNumber the port number
	 */
	public Server(int portNumber) { // throws IOException {
		try {
			serverSocket = new ServerSocket(portNumber);
			pool = Executors.newFixedThreadPool(6);
			
		} catch (IOException e) {
			System.out.println("Unable to create new server.");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Waits for a request from a new client, creates a new
	 * DatabaseController and initiates the communication.
	 */
	public void communicateWithClient() {
		try {
			while(true) {
				
				DatabaseController db = new DatabaseController(serverSocket.accept());
				System.out.println("A new client has connected.");
				//db.menu();
				pool.execute(db);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			pool.shutdown();
		}
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server(9898);
		System.out.println("Server is now running.");
		server.communicateWithClient();
	}

}