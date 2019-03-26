public class FrontEnd {

	public static void main(String[] args) {
		MainWindow view = new MainWindow("Main Window");
		BinSearchTree model = new BinSearchTree();
		
		BackEnd controller = new BackEnd(view,model);
		
		view.setVisible(true);
	}
}
