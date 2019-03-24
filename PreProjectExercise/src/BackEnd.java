public class BackEnd{
	
	private BinSearchTree studentRecords;
	BrowseListener browseListen;
	CreateListener createListen;
	FindListener findListen;
	InsertListener insertListen;
	
	public BackEnd(MainWindow frame) {
		studentRecords = new BinSearchTree();
		browseListen = new BrowseListener(frame,studentRecords);
		createListen = new CreateListener(studentRecords);
		findListen = new FindListener(studentRecords);
		insertListen = new InsertListener(frame,studentRecords);
	}
	
	
//	public void actionPerformed(ActionEvent e) { //TODO split these bad boys up into their own thingy majigs
//		if(e.getSource() == frame.create) { //Create a tree from file
//			String fileName = JOptionPane.showInputDialog("Enter the file name: ");
//			createTreeFromFile(fileName);
//		}
//		else if(e.getSource() == frame.browse) { // browse tree
//			try {
//				frame.studentInfo.setText(studentRecords.toString());
//			}
//			catch(NullPointerException ex) {
//				frame.studentInfo.setText("No file has been loaded");
//			}	
//		}
//		else if(e.getSource() == frame.find) { //Find a student record in tree
//			String id = JOptionPane.showInputDialog("Please enter the students ID number: ");
//			Node student = studentRecords.find(studentRecords.root,id);
//			try {
//				JOptionPane.showMessageDialog(null, "ID: " + student.data.id +"\nFaculty: " + student.data.faculty							 
//											+ "\nCC Major: " + student.data.major + "\nSCIE Year: " + student.data.year);
//			}
//			catch(NullPointerException ex) {
//				JOptionPane.showMessageDialog(null, "Target student record not found");		
//			}
//		}
//		else { //Insert a new node into the tree
//			JTextField idField = new JTextField();
//			JTextField facField = new JTextField();
//			JTextField majField = new JTextField();
//			JTextField yrField = new JTextField();
//			
//			Object [] message = {"Enter the Student ID",idField,"Enter Faculty",facField,
//								"Enter Student's Major",majField,"Enter Year", yrField};
//
//			int option = JOptionPane.showConfirmDialog(null,message,"Insert a New Node",JOptionPane.YES_NO_OPTION);
//			if(option == JOptionPane.OK_OPTION) {		
//				studentRecords.insert(idField.getText(), facField.getText(), majField.getText(), yrField.getText());
//				frame.studentInfo.setText(studentRecords.toString());
//			}		
//		}
//	}
//	
//	public void createTreeFromFile(String fileName) {
//		studentRecords = new BinSearchTree();
//		try {
//			Scanner scan = new Scanner(new File(fileName));
//		
//			scan.useDelimiter("[ |\\n]+");
//		
//			String id,faculty,major,year;
//			while(scan.hasNext()) {
//				id = scan.next();
//				faculty = scan.next();
//				major = scan.next();
//				year = scan.next();
//				studentRecords.insert(id, faculty, major, year);
//			}	
//			scan.close();
//		}
//		catch(IOException e) {
//			JOptionPane.showMessageDialog(null, "File could not be located", "Error Message",JOptionPane.ERROR_MESSAGE);
//		}
//	}
}