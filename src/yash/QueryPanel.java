package yash;

import java.io.File;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;

import yash.DataManager.SearchResult;

public class QueryPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -7885384077514263683L;

	private Font font;
	private JLabel lblselQuery;
	private JComboBox<String> comboBoxQuery;
	private JLabel lbleql;
	private JTextField txtQuery;
	private JButton btnSearch;
	private JLabel lblStats;
	private JLabel lblUser;

	public QueryPanel(JTabbedPane tabPane) throws FontFormatException, IOException {
		tabPane.addTab("Query", this);
		File fontFile = new File("/home/yash/Documents/sem5Labs/Internship/project/src/yash/fonts/Poppins-Medium.ttf");
		font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
		initComponents();
	}

	private void initComponents() {
		this.setBorder(BorderFactory.createMatteBorder(25, 25, 25, 25, new Color(9, 6, 22)));
		this.setBackground(new Color(56, 100, 15));

		this.lblselQuery = new JLabel();
		this.lblselQuery.setFont(font.deriveFont(0, 45f));
		this.lblselQuery.setForeground(new Color(255, 255, 255));
		this.lblselQuery.setText("Select * from Table WHERE ");

		String[] option = { "Name", "UserName", "Password" };
		this.comboBoxQuery = new JComboBox<>(option);
		this.comboBoxQuery.setFont(font.deriveFont(0, 20f));
		this.comboBoxQuery.setPreferredSize(new Dimension(150, 50));

		this.lbleql = new JLabel();
		this.lbleql.setFont(font.deriveFont(0, 45f));
		this.lbleql.setForeground(new Color(255, 255, 255));
		this.lbleql.setText("=");

		this.txtQuery = new JTextField("");
		this.txtQuery.setColumns(10);
		this.txtQuery.setFont(font.deriveFont(0, 36));

		this.btnSearch = new JButton();
		this.btnSearch.setFont(font.deriveFont(0, 50));
		this.btnSearch.setText("Search");
		this.btnSearch.addActionListener(this);

		this.lblStats = new JLabel();
		this.lblStats.setFont(font.deriveFont(0, 25f));
		this.lblStats.setForeground(new Color(255, 255, 255));
		this.lblStats.setText("Search Method: ##SM##, Time taken: ##TT##, Pages Read: ##PR##");
		
		this.lblUser = new JLabel();
		this.lblUser.setFont(font.deriveFont(0, 25f));
		this.lblUser.setForeground(new Color(255, 255, 255));
		this.lblUser.setText("Id: ##ID##, Name: ##NAME##, User Name: ##UN##, Password: ##PWD##");

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(this.lblselQuery, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(this.comboBoxQuery, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(this.lbleql, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.txtQuery,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

		.addGroup(layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(this.btnSearch,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

		.addGroup(layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(this.lblStats,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		
		.addGroup(layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(this.lblUser,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(this.lblselQuery, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.comboBoxQuery, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lbleql, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.txtQuery, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(50, 50, 50)
				.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(50, 50, 50)
				.addComponent(this.lblStats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(50, 50, 50)
				.addComponent(this.lblUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DataManager.Search(this.comboBoxQuery.getSelectedItem().toString(), this.txtQuery.getText(), this);
	}
	
	public void updateResults(SearchResult result)
	{
		if(result.DataRow != null){
			this.lblUser.setText
			(
					"Roll Number: ##RNO##, Name: ##NAME##, User Name: ##UN##, Password: ##PWD##"
					.replace("##RNO##", result.DataRow.RollNum + "\n")
					.replace("##NAME##", result.DataRow.Name + "\n")
					.replace("##UN##", result.DataRow.UserName + "\n")
					.replace("##PWD##", result.DataRow.Password + "\n")
			);
		}
		this.lblStats.setText
		(
				"Search Method: ##SM##, Time taken: ##TT##, Pages Read: ##PR##"
				.replace("##SM##", result.IndexesUsed? "Index Seek\n": "Table Scan\n")
				.replace("##TT##", result.TimeTaken + " ms\n")
				.replace("##PR##", result.PagesLoaded + "\n")
		);
	}
}
