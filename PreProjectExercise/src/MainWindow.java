//***VIEW**//

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 24/03/2019
 * The following class is a GUI which displays all functionality associated with a student records database. 
 */
public class MainWindow extends JFrame {

	/**
	 * Buttons for user interaction. 
	 */
	private JButton insert, find, browse, create; 
		
	/**
	 * A TextArea object which will display all the student records to the user. 
	 */
	JTextArea studentInfo; 
	
	/**
	 * Scroll bar on the right side of the text area
	 */
	private JScrollPane scroll; 
	
	/**
	 * Title of the window displayed to the user
	 */
	private JLabel title;
	
	/**
	 * Container handle for the Main Window
	 */
	private Container c;
	
	
	/**
	 * Constructs a Main Window object with the necessary fields for user interaction. 
	 * @param s The title of the main window that is displayed to the user
	 */
	public MainWindow(String s) { 
		super(s);
		
		try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Set L&F to current OS preferences
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

		studentInfo = new JTextArea(); //Initializing all Member Variables 
		scroll = new JScrollPane(studentInfo,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		title = new JLabel("An Application to Maintain Student Records",JLabel.CENTER);
		insert = new JButton("Insert"); 
		find = new JButton("Find");
		browse = new JButton("Browse");
		create = new JButton("Create Tree From File");
	
		
		JPanel buttons = new JPanel();//Create new panel to hold buttons and add them to south portion of frame.
		buttons.add(insert); 
		buttons.add(find);
		buttons.add(browse);
		buttons.add(create);
		
		
		title.setFont(new Font("Helvetica", Font.BOLD, 14));
		
		studentInfo.setFont(new Font("SansSerif",Font.ITALIC,12)); //Set JTextArea preferences 
		studentInfo.setEditable(false); 
		
		
		c = getContentPane(); //Get handle on container and add all components
		c.add("South",buttons);
		c.add("Center",scroll);
		c.add("North",title);

		
		setPreferredSize(new Dimension(600,400)); //Set JFrame preferences
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setLocationRelativeTo(null);
	}
	
	/**
	 * Setting the ActionListener for the Browse button object
	 * @param browseListener The BrowseListener to be assigned
	 */
	public void setBrowseListener(ActionListener browseListener) {
		browse.addActionListener(browseListener);
	}
	
	/**
	 * Setting the ActionListener for the Insert button object
	 * @param insertListener The InsertListener to be assigned
	 */
	public void setInsertListener(ActionListener insertListener) {
		insert.addActionListener(insertListener);
	}
	
	/**
	 * Setting the ActionListener for the Find button object
	 * @param findListener The FindListener to be assigned
	 */
	public void setFindListener(ActionListener findListener) {
		find.addActionListener(findListener);
	}
	
	/**
	 * Setting the ActionListener for the Create button object
	 * @param createListener The CreateListener to be assigned
	 */
	public void setCreateListener(ActionListener createListener) {
		create.addActionListener(createListener);
	}
	
}
