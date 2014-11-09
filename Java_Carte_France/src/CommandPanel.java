import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CommandPanel extends Panel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;
	private Carte carte;
	private JButton buttonVille;
	private JTextField textFieldVille;
	private JLabel labelVille;
	private JLabel labelItineraire;
	private JTextField textFieldItineraire1;
	private JTextField textFieldItineraire2;
	private JButton buttonItineraire;
	private JLabel infoCourantes;
		
	public CommandPanel(Carte carte) {
		this.carte = carte;

		// Creation des objets de cette partie de l'interface
		BoxLayout interfaceLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(interfaceLayout);
		
		labelVille = new JLabel("Rechercher une ville sur la carte");
		labelVille.setAlignmentX(Component.CENTER_ALIGNMENT);
		textFieldVille = new JTextField();
		textFieldVille.setMaximumSize(new Dimension(100, 50));
		buttonVille = new JButton("OK");
		buttonVille.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		buttonVille.addActionListener(this);
		
		labelItineraire = new JLabel("Calculer un itineraire");
		labelItineraire.setAlignmentX(Component.CENTER_ALIGNMENT);
		textFieldItineraire1 = new JTextField();
		textFieldItineraire1.setMaximumSize(new Dimension(100, 50));
		textFieldItineraire2 = new JTextField();
		textFieldItineraire2.setMaximumSize(new Dimension(100, 50));
		buttonItineraire = new JButton("OK");
		buttonItineraire.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		infoCourantes = new JLabel("");
		infoCourantes.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.add(labelVille);
		this.add(textFieldVille);
		this.add(buttonVille);


		this.add(labelItineraire);
		this.add(textFieldItineraire1);
		this.add(textFieldItineraire2);
		this.add(buttonItineraire);

		buttonItineraire.addActionListener(this);
		
		this.add(infoCourantes);
	}
	
//	protected void paintComponent(Graphics g) {
//		super.paintComponents(g);
//		g.drawString("HAHAHAAH3", 500,500);
//		this.paint(g);
//	}
	
	@Override
	public void update(Observable o, Object arg) {
		majInfo(arg);		
	}
	
	@Override
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
	
	/**
	 * Mise à jour des infos
	 * @param arg 
	 */
	public void majInfo(Object arg) {
		infoCourantes = new JLabel((String) arg);
		
		System.out.println("maj commandPanel");
	}
}
