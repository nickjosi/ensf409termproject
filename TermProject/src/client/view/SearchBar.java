package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class SearchBar extends JTextField {
	
	private TitledBorder title;
	
	public SearchBar() {
		super();
		Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK,Color.BLUE,Color.BLUE,Color.DARK_GRAY);
		title = BorderFactory.createTitledBorder(border,"Search",TitledBorder.CENTER,TitledBorder.CENTER,new Font("Helvetica",Font.ITALIC,14));
		super.setPreferredSize(new Dimension(200,35));
		super.setMinimumSize(new Dimension(100,30));
		super.setMaximumSize(new Dimension(300,40));
		super.setBorder(title);
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("TEST");
		frame.add(new SearchBar());
		frame.setVisible(true);
		frame.setOpacity(0);
	}
}
