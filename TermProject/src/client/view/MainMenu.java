package client.view;

import java.awt.CardLayout;

import java.awt.Container;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import client.controller.*;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 29/03/2019
 * 
 * This class contains the member variables necessary to construct a user interface representing a 
 * retail store's inventory system.
 * All of the buttons and interactive components have listeners assigned to them so that a user may interact
 * with the display as desired. 
 */
public class MainMenu extends JFrame implements ViewConstants {

	/**
	 * Multiple JButton objects which the user can interact with. 
	 */
	private JButton viewInventory,addItem,removeItem,backToLogin,decreaseItem;
	
	/**
	 * Multiple JPanel objects which act as container for different displays to the user. 
	 */
	private JPanel north,south,center;
	
	/**
	 * A JTable object which holds all of the data displayed to the user. 
	 */
	public JTable tableData;
	
	/**
	 * A JScrollPane object which allows the user to scroll through the data being displayed.  
	 */
	private JScrollPane scroll;
	
	/**
	 * Two JLabel objects which display the title of the frame to the user. 
	 */
	private JLabel title;
	
	/**
	 * A SearchBar object which the user can enter item info into and receive search results which are displayed to the JFrame. 
	 */
	public SearchBar searchBar;
	
	/**
	 * A Container object which provides a container handle on the entire JFrame. 
	 */
	private Container c;
		
	/**
	 * Constructs a new object of type MainMenu with all the necessary data fields
	 */
	public MainMenu() {
		super("Inventory System");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Set L&F to current OS preferences
			}
		catch(Exception e) {
				e.printStackTrace();
		}	
		
		title = new JLabel(TITLE,JLabel.CENTER);//Initialize member variables
		tableData = new JTable();
		scroll = new JScrollPane(tableData,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		searchBar = new SearchBar();
		north = new JPanel();
		south = new JPanel();
		center = new JPanel(new CardLayout());
		
		viewInventory = new JButton("View Inventory"); //Initialize buttons
		addItem = new JButton("Add Item");
		removeItem = new JButton("Remove Item(s)");
		backToLogin = new JButton("Back to Login");
		decreaseItem = new JButton("Decrease Quantity of Item");
		
		addItem.setVisible(false); //Set initial visibility of certain components
		removeItem.setVisible(false);
		backToLogin.setVisible(false);
		searchBar.setVisible(false);
		decreaseItem.setVisible(false);

		title.setIcon(new ImageIcon("HammerAndShovel.png")); //Set location of text and tool shop logo
		title.setHorizontalTextPosition(JLabel.CENTER);
		title.setVerticalTextPosition(JLabel.CENTER);
	
		south.setLayout(new BoxLayout(south,BoxLayout.LINE_AXIS)); //Building and setting preferences for south panel which contains all the button objects
		south.setPreferredSize(PANEL_SIZE);
		south.add(Box.createRigidArea(EDGE_SPACING));
		south.add(addItem);
		south.add(Box.createRigidArea(BUTTON_SPACING));
		south.add(removeItem);
		south.add(Box.createRigidArea(BUTTON_SPACING));
		south.add(decreaseItem);
		south.add(Box.createHorizontalGlue());
		south.add(viewInventory);		
		south.add(Box.createRigidArea(EDGE_SPACING));
		
		north.setPreferredSize(PANEL_SIZE); //Building and setting preferences for north panel which contains the searchBar object
		north.setLayout(new BoxLayout(north,BoxLayout.LINE_AXIS));
		north.add(Box.createRigidArea(EDGE_SPACING));
		north.add(backToLogin);
		north.add(Box.createHorizontalGlue());
		north.add(searchBar);
		north.add(Box.createRigidArea(EDGE_SPACING));

		center.add(title,"Title");
		center.add(scroll,"Table");

		c = getContentPane(); //Getting handle of content pane and adding all panels
		c.add("North",north);
		c.add("South",south);
		c.add("Center", center);
				
		title.setFont(TITLE_FONT); //Setting the font of the title displayed in the frame
		
		setMinimumSize(MINIMUM_FRAME_SIZE); //Setting JFrame preferences
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Assigns an action listener to the searchBar data member
	 * @param listener The ActionListener that is being assigned to the data member
	 */
	public void setSearchBarListener(SearchBarListener listener) { 
		searchBar.addActionListener(listener);
	}
	
	/**
	 * Assigns an action listener to the addItem data member
	 * @param listener The ActionListener that is being assigned to the data member
	 */
	public void setAddItemListener(AddItemListener listener) {
		addItem.addActionListener(listener);
	}
	
	/**
	 * Assigns a window listener to the parent JFrame
	 * @param listener The WindowListener that is being assigned
	 */
	public void setCloseWindowListener(WindowAdapter listener) {
		super.addWindowListener(listener);
	}
	
	/**
	 * Assigns an action listener to the viewInventory data member
	 * @param listener The ActionListener that is being assigned to the data member
	 */
	public void setViewInventoryListener(ViewInventoryListener listener) {
		viewInventory.addActionListener(listener);
	}
	
	/**
	 * Assigns an action listener to the removeItem data member
	 * @param listener The ActionListener that is being assigned to the data member
	 */
	public void setRemoveItemListener(RemoveItemListener listener) {
		removeItem.addActionListener(listener);
	}
	
	/**
	 * Assigns an action listener to the backToLogin data member
	 * @param listener The ActionListener that is being assigned to the data member. 
	 */
	public void setBackToLoginListener(BackToLoginListener listener) {
		backToLogin.addActionListener(listener);
	}
	
	public void setDecreaseItemListener(DecreaseItemListener listener) {
		decreaseItem.addActionListener(listener);
	}
	
	/**
	 * Toggles the visibility of the backToLogin button. 
	 */
	public void setBackButtonVisibility(boolean b) {
		backToLogin.setVisible(b);
	}
	
	/**
	 * Toggles the visibility of the searchBar. 
	 */
	public void setSearchBarVisibility(boolean b) {
		searchBar.setVisible(b);
	}
	
	/**
	 * Toggles the visibility of the removeItem button. 
	 */
	public void setRemoveButtonVisibility(boolean b) {
		removeItem.setVisible(b);
	}
	
	/**
	 * Toggles the visibility of the addItem button
     */
	public void setAddButtonVisibility(boolean b) {
		addItem.setVisible(b);
	}
	
	/**
	 * Toggles the visibility of the decreaseItem button
	 */
	public void setDecreaseButtonVisibiity(boolean b) {
		decreaseItem.setVisible(b);
	}
	
	/**
	 * Displays the table of items to the user 
	 */
	public void showItemList() {
		CardLayout layout = (CardLayout) center.getLayout();
		layout.show(center,"Table");
	}
	
	/**
	 * Displays the login screen to the user
	 */
	public void showLoginScreen() {
		CardLayout layout = (CardLayout) center.getLayout();
		layout.show(center, "Title");
	}
	
	/**
	 * Updates the displayed JTable with values pulled from the database. 
	 * @param data
	 */
	public void updateTable(ArrayList<String> data) {
		DefaultTableModel table = (DefaultTableModel) tableData.getModel();
		
		for(int i = 0; i < data.size() && !data.get(i).equals(""); i ++) {
			String[] temp = data.get(i).split("\t");
			table.addRow(new Object[] {temp[0],temp[1],temp[2],temp[3]});
		}
	}
}
