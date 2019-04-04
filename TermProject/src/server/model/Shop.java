package server.model;

import java.util.ArrayList;

public class Shop {
	
	private Inventory inventory;
	private ArrayList <Supplier> supplierList;
	
	public Shop (Inventory inventory, ArrayList <Supplier> suppliers) {
		
		this.inventory = inventory;
		supplierList = suppliers;
		
	}
	
	public Inventory getInventory () {
		return inventory;
	}
	public void setInventory (Inventory inventory) {
		this.inventory = inventory;
	}
	public ArrayList<Supplier> getSupplierList (){
		return supplierList;
	}
	public void setSupplierList (ArrayList <Supplier> suppliers){
		supplierList = suppliers;
	}
	

	public String listAllItems() {
		return inventory.toString();
		
	}
	
	public boolean decreaseItem (String name) {
		if (inventory.manageItem(name) == null)
			return false;
		else
			return true;
	}
	
	public void removeItem(String name) {
		Item theItem = inventory.searchForItem(name);
		
		if(theItem != null) {
			inventory.removeItem(theItem);
		}
	}

	public void listAllSuppliers() {
		// TODO Auto-generated method stub
		for (Supplier s: supplierList) {
			System.out.println(s);
		}
		
	}

	public String getItem(String name) {
		// TODO Auto-generated method stub
		Item theItem = inventory.searchForItem(name);
		if (theItem == null)
		     return "\0";
		else
			 return outputItem (theItem);	
	}
	
	public String getItemSupplier(String name) {
		Item theItem = inventory.searchForItem(name);
		if(theItem == null) {
			return "\0";
		}
		else
			return outputSupplier(theItem);
	}

	public String getItem(int id) {
		// TODO Auto-generated method stub
		Item theItem = inventory.searchForItem(id);
		if (theItem == null)
		     return "\0";
		else
			return outputItem (theItem);
	}
	
	public String getItemSupplier(int id) {
		Item theItem = inventory.searchForItem(id);
		if(theItem == null) {
			return "\0";
		}
		else
			return outputSupplier(theItem);
	}
	
	private String outputItem (Item theItem){
		return theItem.toString() +"\0";
	}

	public String getItemQuantity(String name) {
		// TODO Auto-generated method stub
		int quantity = inventory.getItemQuantity(name);
		if (quantity < 0)
		    return "Item " + name + " could not be found!";
		else
			return "The quantity of Item " + name + " is: " + quantity + "\n";
	}

	private String outputSupplier(Item theItem) {
		return theItem.getTheSupplier().toString() + "\0";
	}
	
	public String printOrder() {
		// TODO Auto-generated method stub
		
		return inventory.printOrder();
	}

	

}
