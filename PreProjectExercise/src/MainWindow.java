import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 24/03/2019
 * The following class is a GUI which manages all functionality associated with a student records database. 
 */
public class MainWindow extends JFrame {

	
	JButton insert, find, browse, create; //Buttons for user interaction
	JPanel buttons; //Panel containing all the buttons 
	TextArea studentInfo; // Text area containing all the data about students
	
	/**
	 * Scroll bar on the right side of the text area
	 */
	private JScrollPane scroll; //Scroll bar within text area
	
	/**
	 * Title of the window displayed to the user
	 */
	private JLabel title;
	
	/**
	 * Container handle for the Main Window
	 */
	private Container c;
	
	/**
	 * Back end object which implements Action Listener and handles all listeners for the interactive buttons
	 */
	private BackEnd b;
	
	/**
	 * Constructs a Main Window object with the necessary fields for user interaction. 
	 * @param s The title of the main window that is displayed to the user
	 */
	public MainWindow(String s) { 
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
		browse.addActionListener(b.browseListen);
		create.addActionListener(b.createListen);
		find.addActionListener(b.findListen);
		insert.addActionListener(b.insertListen);

		
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
