package server.model;

import java.util.ArrayList;

/**
 * Provides data fields and methods for suppliers that supply tools to
 * the store.
 * Stores ID-number, supplier name, the address of the supplier, the
 * name of the sales contact at the supplier, and the list of items
 * that the supplier supplies.
 * 
 * @author M. Moshirpour, Nick Park, Carter Shaul
 * @version 1.0
 * @since February 4, 2019
 */
public class Supplier {
	
	/**
	 * The Supplier's ID number
	 */
	private int supId;
	/**
	 * The name of the Supplier
	 */
	private String supName;
	/**
	 * The address of the Supplier
	 */
	private String supAddress;
	/**
	 * The name of the contact for the Supplier
	 */
	private String supContactName;
	/**
	 * The list of Items supplied by the Supplier
	 */
	private ArrayList <Item> itemList;
	
	
	/**
	 * Constructs a Supplier object with the given parameters and an empty
	 * list of items.
	 * @param id the ID number
	 * @param name the name
	 * @param address the address
	 * @param contactName the name of the contact
	 */
	public Supplier (int id, String name, String address, String contactName) {
		supId = id;
		supName = name;
		supAddress = address;
		supContactName = contactName;
		itemList = new ArrayList <Item>();
	}

	/**
	 * Gets the ID of the Supplier.
	 * @return supId
	 */
	public int getSupId() {
		return supId;
	}

	/**
	 * Sets the Supplier ID to the given ID.
	 * @param supId the ID number
	 */
	public void setSupId(int supId) {
		this.supId = supId;
	}

	/**
	 * Gets the name of the Supplier.
	 * @return supName
	 */
	public String getSupName() {
		return supName;
	}

	/**
	 * Sets the name of the Supplier to the given name.
	 * @param supName the name
	 */
	public void setSupName(String supName) {
		this.supName = supName;
	}

	/**
	 * Gets the address of the Supplier.
	 * @return supAddress
	 */
	public String getSupAddress() {
		return supAddress;
	}

	/**
	 * Sets the address of the Supplier to the given address.
	 * @param supAddress the address
	 */
	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}

	/**
	 * Gets the name of the contact for the Supplier.
	 * @return supContactName
	 */
	public String getSupContactName() {
		return supContactName;
	}

	/**
	 * Sets the name of the contact for the Supplier to the given
	 * name.
	 * @param supContactName the name of the contact
	 */
	public void setSupContactName(String supContactName) {
		this.supContactName = supContactName;
	}

	/**
	 * Gets the list of items supplied by the Supplier.
	 * @return itemList
	 */
	public ArrayList <Item> getItemList() {
		return itemList;
	}

	/**
	 * Sets the item list to the given ArrayList of items
	 * @param itemList the list of items
	 */
	public void setItemList(ArrayList <Item> itemList) {
		this.itemList = itemList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString () {
		return supName + "\t" + supId + "\t" + supContactName + "\t" + supAddress + "\t";
		
	}
	

}
