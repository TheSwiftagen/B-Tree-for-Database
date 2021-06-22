package yash;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

public class HomePanel extends JPanel {
    private static final long serialVersionUID = 1056433964966474590L;

    public HomePanel(JTabbedPane tabPane) {
        tabPane.addTab("Home", this);
        initComponents();
    }

    private JTextArea jTextArea;
    private void initComponents() {
    	this.setBorder(BorderFactory.createMatteBorder(25, 25, 25, 25, new Color(9, 6, 22)));
		this.setBackground(new Color(56, 100, 15));

        this.jTextArea = new JTextArea();
        this.jTextArea.setFont(new Font("Monospaced", 1, 35));
        this.jTextArea.setForeground(new Color(102, 0, 102));
        this.jTextArea.setColumns(60);
        this.jTextArea.setRows(5);
        this.jTextArea.setText("In this project, I am making a software which will make searching in Relational Database Management system optimized. As well as we will also show better performance of Index seek method over Table scan method.");
        this.jTextArea.setLineWrap(true);
        this.jTextArea.setEditable(false);
        
        javax.swing.GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(
            	layout.createSequentialGroup()
                .addContainerGap(0, Short.MAX_VALUE)
                .addComponent(this.jTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(
            	layout.createSequentialGroup()
                .addContainerGap(0, Short.MAX_VALUE)
                .addComponent(this.jTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(0, Short.MAX_VALUE))
        );
    }                       
}
