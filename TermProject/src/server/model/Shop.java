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
	public String decreaseItem (String name) {
		if (inventory.manageItem(name) == null)
			return "Couldn't not decrease item quantity!\n";
		else
			return "Item quantity was decreased!\n";
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
		     return "Item " + name + " could not be found!";
		else
			 return outputItem (theItem);
			
	}

	public String getItem(int id) {
		// TODO Auto-generated method stub
		Item theItem = inventory.searchForItem(id);
		if (theItem == null)
		     return "Item number " + id + " could not be found!";
		else
			return outputItem (theItem);
			 
		
	}
	
	private String outputItem (Item theItem){
		return "The item information is as follows: \n" + theItem;
	}

	public String getItemQuantity(String name) {
		// TODO Auto-generated method stub
		int quantity = inventory.getItemQuantity(name);
		if (quantity < 0)
		    return "Item " + name + " could not be found!";
		else
			return "The quantity of Item " + name + " is: " + quantity + "\n";
	}

	public String printOrder() {
		// TODO Auto-generated method stub
		
		return inventory.printOrder();
	}

	

}
