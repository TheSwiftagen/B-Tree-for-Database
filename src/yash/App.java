package yash;

import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JFrame;
import java.applet.Applet;

public class App extends JFrame {
	private static final long serialVersionUID = -7533351847804280477L;

	JTabbedPane tabPane;
	HomePanel homePanel;
	DataPanel dataPanel;
	IndexPanel indexPanel;
	QueryPanel queryPanel;

	public App() throws FontFormatException, IOException {
		tabPane = new JTabbedPane();
		File fontFile = new File("/home/yash/Documents/sem5Labs/Internship/project/src/yash/fonts/Poppins-Medium.ttf");
		Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(0, 24);
		tabPane.setFont(font);
		tabPane.setBackground(new Color(9, 6, 22));
		tabPane.setForeground(Color.WHITE);
		
		homePanel = new HomePanel(tabPane);
		dataPanel = new DataPanel(tabPane);
		indexPanel = new IndexPanel(tabPane);
		queryPanel = new QueryPanel(tabPane);
		
		super.add(tabPane);
		
		pack();
		
		super.setTitle("Yash Saxena | B-Tree for Indexing in DataBase Implementation");
		super.setExtendedState(JFrame.MAXIMIZED_BOTH);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		super.setVisible(true);
	}
}
