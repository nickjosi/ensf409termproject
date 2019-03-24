import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CreateListener implements ActionListener{

	private BinSearchTree studentRecords;
	
	
	public CreateListener(BinSearchTree studentRecords) {
		this.studentRecords = studentRecords;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String fileName = JOptionPane.showInputDialog("Enter the file name: ");
		createTreeFromFile(fileName);		
	}
	
	
	public void createTreeFromFile(String fileName) { 
//		studentRecords = new BinSearchTree();
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
