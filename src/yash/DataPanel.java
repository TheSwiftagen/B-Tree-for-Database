package yash;

import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
// import javax.swing.border.Border;

public class DataPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 2210774784857527122L;

	private Font font;
	private JLabel lblRows;
	private JTextField txtRows;
	private JButton btnCreate;
	private JProgressBar progressBar;

	public DataPanel(JTabbedPane tabPane) throws FontFormatException, IOException {
		tabPane.addTab("Data", this);
		File fontFile = new File("/home/yash/Documents/sem5Labs/Internship/project/src/yash/fonts/Poppins-Medium.ttf");
		font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(48f);
		initComponents();
	}

	private void initComponents() {
		this.setBorder(BorderFactory.createMatteBorder(25, 25, 25, 25, new Color(9, 6, 22)));
		this.setBackground(new Color(56, 100, 15));

		this.lblRows = new JLabel();
		this.lblRows.setFont(font); // NOI18N
		this.lblRows.setForeground(new Color(255, 255, 255));
		this.lblRows.setText("Rows:");

		this.txtRows = new JTextField("");
		this.txtRows.setColumns(10);
		this.txtRows.setFont(font); // NOI18N

		this.btnCreate = new JButton();
		this.btnCreate.setFont(font); // NOI18N
		this.btnCreate.setText("Create Test Data");
		this.btnCreate.addActionListener(this);

		this.progressBar = new JProgressBar();
		this.progressBar.setStringPainted(true);
		this.progressBar.setMinimum(0);
		this.progressBar.setMaximum(100);
		this.progressBar.setFont(font);
		// this.progressBar.setFont(new Font("Monospaced", 0, 50));

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(this.lblRows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(this.txtRows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(this.btnCreate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(this.progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(this.txtRows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(this.lblRows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(50, 50, 50)
				.addComponent(this.btnCreate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(50, 50, 50).addComponent(this.progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DataPanel obj = this; // for sending a refernce of myself (neeche Thread ki anonymous class hai)
		Thread thrd = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int rows = Integer.parseInt(obj.txtRows.getText());
					DataManager.CreateData(rows, obj);
					JOptionPane.showMessageDialog(obj, "Data created successfully! Yay Yash!!");
					obj.progressBar.setValue(0);
				} catch (Exception ex) {
					System.err.println("thread action failed here Yash : \n" + ex.toString());
				}
			}
		});
		thrd.start();
	}

	public void updateStatus(int percent) {
		this.progressBar.setValue(percent);
	}
}
