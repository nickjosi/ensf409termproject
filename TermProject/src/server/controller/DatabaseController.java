package server.controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import server.model.*;

/**
 * Receives input from the client, modifies the shop accordingly,
 * and sends back the appropriate responses in order for the view to
 * be updated and display accurate data to the client. 
 * 
 * @author Nick Park and Carter Shaul
 * @version 1.0
 * @since March 29, 2019
 */
public class DatabaseController implements Runnable {
	/**
	 * The shop which stores the inventory and list of suppliers
	 */
	private Shop shop;
	/**
	 * The endpoint for the communication with the client.
	 */
	private Socket socket;
	/**
	 * Reads the socket input stream to receive data from the client.
	 */
	private BufferedReader socketIn;
	/**
	 * Writes to the socket output stream to communicate with the client.
	 */
	private PrintWriter socketOut;
	
	/**
	 * Constructs a DatabaseController object for the given socket, and
	 * loads the inventory and list of suppliers from the from text 
	 * files.
	 * @param s the Socket the DatabaseController object will use to
	 * 		    communicate with the client.
	 */
	DatabaseController(Socket s) {
		socket = s;
		try {
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		readSuppliers(suppliers);
		Inventory inventory = new Inventory(readItems(suppliers));
		shop = new Shop(inventory, suppliers);
	}

	/**
	 * Sends the specified String to the client and flushes the PrintWriter.
	 * @param toSend the String to be sent
	 */
	public void sendString(String toSend) {
		socketOut.println(toSend);
		socketOut.flush();
	}

	/**
	 * Reads the input file and creates a list of items which represents
	 * the shop's inventory.
	 * @param suppliers the list of suppliers for the items
	 * @return the ArrayList of Item objects created from the text file
	 */
	private ArrayList<Item> readItems(ArrayList<Supplier> suppliers) {

		ArrayList<Item> items = new ArrayList<Item>();

		try {
			FileReader fr = new FileReader("items.txt");
			BufferedReader br = new BufferedReader(fr);

			String line = "";
			while ((line = br.readLine()) != null) {
				String[] temp = line.split(";");
				int supplierId = Integer.parseInt(temp[4]);

				Supplier theSupplier = findSupplier(supplierId, suppliers);
				if (theSupplier != null) {
					Item myItem = new Item(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]),
							Double.parseDouble(temp[3]), theSupplier);
					items.add(myItem);
					theSupplier.getItemList().add(myItem);
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return items;
	}

	/**
	 * Finds the supplier which matches the supplierID
	 * @param supplierId the ID to be searched for
	 * @return theSupplier the Supplier with the given ID
	 */
	private Supplier findSupplier(int supplierId, ArrayList<Supplier> suppliers) {
		Supplier theSupplier = null;
		for (Supplier s : suppliers) {
			if (s.getSupId() == supplierId) {
				theSupplier = s;
				break;
			}

		}
		return theSupplier;
	}

	/**
	 * Reads the input file and creates a list of suppliers.
	 * @param suppliers the ArrayList of Supplier objects to be filled.
	 */
	private void readSuppliers(ArrayList<Supplier> suppliers) {

		try {
			FileReader fr = new FileReader("suppliers.txt");
			BufferedReader br = new BufferedReader(fr);

			String line = "";
			while ((line = br.readLine()) != null) {
				String[] temp = line.split(";");
				suppliers.add(new Supplier(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3]));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	
	/**
	 * Reads client input choices and performs the selected request. 
	 */
	public void menu() {

		while (true) {

			int choice = -1;
			String[] data = null;

			try {
				String command = socketIn.readLine();
				if(command != null) {
					data = command.split("\t");
					choice = Integer.parseInt(data[0]);
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
			switch (choice) {

				case 1:
					listAllTools();
					break;
				case 2:
					searchForItemByName(data[1]);
					//getItemSupplier(data[1]);
					break;
				case 3:
					searchForItemById(Integer.parseInt(data[1]));
					//getItemSupplier(Integer.parseInt(data[1]));
					break;
				case 4:
					removeItem(data[1]);
					break;
				case 5:
					decreaseItem(data[1]);
					break;
				case 6: 
					printOrder();
					break;
				case 7:
					sendString("\nGood Bye!");
					sendString("QUIT");
					return;
				default:
					sendString("\nInvalid selection Please try again!");
					break;
			}						
		}
				
	}

	private void listAllTools() {
		sendString(shop.listAllItems());
	}
	
	private void searchForItemByName(String name) {
		sendString(shop.getItem(name));
		
	}
	
	private void searchForItemById(int id) {
		sendString(shop.getItem(id));
	}
	
	private void getItemSupplier(String name) {
		sendString(shop.getItemSupplier(name));
	}
	
	private void getItemSupplier(int id) {
		sendString(shop.getItemSupplier(id));
	}
	
	private void removeItem(String name) {
		shop.removeItem(name);
	}
	
	private void decreaseItem(String name) {
		boolean result = shop.decreaseItem(name);
		if(result) {
			sendString("true\0");
		}
		else {
			sendString("false\0");
		}	
	}

	private void printOrder() {
		sendString(shop.printOrder());
	}

	
	private String getItemName() {
		sendString("Please enter the name of the item: \0");

		String line = "";
		try {
			line = socketIn.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;

	}

	private int getItemId() {
		sendString("Please enter the ID number of the item: \0");
		try {
			return Integer.parseInt(socketIn.readLine());
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void run() {
		menu();
		System.out.println("A client has quit the program.");
	}

}
