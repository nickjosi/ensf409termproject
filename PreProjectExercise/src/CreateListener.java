import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 24/03/2019
 * The following class is a listener which handles the functionality of the "Create tree from file" button displayed on the Main Window. 
 * Loads all necessary data from a user input text file into a binary search tree. 
 */
public class CreateListener implements ActionListener{

	/**
	 * A binary search tree containing all data pertaining to student records.
	 */
	private BinSearchTree studentRecords;
	
	/**
	 * Constructs a new object of type CreateListener. 
	 * @param studentRecords A binary search tree containing all data pertaining to student records.
	 */
	public CreateListener(BinSearchTree studentRecords) {
		this.studentRecords = studentRecords;
	}
	
	
	/**
	 * @Override
	 * Loads all data from a text file into a binary search tree after the button is pressed. 
	 */
	public void actionPerformed(ActionEvent e) {
		String fileName = JOptionPane.showInputDialog("Enter the file name: ");
		createTreeFromFile(fileName);		
	}
	
	
	/**
	 * Loads all necessary data from a given text file into a binary search tree. 
	 * @param fileName The user input file name.
	 */
	public void createTreeFromFile(String fileName) { 
		try {
			Scanner scan = new Scanner(new File(fileName));
		
			scan.useDelimiter("[ |\\n]+");
		
			String id,faculty,major,year;
			while(scan.hasNext()) {
				id = scan.next();
				faculty = scan.next();
				major = scan.next();
				year = scan.next();
				studentRecords.insert(id, faculty, major, year);
			}	
			scan.close();
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, "File could not be located", "Error Message",JOptionPane.ERROR_MESSAGE);
		}
	}

	
}
