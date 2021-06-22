import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Test
 */
public class Test extends JFrame{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Test() {
        super.add(new JPanel());
        pack();
        super.setTitle("this is java viva");
		super.setExtendedState(JFrame.MAXIMIZED_BOTH);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
    }

    public static void main(String[] args) {
        Test t = new Test();
    }
}