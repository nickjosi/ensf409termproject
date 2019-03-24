import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class FindListener implements ActionListener {

	private BinSearchTree studentRecords;
	
	
	public FindListener(BinSearchTree studentRecords) {
		this.studentRecords = studentRecords;
	}


	@Override
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
