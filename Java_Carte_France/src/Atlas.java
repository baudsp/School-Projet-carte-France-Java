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

public class Atlas extends JFrame implements ActionListener {

	private Carte carte;
	private JButton buttonVille;
	private JTextField textFieldVille;
	private JLabel labelVille;
	private JLabel labelItineraire;
	private JTextField textFieldItineraire1;
	private JTextField textFieldItineraire2;
	private JButton buttonItineraire;

	public Atlas() {

		// Creation de la carte de France
		carte = new Carte();

		// Creation de l'interface
		Panel c = new Panel();
		BoxLayout interfaceLayout = new BoxLayout(c, BoxLayout.Y_AXIS);
		c.setLayout(interfaceLayout);

		labelVille = new JLabel("Rechercher une ville sur la carte");
		labelVille.setAlignmentX(Component.CENTER_ALIGNMENT);
		textFieldVille = new JTextField();
		textFieldVille.setMaximumSize(new Dimension(100, 50));
		buttonVille = new JButton("OK");
		buttonVille.setAlignmentX(Component.CENTER_ALIGNMENT);

		c.add(labelVille);
		c.add(textFieldVille);
		c.add(buttonVille);

		buttonVille.addActionListener(this);

		labelItineraire = new JLabel("Rechercher une ville sur la carte");
		labelItineraire.setAlignmentX(Component.CENTER_ALIGNMENT);
		textFieldItineraire1 = new JTextField();
		textFieldItineraire1.setMaximumSize(new Dimension(100, 50));
		textFieldItineraire2 = new JTextField();
		textFieldItineraire2.setMaximumSize(new Dimension(100, 50));
		buttonItineraire = new JButton("Calculer un itineraire");
		buttonItineraire.setAlignmentX(Component.CENTER_ALIGNMENT);

		c.add(labelItineraire);
		c.add(textFieldItineraire1);
		c.add(textFieldItineraire2);
		c.add(buttonItineraire);

		buttonItineraire.addActionListener(this);

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
		// si l'utilisateur appuie sur un des boutons auxquels on a ajouté un
		// action listener, cette methode est appelee

		// Pour distinguer le bouton, on utilise la source de e:
		if (e.getSource() == this.buttonVille) {
			String ville = this.textFieldVille.getText();
			this.carte.setVilleSelectionnee(ville);
		} else if (e.getSource() == this.buttonItineraire) {
			String v1 = this.textFieldItineraire1.getText();
			String v2 = this.textFieldItineraire2.getText();
			this.carte.setItineraire(v1, v2);
		}
	}
}
