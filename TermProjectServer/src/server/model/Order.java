package server.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Provides data fields and methods for a list of order lines, with
 * and ID and date for the order line list.
 * 
 * @author M. Moshirpour, Nick Park, Carter Shaul
 * @version 1.0
 * @since February 4, 2019
 */
public class Order {
	/**
	 * The current date
	 */
	private Date today;
	/**
	 * The ID number of the Order
	 */
	private int orderId;
	/**
	 * The list of OrderLine objects
	 */
	private ArrayList <OrderLine> orderLines;


	/**
	 * Constructs an Order object with the current date and an empty
	 * list of )rderLine objects.
	 */
	public Order () {
		today = Calendar.getInstance().getTime();
		orderLines = new ArrayList <OrderLine> ();
	}
	
	/**
	 * Adds an OrderLine object to the list.
	 * @param ol the OrderLine object to be added.
	 */
	public void addOrderLine (OrderLine ol) {
		orderLines.add(ol);
	}
	
	/**
	 * Gets the list of OrderLine objects.
	 * @return orderLines
	 */
	public ArrayList <OrderLine> getOrderLines (){
		return orderLines;
	}
	
	/**
	 * Sets the list of OrderLine objects to the given list.
	 * @param lines the ArrayList of OrderLine objects
	 */
	public void setOrderLines (ArrayList <OrderLine> lines){
		orderLines = lines;
	}

	/**
	 * Gets the ID number of the Order
	 * @return orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * Sets the ID number of the Order to the given ID.
	 * @param orderId the ID number
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString (){
		String order = "Order Date: " + today.toString() + "\n\n";
		String str = "";
		for (OrderLine ol: orderLines) {
			str += ol;
			str += "------------------------\n";
		}
		if (str == "")
			str = "There are corrently no orderlines";

		order += str;
		order += "\n";
		return order;
	}

}
