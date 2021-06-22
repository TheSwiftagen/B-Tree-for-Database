package yash;

import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class IndexPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -7690374334762528979L;

	private Font font;
	private JLabel lblRows;
	private JComboBox<String> comboIndices;
	private JButton btnCreate;
	private JProgressBar progressBar;

	public IndexPanel(JTabbedPane tabPane) throws FontFormatException, IOException {
		tabPane.addTab("Indices", this);
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
		this.lblRows.setText("Column:");

		this.comboIndices = new JComboBox<>(new String[] { "--Select One--", "Name", "UserName", "Password" });
		this.comboIndices.setFont(new Font("Monospaced", 0, 35)); // NOI18N

		this.btnCreate = new JButton();
		this.btnCreate.setFont(font); // NOI18N
		this.btnCreate.setText("Create Index");
		this.btnCreate.addActionListener(this);

		this.progressBar = new JProgressBar();
		this.progressBar.setStringPainted(true);
		this.progressBar.setMinimum(0);
		this.progressBar.setMaximum(100);
		this.progressBar.setFont(font);

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(this.lblRows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(this.comboIndices, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(this.btnCreate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(this.progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(
				layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(this.comboIndices, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblRows, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(50, 50, 50)
						.addComponent(this.btnCreate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(50, 50, 50).addComponent(this.progressBar, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		IndexPanel obj = this;
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (obj.comboIndices.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(obj, "Please select a column");
						return;
					}
					DataManager.CreateIndex(obj.comboIndices.getSelectedItem().toString(), obj);
					JOptionPane.showMessageDialog(obj, "Index created successfully");
					obj.progressBar.setValue(0);
				} catch (Exception ex) {
					System.err.println("thread action failed here Yash : \n" + ex.toString());
				}
			}
		});
		t.start();
	}

	public void updateStatus(int percent) {
		this.progressBar.setValue(percent);
	}
}
