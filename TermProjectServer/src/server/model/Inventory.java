package server.model;

import java.util.ArrayList;

/**
 * Consists of the list of items carried by the shop and the current
 * order.
 * This class contains the methods to get and manipulate stock 
 * information and search for tools. 
 * 
 * @author M. Moshirpour, Nick Park, Carter Shaul
 * @version 1.0
 * @since February 4, 2019
 */
public class Inventory {
	
	/**
	 * The list of items in stock
	 */
	private ArrayList <Item> itemList;
	/**
	 * The order list for re-stocking items
	 */
	private Order order;
	
	
	/**
	 * Constructs an Inventory item with the list of items.
	 * @param itemList the list of items
	 */
	public Inventory (ArrayList <Item> itemList) {
		this.itemList = itemList;
		order = new Order ();
	}

	
	/**
	 * Gets the itemList.
	 * @return the list of items
	 */
	public ArrayList <Item> getItemList() {
		return itemList;
	}
	
	/**
	 * Sets itemList to the given ArrayList of Item objects.
	 * @param itemList the new list of items
	 */
	public void setItemList(ArrayList <Item> itemList) {
		this.itemList = itemList;
	}
	
	/**
	 * Get the item quantity of the Item object with name that matches the
	 * given name.
	 * @param name name of the item
	 * @return the quantity of the item with name matching the given name.
	 * 		   If no such item exists in the inventory, -1 is returned.
	 */
	public int getItemQuantity (String name){
		Item theItem = searchForItem (name);
		if (theItem == null)
			return -1;
		else
			return theItem.getItemQuantity();
	}
	
	/**
	 * Get the Item object with name that matches the given name.
	 * @param name name of the item to be searched for
	 * @return the Item object. If no item has a name matching the
	 * 	       given name, returns null.
	 */
	public Item searchForItem (String name) {
		for (Item i: itemList) {
			if (i.getItemName().toLowerCase().equals(name.toLowerCase()))
				return i;
		}
		return null;
	}
	
	/**
	 * Get the Item object with ID that matches the given ID.
	 * @param id ID of the item to be searched for
	 * @return the Item object. If no item has a name matching the
	 * 	       given name, returns null.
	 */
	public Item searchForItem(int id) {
		for (Item i: itemList) {
			if (i.getItemId() == id)
				return i;
		}
		return null;
	}
	
	/**
	 * Manages the item with given ID by decreasing the item
	 * quantity (if possible), and checking if an order needs to
	 * be placed for the item.
	 * @param id the ID of the item
	 * @return the Item object, if it exists in the inventory
	 */
	public Item manageItem (int id) {
		Item theItem = decreaseItem (id);
		
		if (theItem != null){
			placeOrder (theItem);
		}
		return theItem;
	}
	
	/**
	 * Decreases the item quantity if the item exists and if
	 * its current quantity is greater than 0.
	 * @param id the ID of the item
	 * @return the Item if it exists and its quantity can be
	 *  	   decreased. Otherwise, returns null.
	 */
	private Item decreaseItem (int id) {
		
		Item theItem = searchForItem (id);
		
		if (theItem == null)
			return null;
		
		if (theItem.decreaseItemQuantity() == true){
			return theItem;
		}
		return null;
		
	}
	
	/**
	 * Places an order for the item if its quantity has decreased
	 * below a benchmark value.
	 * @param theItem the item
	 */
	public void placeOrder (Item theItem){
		OrderLine ol = theItem.placeOrder();
		if (ol != null){
			order.addOrderLine(ol);
		}
	}
	
	/**
	 * Removes the given item from the inventory.
	 * @param theItem the item to be removed
	 */
	public void removeItem(Item theItem) {
		for(int i = 0; i < itemList.size(); i++) {
			if(itemList.get(i).getItemName().equals(theItem.getItemName())){
				itemList.remove(theItem);
				return;
			}
		}
	}
	
	/**
	 * Returns a String representing the current order.
	 * @return the current order
	 */
	public String printOrder() {
		return order.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString () {
		String str = "";
		for (Item i: itemList) {
			str += i.toString() + "\n";
		}
		return str;
	}

}
