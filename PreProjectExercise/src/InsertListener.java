import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 24/03/2019
 * The following class is a listener that handles the functionality of the "Insert" button displayed on the Main Window. 
 * Inserts a new node into the binary search tree which contains data entered by the user. 
 */
public class InsertListener implements ActionListener {

	/**
	 * The Main Window object that the user interacts with. 
	 */
	private MainWindow frame;
	
	/**
	 * A binary search tree containing all data pertaining to student records. 
	 */
	private BinSearchTree studentRecords;
	
	/**
	 * Constructs a new object of type InsertListener
	 * @param frame The Main Window object that the user interacts with. 
	 * @param studentRecords A Binary search tree containing all data pertaining to student records
	 */
	public InsertListener(MainWindow frame, BinSearchTree studentRecords) {
		this.frame = frame;
		this.studentRecords = studentRecords;
	}
	
	/**
	 * @Override
	 * Insets a new node into the student records binary search tree containing all the data entered by the user. 
	 */	
	public void actionPerformed(ActionEvent e) {
		JTextField idField = new JTextField();
		JTextField facField = new JTextField();
		JTextField majField = new JTextField();
		JTextField yrField = new JTextField();
		
		Object [] message = {"Enter the Student ID",idField,"Enter Faculty",facField,
							"Enter Student's Major",majField,"Enter Year", yrField};

		int option = JOptionPane.showConfirmDialog(null,message,"Insert a New Node",JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION) {		
			studentRecords.insert(idField.getText(), facField.getText(), majField.getText(), yrField.getText());
			frame.studentInfo.setText(studentRecords.toString());
		}				
	}
}
