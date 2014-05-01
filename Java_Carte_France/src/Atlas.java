import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Atlas extends JFrame {
	
	
	public Atlas() {
		
		Container c = new Container();
		BoxLayout interfaceLayout = new BoxLayout(c, BoxLayout.Y_AXIS);
		c.add(new JButton("TataButton"));
		c.add(new JLabel("toto"));
		Carte carte = new Carte();
		carte.setVisible(true);
		add(carte);
		add(c);
		pack();
		setVisible(true);
	}
}
