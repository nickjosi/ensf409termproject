import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 24/03/2019
 * The following class is a listener that handles the functionality of the "Browse" button displayed on the Main Window. 
 * Loads all data within a binary search tree into a text area displayed to the user once the button is pushed.
 */
public class BrowseListener implements ActionListener {

	
	/**
	 * The Main Window object that the user interacts with. 
	 */
	private MainWindow frame;
	
	/**
	 * A binary search tree containing all data pertaining to student records. 
	 */
	private BinSearchTree studentRecords;
	
	
	/**
	 * Constructs a new object of type BrowseListener
	 * @param frame The Main Window object that the user interacts with. 
	 * @param studentRecords A Binary search tree containing all data pertaining to student records
	 */
	public BrowseListener(MainWindow frame,BinSearchTree studentRecords) {
		this.frame = frame;
		this.studentRecords = studentRecords;
	}
	
	/**
	 * @Override
	 * Loads all the data stored in a binary search to the text area on the MainWindow
	 */	
	public void actionPerformed(ActionEvent e) {
		try {
			frame.studentInfo.setText(studentRecords.toString());
		}
		catch(NullPointerException ex) {
			frame.studentInfo.setText("No file has been loaded");
		}			
	}

}
