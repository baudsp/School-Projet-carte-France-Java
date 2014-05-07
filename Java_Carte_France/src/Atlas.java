import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Atlas extends JFrame implements ActionListener{
	
	private Carte carte;
	private JButton button;
	private JTextField textField;
	private JLabel label;

	public Atlas() {

		
		// Creation de la carte de France
		 carte = new Carte();
				
				
		// Creation de l'interface
		Panel c = new Panel();
		BoxLayout interfaceLayout = new BoxLayout(c, BoxLayout.Y_AXIS);
		c.setLayout(interfaceLayout);

		button = new JButton("OK");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		label = new JLabel("Rechercher une ville sur la carte");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		textField = new JTextField();
		textField.setMaximumSize(new Dimension(100, 50));
		
		c.add(label);
		c.add(textField);
		c.add(button);

		button.addActionListener(this);

		// Creation de la fenetre et positionnement des elements
		BorderLayout windowsLayout = new BorderLayout();
		setLayout(windowsLayout);
		add(carte, BorderLayout.CENTER);
		add(c, BorderLayout.LINE_END);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(getMaximumSize());
		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		String ville = this.textField.getText();
		
		this.carte.setVilleSelectionnee(ville);
	}
}
