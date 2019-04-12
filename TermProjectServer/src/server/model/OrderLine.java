package server.model;

/**
 * Provides data fields and methods for an order line
 * for a tool.
 * 
 * @author M. Moshirpour, Nick Park, Carter Shaul
 * @version 1.0
 * @since February 4, 2019
 */
public class OrderLine {
	
	/**
	 * The item that is being ordered
	 */
	private Item theItem;
	/**
	 * The quantity that is being ordered
	 */
	private int orderQuantity;
	
	
	/***
	 * Constructs an OrderLine object with the given parameters.
	 * @param item the Item
	 * @param quantity the quantity to be ordered
	 */
	public OrderLine (Item item, int quantity) {
		theItem = item;
		setOrderQuantity(quantity); 
		
	}

	/**
	 * Gets the Item object.
	 * @return theItem
	 */
	public Item getTheItem() {
		return theItem;
	}

	/**
	 * Sets the Item to be ordered.
	 * @param theItem the item to be ordered
	 */
	public void setTheItem(Item theItem) {
		this.theItem = theItem;
	}

	/**
	 * Gets the quantity to be ordered.
	 * @return orderQuantity
	 */
	public int getOrderQuantity() {
		return orderQuantity;
	}

	/**
	 * Sets the quantity to be ordered.
	 * @param orderQuantity the quantity to be ordered
	 */
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString (){
		return  "Item Name: " + theItem.getItemName() +
				", Item ID: " + theItem.getItemId()+ "\n" + 
				"Order Quantity: " + orderQuantity + "\n";
	}

}
