import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class MainWindow extends JFrame {

	
	JButton insert, find, browse, create; //Buttons for user to interact 
	JPanel buttons; //Panel containing all the buttons 
	TextArea studentInfo; // Text area containing all the data about students
	
	private JScrollPane scroll; //Scroll bar within text area
	private JLabel title;
	private Container c;
	private BackEnd b;
//	private CreateFileListener createListener;
//	private BrowseTreeListener browseListener;
	
	
	
	public MainWindow(String s) { //Constructs a MainWindow object with the required fields
		super(s);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
				
		try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Set L&F to current OS preferences
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		b = new BackEnd(this);
			
		//Declaring all member variables
		insert = new JButton("Insert");
		
		find = new JButton("Find");
		browse = new JButton("Browse");
		create = new JButton("Create Tree From File");
		
		//Assign listener to the buttons 
		browse.addActionListener(b);
		create.addActionListener(b);

		
		
		studentInfo = new TextArea();
		studentInfo.setEditable(false);
		scroll = new JScrollPane(studentInfo,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		title = new JLabel("An Application to Maintain Student Records",JLabel.CENTER);
		
		
		
		//Adding all buttons to the south portion of the frame
		buttons = new JPanel();
		buttons.add(insert);
		buttons.add(find);
		buttons.add(browse);
		buttons.add(create);
		
		c = getContentPane();
		c.add("South",buttons);
		c.add("Center",scroll);
		c.add("North",title);
		
		this.setVisible(true);
		this.pack();
	}
	
	
	
	public static void main(String[] args) {
		MainWindow frame = new MainWindow("Main Window");
	}
}
