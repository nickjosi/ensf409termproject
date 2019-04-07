package server.controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import server.model.*;
import java.sql.*;

/**
 * Receives input from the client, modifies the shop accordingly,
 * and sends back the appropriate responses in order for the view to
 * be updated and display accurate data to the client. MySQL database
 * functionality has been integrated so that the shop and database
 * are both kept up to date with each other.
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
	 * The Connection object for connecting to the MySQL database.
	 */
	private Connection conn;
	/**
	 * The Statement object for communication with the MySQL database.
	 */
	private Statement stmt;
	/**
	 * The ResultSet object for communication with the MySQL database.
	 */
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

		// Get data from database
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		readSuppliers(suppliers);
		Inventory inventory = new Inventory(readItems(suppliers));
		shop = new Shop(inventory, suppliers);
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
				suppliers.add(new Supplier(Integer.parseInt(rs.getString("supId")), rs.getString("supName"), 
						rs.getString("supAddress"), rs.getString("supContactName")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

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
				break;
			case 3:
				searchForItemById(Integer.parseInt(data[1]));
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
	
	/**
	 * Updates inventory from database, and send it to the client.
	 */
	private void listAllTools() {
		Inventory inventory = new Inventory(readItems(shop.getSupplierList()));
		shop.setInventory(inventory);
		sendString(shop.listAllItems() + "\0");
	}

	/**
	 * Sends item and supplier information to client, if item found.
	 * @param name the name of the item
	 */
	private void searchForItemByName(String name) {
		sendString(shop.getItem(name) + shop.getItemSupplier(name) + "\0");
	}

	/**
	 * Sends item and supplier information to client, if item found.
	 * @param id the id of the item
	 */
	private void searchForItemById(int id) {
		sendString(shop.getItem(id) + shop.getItemSupplier(id) + "\0");
	}

	/**
	 * TODO Complete this to work with database.
	 * Removes item from the inventory and database.
	 * @param name the name of the item
	 */
	private void removeItem(String stringID) {
		try {
			
			int id = Integer.parseInt(stringID);

			String delete = "DELETE FROM inventory WHERE itemId=?";
			PreparedStatement pStat = conn.prepareStatement(delete);
			pStat.setInt(1, id);
			pStat.executeUpdate();
			
			shop.removeItem(id);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets updated quantity for item from database, and decreases it if
	 * the quantity is greater than 0. If the quantity is decreased, the
	 * database is updated and the client is sent the new quantity.
	 * @param stringID the ID of the item as a String
	 */
	private void decreaseItem(String stringID) {
		try {
			
			int id = Integer.parseInt(stringID);

			String query = "SELECT * FROM inventory WHERE itemId=?";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			rs = pStat.executeQuery();
			
			//Get updated quantity from database
			int dbQuantity = 0;
			if(rs.next()) {
				dbQuantity = rs.getInt("ItemQuantity");
				shop.getInventory().searchForItem(id).setItemQuantity(dbQuantity);
			} else {
				sendString("false\0");
				return;
			}
			
			//Decrease item quantity
			boolean result = shop.decreaseItem(id);
			
			//If successful, update database value and send updated quantity to client.
			//Else, send a quantity of 0 to client.
			if(result) {
				String update = "UPDATE inventory SET itemQuantity=? WHERE itemId=?";
				pStat = conn.prepareStatement(update);
				pStat.setInt(1, dbQuantity-1);
				pStat.setInt(2, id);
				pStat.executeUpdate();
				pStat.close();
				
				sendString("true");
				sendString(Integer.toString(dbQuantity-1) + "\0");
			}
			else {
				sendString("false");
				sendString("0\0");
			}
			
		} catch(SQLException e)  {
			System.out.println("Problem decreasing item quantity");
			e.printStackTrace();
		}
	}
	

	/**
	 * Sends the order information to the client.
	 */
	private void printOrder() {
		sendString(shop.printOrder());
	}

	/**
	 * Closes the Connection, Statement, and Result Set objects.
	 */
	public void close() {
		try {
			conn.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Sends the specified String to the client and flushes the PrintWriter.
	 * @param toSend the String to be sent
	 */
	public void sendString(String toSend) {
		socketOut.println(toSend);
		socketOut.flush();
	}

	@Override
	public void run() {
		menu();
		System.out.println("A client has quit the program.");
		//close();
	}

}
