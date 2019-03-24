import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseListener implements ActionListener {

	private MainWindow frame;
	private BinSearchTree studentRecords;
	
	public BrowseListener(MainWindow frame,BinSearchTree studentRecords) {
		this.frame = frame;
		this.studentRecords = studentRecords;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			frame.studentInfo.setText(studentRecords.toString());
		}
		catch(NullPointerException ex) {
			frame.studentInfo.setText("No file has been loaded");
		}			
	}

}
