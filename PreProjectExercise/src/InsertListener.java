import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InsertListener implements ActionListener {

	private MainWindow frame;
	private BinSearchTree studentRecords;
	
	public InsertListener(MainWindow frame, BinSearchTree studentRecords) {
		this.frame = frame;
		this.studentRecords = studentRecords;
	}
	
	@Override
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
