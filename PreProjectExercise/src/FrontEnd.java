/**
 * @author Carter Shaul/Nick Park
 * @version 1
 * @since 26/03/19
 * The following class is the running point for the entire program.
 * It only contains the static main method, and proceeds to create a View,Controller, and Model object. Following the 
 * MVC model discussed in lecture.
 */
public class FrontEnd {

	public static void main(String[] args) {
		MainWindow view = new MainWindow("Main Window");
		BinSearchTree model = new BinSearchTree();
		BackEnd controller = new BackEnd(view,model);
		
		view.setVisible(true);
	}
}
