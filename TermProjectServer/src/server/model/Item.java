package server.model;

/**
 * Provides data fields and methods for tools that the store carries.
 * Stores ID-number, tool name, the quantity in stock, the tool's price,
 * and a Supplier object for the supplier of the tool.
 * 
 * @author M. Moshirpour, Nick Park, Carter Shaul
 * @version 1.0
 * @since February 4, 2019
 */
public class Item {
	
	/**
	 * The Item's ID number
	 */
	private int itemId;
	/**
	 * The Item's name
	 */
	private String itemName;
	/**
	 * The Item's quantity
	 */
	private int itemQuantity;
	/**
	 * The Item's price
	 */
	private double itemPrice;
	/**
	 * A boolean that is true if an order for the Item has already been placed
	 */
	private boolean alreadyOrdered;
	/**
	 * The Item's Supplier
	 */
	private Supplier theSupplier;
	/**
	 * The quantity of the Item to order when an order is placed
	 */
	private static final int ORDERQUANTITY = 40;
	/**
	 * If the Item's quantity falls below this value, and order should be placed
	 */
	private static final int MINIMUMNUMBER = 20; 	
	
	
	/**
	 * Constructs an Item object with the given parameters.
	 * @param id the ID number of the Item
	 * @param name the name of the Item
	 * @param quantity the quantity of the Item
	 * @param price the price of the Item
	 * @param sup the Item's Supplier
	 */
	public Item (int id, String name, int quantity, double price, Supplier sup) {
		itemId = id;
		itemName = name;
		itemQuantity = quantity;
		itemPrice = price;
		theSupplier = sup;
		setAlreadyOrdered(false);
	}
	
	/**
	 * Decreases the itemQuantity if it is greater than 0.
	 * @return true if the decrease was successful, false if
	 *         if it was not successful.
	 */
	public boolean decreaseItemQuantity () {
		if (itemQuantity > 0) {
			itemQuantity--;
		    return true;	
		}
		else
			return false;
			
	}
	
	/**
	 * Places an order for the Item if the itemQuantity has been
	 * decreased below the MINIMUMNUMBER and if an order has not
	 * already been placed.
	 * @return the OrderLine if an order was placed. Otherwise,
	 *         returns null.
	 */
	public OrderLine placeOrder (){
		OrderLine ol;
		if (getItemQuantity() < MINIMUMNUMBER && alreadyOrdered == false){
			ol = new OrderLine (this, ORDERQUANTITY);
			alreadyOrdered = true;
			return ol;
		}
	    return null;
	}

	/**
	 * Gets the Item's ID.
	 * @return itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * Sets the Item's ID to the given integer.
	 * @param itemId the ID number
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	/**
	 * Gets the Item's name.
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Sets the Item's name to the given name.
	 * @param itemName the name
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * Gets the Item's quantity.
	 * @return itemQuantity
	 */
	public int getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * Sets the Item's quantity to the given value.
	 * @param itemQuantity the quantity
	 */
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	/**
	 * Gets the Item's price.
	 * @return itemPrice
	 */
	public double getItemPrice() {
		return itemPrice;
	}

	/**
	 * Sets the Item's price to the given value.
	 * @param itemPrice the price
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	/**
	 * Gets the Item's Supplier.
	 * @return the Supplier object
	 */
	public Supplier getTheSupplier () {
		return theSupplier;
	}
	
	/**
	 * Sets the Item's Supplier to the given Supplier object.
	 * @param sup the Supplier object
	 */
	public void setTheSupplier (Supplier sup) {
		theSupplier = sup;
	}

	/**
	 * Returns the value of the alreadyOrder boolean.
	 * @return true if the Item has already been order, otherwise
	 *         returns false.
	 */
	public boolean isAlreadyOrdered() {
		return alreadyOrdered;
	}

	/**
	 * Sets the alreadyOrder boolean to the given value.
	 * @param alreadyOrdered the value that alreadyOrder is to be set to.
	 */
	public void setAlreadyOrdered(boolean alreadyOrdered) {
		this.alreadyOrdered = alreadyOrdered;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString () {
		return itemId +"\t" + itemName + "\t" + itemQuantity + "\t" + itemPrice + "\t";
	}

}
