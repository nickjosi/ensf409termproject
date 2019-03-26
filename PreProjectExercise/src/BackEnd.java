//****CONTROLLER***//

/** 
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 24/03/2019
 * The following class contains a Binary Search Tree and allows the user to interact/modify the tree through
 * interaction with the buttons displayed on the Main Window object.
 * Manages all the listener objects associated with all the buttons.
 */
public class BackEnd{
	
	
	private MainWindow mainView; //View
	/**
	 * A binary search tree which contains all the data the user wishes to add to it
	 */
	private BinSearchTree studentRecords; //Model
	
	/**
	 * Listener object associated with the Browse button object in the Main Window. 
	 */
	BrowseListener browseListen;
	
	/**
	 * Listener object associated with the Create Tree from file button object in the Main Window. 
	 */
	CreateListener createListen;
	
	/**
	 * Listener object associated with the Find button object in the Main Window. 
	 */
	FindListener findListen;
	
	/**
	 * Listener object associated with the Insert button object in the Main Window. 
	 */
	InsertListener insertListen;
	
	
	/**
	 * The following constructor creates a new Back End object. 
	 * @param frame The Main Window object that the user interacts with. 
	 */
	public BackEnd(MainWindow frame, BinSearchTree data) {
		
		mainView = frame;
		studentRecords = data;
		
		browseListen = new BrowseListener(mainView,studentRecords); //Initialize new listeners
		createListen = new CreateListener(studentRecords);
		findListen = new FindListener(studentRecords);
		insertListen = new InsertListener(mainView,studentRecords);
		
		frame.setBrowseListener(browseListen);
		frame.setCreateListener(createListen);
		frame.setFindListener(findListen);
		frame.setInsertListener(insertListen);
	}

}