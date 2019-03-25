import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 24/03/2019
 * The following class is a listener that handles the functionality of the "Find" button displayed on the Main Window. 
 * Performs a binary search for a node containing the Student ID enterd by the user 
 */
public class FindListener implements ActionListener {

	/**
	 * A binary search tree containing all data pertaining to student records. 
	 */
	private BinSearchTree studentRecords;
	
	
	/**
	 * Constructs a new object of type Find Listener
	 * @param studentRecords A Binary search tree containing all data pertaining to student records
	 */
	public FindListener(BinSearchTree studentRecords) {
		this.studentRecords = studentRecords;
	}


	/**
	 * @Override
	 * Performs a binary search on the student records search tree for a node containing the Student ID input by the user.
	 */	
	public void actionPerformed(ActionEvent e) {
		String id = JOptionPane.showInputDialog("Please enter the students ID number: ");
		Node student = studentRecords.find(studentRecords.root,id);
		try {
			JOptionPane.showMessageDialog(null, "ID: " + student.data.id +"\nFaculty: " + student.data.faculty							 
										+ "\nCC Major: " + student.data.major + "\nSCIE Year: " + student.data.year);
		}
		catch(NullPointerException ex) {
			JOptionPane.showMessageDialog(null, "Target student record not found");		
		}		
	}
}
