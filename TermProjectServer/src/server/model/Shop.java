package server.model;

import java.util.ArrayList;

/**
 * Consists of the shop's inventory and list of suppliers.
 * This class contains the methods to manage these lists.
 * 
 * @author M. Moshirpour, Nick Park, Carter Shaul
 * @version 1.0
 * @since February 4, 2019
 */
public class Shop {
	
	/**
	 * The Shop's inventory
	 */
	private Inventory inventory;
	/**
	 * The Shop's list of suppliers
	 */
	private ArrayList <Supplier> supplierList;
	
	
	/**
	 * Constructs a Shop object with the given inventory and supplier
	 * list.
	 * @param inventory the inventory
	 * @param suppliers the list of suppliers
	 */
	public Shop (Inventory inventory, ArrayList <Supplier> suppliers) {	
		this.inventory = inventory;
		supplierList = suppliers;
	}
	
	/**
	 * Gets the inventory.
	 * @return the inventory.
	 */
	public Inventory getInventory () {
		return inventory;
	}
	/**
	 * Sets the inventory.
	 * @param inventory the new Inventory object
	 */
	public void setInventory (Inventory inventory) {
		this.inventory = inventory;
	}
	/**
	 * Gets the supplierList.
	 * @return the list of suppliers
	 */
	public ArrayList<Supplier> getSupplierList (){
		return supplierList;
	}
	/**
	 * Sets the supplierList.
	 * @param suppliers the new list of suppliers
	 */
	public void setSupplierList (ArrayList <Supplier> suppliers){
		supplierList = suppliers;
	}
	
	/**
	 * Lists all the items in the inventory.
	 * @return the list of items as a String
	 */
	public String listAllItems() {
		return inventory.toString();
	}
	
	/**
	 * Gets the information of the item with name matching the given 
	 * name.
	 * @param name name of the item to be searched for
	 * @return the Item object's information as a String, if the item
	 * 		   exists. Otherwise, returns an empty String.
	 */
	public String getItem(String name) {
		Item theItem = inventory.searchForItem(name);
		if (theItem == null)
		     return "";
		else
			 return theItem.toString();	
	}
	
	/**
	 * Gets the information of the item with ID matching the given ID.
	 * @param id the id to be searched for
	 * @return the Item object's information as a String, if the item
	 * 		   exists. Otherwise, returns an empty String.
	 */
	public String getItem(int id) {
		Item theItem = inventory.searchForItem(id);
		if (theItem == null)
		     return "";
		else
			return theItem.toString();
	}
	
	/**
	 * Gets the supplier information for the item with name matching
	 * the given name.
	 * @param name the name of the item
	 * @return the Supplier object's information as a String, if the
	 *         item exists. Otherwise, returns an empty String.
	 */
	public String getItemSupplier(String name) {
		Item theItem = inventory.searchForItem(name);
		if(theItem == null) {
			return "";
		}
		else
			return theItem.getTheSupplier().toString();
	}
	
	/**
	 * Gets the supplier information for the item with ID matching
	 * the given ID.
	 * @param id the ID of the item
	 * @return the Supplier object's information as a String, if the
	 *         item exists. Otherwise, returns an empty String.
	 */
	public String getItemSupplier(int id) {
		Item theItem = inventory.searchForItem(id);
		if(theItem == null) {
			return "";
		}
		else
			return theItem.getTheSupplier().toString();
	}
	
	/**
	 * Removes the item with ID matching the given ID from the
	 * inventory, if the item exists.
	 * @param id the ID of the item
	 */
	public void removeItem(int id) {
		Item theItem = inventory.searchForItem(id);
		
		if(theItem != null) {
			inventory.removeItem(theItem);
		}
	}
	
	/**
	 * Decreasing the item quantity (if possible), and checks if an 
	 * order needs to be placed for the item.
	 * @param id the ID of the item
	 * @return true if the decrease was successful, false if it failed.
	 */
	public boolean decreaseItem (int id) {
		if (inventory.manageItem(id) == null)
			return false;
		else
			return true;
	}

	/**
	 * Retrieves the item quantity of the item with the given name.
	 * @param name the name of the item
	 * @return the stock information as a String if the item is found, 
	 * 		   otherwise an appropriate message is returned.
	 */
	public String getItemQuantity(String name) {
		int quantity = inventory.getItemQuantity(name);
		if (quantity < 0)
		    return "Item " + name + " could not be found!";
		else
			return "The quantity of Item " + name + " is: " + quantity + "\n";
	}
	
	/**
	 * Returns a String representing the current order.
	 * @return the current order
	 */
	public String printOrder() {		
		return inventory.printOrder();
	}
	
}
