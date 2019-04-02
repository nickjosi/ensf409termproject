package server.model;

import java.util.ArrayList;

public class Inventory {
	
	private ArrayList <Item> itemList;
	private Order order;
	
	
	public Inventory (ArrayList <Item> itemList) {
		this.itemList = itemList;
		order = new Order ();
	}

	public ArrayList <Item> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList <Item> itemList) {
		this.itemList = itemList;
	}
	
	public int getItemQuantity (String name){
		Item theItem = searchForItem (name);
		if (theItem == null)
			return -1;
		else
			return theItem.getItemQuantity();
	}
	
	public Item searchForItem (String name) {
		for (Item i: itemList) {
			if (i.getItemName().toLowerCase().equals(name.toLowerCase()))
				return i;
		}
		return null;
	}
	
	public Item searchForItem(int id) {
		for (Item i: itemList) {
			if (i.getItemId() == id)
				return i;
		}
		return null;
	}
	
	public Item manageItem (String name) {
		Item theItem = decreaseItem (name);
		
		if (theItem != null){
			placeOrder (theItem);
		}
		return theItem;
	}
	public void placeOrder (Item theItem){
		OrderLine ol = theItem.placeOrder();
		if (ol != null){
			order.addOrderLine(ol);
		}
	}
	private Item decreaseItem (String name) {
		
		Item theItem = searchForItem (name);
		
		if (theItem == null)
			return null;
		
		if (theItem.decreaseItemQuantity() == true){
			return theItem;
		}
		return null;
		
	}

	public String printOrder() {
		return order.toString();
	}
	
	public String toString () {
		String str = "";
		for (Item i: itemList) {
			str += i;
		}
		return str;
	}

}
