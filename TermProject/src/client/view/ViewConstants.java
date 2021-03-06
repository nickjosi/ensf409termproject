package client.view;

import java.awt.Dimension;
import java.awt.Font;

/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 29/03/2019
 * 
 * This interface provides dimensions,fonts and other constants that are used to organize,separate and 
 * enhance components within a JFrame object.
 */
public interface ViewConstants {

	public static final Dimension BUTTON_SPACING = new Dimension(10,0); //All dimension related constants
	public static final Dimension EDGE_SPACING = new Dimension(20,0);
	public static final Dimension PANEL_SIZE = new Dimension(100,40);
	public static final Dimension PREFERRED_FRAME_SIZE = new Dimension(800,500);
	public static final Dimension MINIMUM_FRAME_SIZE = new Dimension(800,500);
	public static final Dimension SEARCH_PANEL_SIZE = new Dimension(75,30);
	public static final Dimension TEXT_FIELD_SIZE = new Dimension(175,30);
	
	public static final int VERTICAL_SPACE = 10;
	
	public static final Font TITLE_FONT = new Font("Helvetica",Font.BOLD,40);
	public static final Font OTHER_FONT = new Font("Helvetica",Font.BOLD,14);
	
	public static final String TITLE = " \"THIS IS A TOOL SHOP.\"\n";
	
}
