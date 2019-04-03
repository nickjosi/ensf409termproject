package server.controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import server.model.*;
import java.sql.*;

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
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	/**
	 * Constructs a DatabaseController object for the given socket, and
	 * loads the inventory and list of suppliers from the from text 
	 * files.
	 * @param s the Socket the DatabaseController object will use to
	 * 		    communicate with the client.
	 */
	DatabaseController(Socket s) {
		socket = s;
		// Setup socketIn and socketOut to communicate with client
		try {
			socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socketOut = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		} finally {
			sendString("Connected to the server. Waiting...");
		}
		
		// Initialize connection to database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo"
					+ "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "ensf409");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 * Reads the database and creates a list of items which represents
	 * the shop's inventory.
	 * @param suppliers the list of suppliers for the items
	 * @return the ArrayList of Item objects created from the text file
	 */
	private ArrayList<Item> readItems(ArrayList<Supplier> suppliers) {

		ArrayList<Item> items = new ArrayList<Item>();

		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM inventory";
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Supplier supplier = findSupplier(rs.getInt("supplierId"), suppliers);
				if(supplier != null) {
					Item item = new Item(rs.getInt("itemId"), rs.getString("itemName"), rs.getInt("itemQuantity"), 
							rs.getDouble("itemPrice"), supplier);
					items.add(item);
					supplier.getItemList().add(item);
				}
			}			
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
	 * Reads the database and creates a list of suppliers.
	 * @param suppliers the ArrayList of Supplier objects to be filled.
	 */
	private void readSuppliers(ArrayList<Supplier> suppliers) {

		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM supplierList";
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				System.out.println(rs.getString("supId") + " " + rs.getString("supName")
				+ " " + rs.getString("supAddress") + " " + rs.getString("supContactName"));
				suppliers.add(new Supplier(Integer.parseInt(rs.getString("supId")), rs.getString("supName"), 
						rs.getString("supAddress"), rs.getString("supContactName")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void printMenuChoices() {
		sendString("Please choose from one of the following options: ");
		sendString("1. List all tools in the inventory.");
		sendString("2. Search for tool by tool name.");
		sendString("3. Search for tool by tool id.");
		sendString("4. Check item quantity.");
		sendString("5. Decrease item quantity.");
		sendString("6. Print today's order.");
		sendString("7. Quit.");
		sendString("");
		sendString("Please enter your selection: \0");
	}

	public void menu() {

		while (true) {

			printMenuChoices();

			int choice = -1;
			try {
				choice = Integer.parseInt(socketIn.readLine());
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			switch (choice) {

			case 1:
				listAllTools();
				break;
			case 2:
				searchForItemByName();
				break;
			case 3:
				searchForItemById();
				break;
			case 4:
				checkItemQuantity();
				break;
			case 5:
				decreaseItem();
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
	
	private void searchForItemByName() {
		String name = getItemName();
		sendString(shop.getItem(name));
	}
	
	private void searchForItemById() {
		int id = getItemId();
		sendString(shop.getItem(id));
	}
	
	private void checkItemQuantity() {
		String name = getItemName();
		sendString(shop.getItemQuantity(name));
	}
	
	private void decreaseItem() {
		String name = getItemName();
		sendString(shop.decreaseItem(name));
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
