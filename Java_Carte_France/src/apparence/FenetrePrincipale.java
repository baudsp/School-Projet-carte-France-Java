package apparence;
import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Classe affichant la carte
 * 
 * @author Baudouin
 *
 */
public class FenetrePrincipale extends JFrame {

	private static final long serialVersionUID = 1L;
	private Carte carte;
	private CommandPanel commandPanel;

	public FenetrePrincipale() {
		// Creation de la carte de France
		carte = new Carte();

		//  Création du panneau de commande
		commandPanel = new CommandPanel(carte);
		
		carte.setObserverOfInfo(commandPanel);
		
		// Positionnement des elements
		BorderLayout windowsLayout = new BorderLayout();
		setLayout(windowsLayout);
		add(carte, BorderLayout.CENTER);
		add(commandPanel, BorderLayout.LINE_END);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(getMaximumSize());
				
		setVisible(true);
	}

}
