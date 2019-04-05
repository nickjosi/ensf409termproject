package client.view;

import client.controller.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

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
	private JButton viewInventory,removeItem,backToLogin,decreaseItem,login;
	
	/**
	 * Multiple JPanel objects which act as container for different displays to the user. 
	 */
	private JPanel north,south,center,bar,homeScreen,titlePanel,loginPanel;
	
	/**
	 * A JTable object which holds all of the data displayed to the user. 
	 */
	private JTable tableData;
	
	/**
	 * A JScrollPane object which allows the user to scroll through the data being displayed.  
	 */
	private JScrollPane scroll;
	
	/**
	 * Two JLabel objects which display the title of the frame to the user. 
	 */
	private JLabel title,searchLabel,usernameLabel,passwordLabel;
	
	/**
	 * A SearchBar object which the user can enter item info into and receive search results which are displayed to the JFrame. 
	 */
	public SearchBar searchBar;
	
	/**
	 * A Container object which provides a container handle on the entire JFrame. 
	 */
	private Container c;
	
	/**
	 * TextField objects which take user input data fields and grant access to the DataBase. 
	 */
	private JTextField username,password;
	
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
		
		//The following block of code was taken from http://blog.marcnuri.com/jtable-alternate-row-background/
		tableData = new JTable() {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component returnComp = super.prepareRenderer(renderer, row, col);
				Color altColor = new Color(204,229,255);
				Color defaultColor = Color.WHITE;
				if(!returnComp.getBackground().equals(getSelectionBackground())){
					Color bg = (row % 2 == 0 ? altColor : defaultColor);
					returnComp.setBackground(bg);
					bg = null;
				}
				return returnComp;
			}
		};
		
		title = new JLabel(TITLE,JLabel.CENTER);//Initialize member variables
		searchLabel = new JLabel("Item Search");
		passwordLabel = new JLabel("Password");
		usernameLabel = new JLabel("Username");
		scroll = new JScrollPane(tableData,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		searchBar = new SearchBar();
		north = new JPanel();
		south = new JPanel();
		center = new JPanel(new CardLayout());
		bar = new JPanel();
		homeScreen = new JPanel();
		titlePanel = new JPanel();
		loginPanel = new JPanel();
		username = new JTextField();
		password = new JTextField();
		usernameLabel = new JLabel("Username");
		passwordLabel = new JLabel("Password");

		viewInventory = new JButton("View Inventory"); //Initialize buttons
		removeItem = new JButton("Remove Item(s)");
		backToLogin = new JButton("Back to Login");
		decreaseItem = new JButton("Decrease Quantity of Item");
		login = new JButton("Login");
		
		south.setVisible(false); //Set initial visibility of certain components
		north.setVisible(false);

		title.setIcon(new ImageIcon("HammerAndShovel.png")); //Set location of text and tool shop logo
		title.setHorizontalTextPosition(JLabel.CENTER);
		title.setVerticalTextPosition(JLabel.CENTER);
		
		titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.LINE_AXIS));
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(title);
		titlePanel.add(Box.createHorizontalGlue());
		
		username.setPreferredSize(TEXT_FIELD_SIZE);
		username.setMaximumSize(TEXT_FIELD_SIZE);
		password.setPreferredSize(TEXT_FIELD_SIZE);
		password.setMaximumSize(TEXT_FIELD_SIZE);
		
		loginPanel.setLayout(new BoxLayout(loginPanel,BoxLayout.LINE_AXIS));
		loginPanel.add(Box.createHorizontalGlue());
		loginPanel.add(usernameLabel);
		loginPanel.add(Box.createRigidArea(BUTTON_SPACING));
		loginPanel.add(username);
		loginPanel.add(Box.createRigidArea(BUTTON_SPACING));
		loginPanel.add(passwordLabel);
		loginPanel.add(Box.createRigidArea(BUTTON_SPACING));
		loginPanel.add(Box.createRigidArea(BUTTON_SPACING));
		loginPanel.add(password);
		loginPanel.add(Box.createHorizontalGlue());
		
		homeScreen.setLayout(new BoxLayout(homeScreen,BoxLayout.Y_AXIS));
		homeScreen.add(Box.createVerticalGlue());
		homeScreen.add(titlePanel);
		homeScreen.add(Box.createVerticalStrut(VERTICAL_SPACE));
		homeScreen.add(loginPanel);
		homeScreen.add(Box.createVerticalStrut(VERTICAL_SPACE));
		homeScreen.add(login,Box.CENTER_ALIGNMENT);
		homeScreen.add(Box.createVerticalGlue());
	
		bar.add(searchLabel); //Compose search bar panel
		bar.add(searchBar);
		bar.setMaximumSize(new Dimension(250,40));
				
		south.setLayout(new BoxLayout(south,BoxLayout.LINE_AXIS)); //Building and setting preferences for south panel which contains all the button objects
		south.setPreferredSize(PANEL_SIZE);
		south.add(Box.createRigidArea(EDGE_SPACING));
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
		north.add(bar);
		north.add(Box.createRigidArea(EDGE_SPACING));
		
		center.add(homeScreen,"Home"); //
		center.add(scroll,"Table");

		c = getContentPane(); //Getting handle of content pane and adding all panels
		c.add("North",north);
		c.add("South",south);
		c.add("Center", center);
				
		title.setFont(TITLE_FONT); //Setting the font of the title displayed in the frame
		searchLabel.setFont(OTHER_FONT);
		passwordLabel.setFont(OTHER_FONT);
		usernameLabel.setFont(OTHER_FONT);
		
		setPreferredSize(PREFERRED_FRAME_SIZE);
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
	
	/**
	 * Assigns an action listener to the decreaseItem data member
	 * @param listener The ActionListener that is being assigned to the data member. 
	 */
	public void setDecreaseItemListener(DecreaseItemListener listener) {
		decreaseItem.addActionListener(listener);
	}
	
	/**
	 * Assigns an action listener to the login button. 
	 * @param listener The ActionListener that is being assigned to the data member. 
	 */
	public void setLoginListener(LoginListener listener) {
		login.addActionListener(listener);
	}
	
	/**
	 * Provides the handle on the removeItem button. 
	 */
	public JButton getRemoveButton() {
		return removeItem;
	}
	
	/**
	 * Toggles the visibility of all components in the north panel of the frame
	 * @param b
	 */
	public void setNorthVisibility(boolean b) {
		north.setVisible(b);
	}
	
	/**
	 * Toggles the visibility of all components in the south panel of the frame
	 * @param b
	 */
	public void setSouthVisibility(boolean b) {
		south.setVisible(b);
	}
	
	/**
	 * Displays the table of items to the user 
	 */
	public void showItemList() {
		CardLayout layout = (CardLayout) center.getLayout();
		layout.show(center,"Table");
	}
	
	/**
	 * Provides a handle on the JTable data member. 
	 */
	public JTable getTable() {
		return tableData;
	}
	
	public JButton getInventoryButton() {
		return viewInventory;
	}
	
	/**
	 * Displays the login screen to the user
	 */
	public void showHomeScreen() {
		CardLayout layout = (CardLayout) center.getLayout();
		layout.show(center, "Home");
	}
	
	public void showTableScreen() {
		CardLayout layout = (CardLayout) center.getLayout();
		layout.show(center, "Table");
	}
	
	/**
	 * Updates the displayed JTable with values pulled from the database. 
	 * @param data The list of string representations of items being inserted into the table. 
	 */
	public void updateTable(ArrayList<String> data) {
		DefaultTableModel table = (DefaultTableModel) tableData.getModel();
		
		for(int i = 0; i < data.size() && !data.get(i).equals(""); i ++) {
			String[] temp = data.get(i).split("\t");
			table.addRow(new Object[] {temp[0],temp[1],temp[2],temp[3]});
		}
	}
	
	/**
	 * Adds a new row of user input data to the inventory
	 * @param data The string representation of the item being added to the table. 
	 */
	public void addToTable(String data) {
		DefaultTableModel table = (DefaultTableModel) tableData.getModel();
		String [] temp = data.split(" ");
		table.addRow(new Object[] {temp[0],temp[1],temp[2],temp[3]});
	}
}
