import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Atlas extends JFrame {

	public Atlas() {

		// Creation de l'interface
		Panel c = new Panel();
		BoxLayout interfaceLayout = new BoxLayout(c, BoxLayout.Y_AXIS);
		c.setLayout(interfaceLayout);

		JButton button = new JButton("OK");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel label = new JLabel("Rechercher une ville sur la carte");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		JTextField textField = new JTextField();
		textField.setMaximumSize(new Dimension(100, 50));

		c.add(label);
		c.add(textField);
		c.add(button);

		// Creation de la carte de France
		Carte carte = new Carte();

		// Creation de la fenetre et positionnement des elements
		BorderLayout windowsLayout = new BorderLayout();
		setLayout(windowsLayout);
		add(carte, BorderLayout.CENTER);
		add(c, BorderLayout.LINE_END);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(getMaximumSize());
		setVisible(true);

	}
}
