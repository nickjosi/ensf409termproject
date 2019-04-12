package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 29/03/2019
 * 
 * This class sets the size and appearance of a search bar.
 */
public class SearchBar extends JTextField {
		
	/**
	 * Constructs a SearchBar object with the set size and appearance.
	 */
	public SearchBar() {
		super();
		Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK,Color.BLUE,Color.BLUE,Color.DARK_GRAY);
		super.setBorder(border);
		super.setPreferredSize(new Dimension(200,30));
		super.setMinimumSize(new Dimension(200,30));
		super.setMaximumSize(new Dimension(200,30));
	}
	
}
