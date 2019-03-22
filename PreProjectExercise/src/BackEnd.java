import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class BackEnd implements ActionListener{
	
	private MainWindow frame;
	private BinSearchTree studentRecords;
	
	public BackEnd(MainWindow frame) {
		this.frame = frame;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == frame.create) {
			String fileName = JOptionPane.showInputDialog("Enter the file name: ");
			createTreeFromFile(fileName);
		}
		else if(e.getSource() == frame.browse) {
			try {
				frame.studentInfo.setText(studentRecords.toString());
			}
			catch(NullPointerException ex) {
				frame.studentInfo.setText("No file has been loaded");
			}
		}
	}
	
	public void createTreeFromFile(String fileName) {
		studentRecords = new BinSearchTree();
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