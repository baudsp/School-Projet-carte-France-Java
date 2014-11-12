package apparence;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import domaine.InfoCarte;
import domaine.Monde;
import domaine.Ville;

/**
 * Classe responsable de l'affichage graphique de la carte
 * 
 * @author Baudouin
 *
 */
public class Carte extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<Ville> villes;
	private Monde monde;
	private InfoCarte infoCarte;

	public Carte() {

		monde = new Monde();
		monde.charger();
		this.villes = monde.getVilles();
		
		infoCarte = new InfoCarte(monde);		
		
		setVisible(true);
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		for (Ville ville : this.villes) {
			int x = ville.getX();
			int y = ville.getY();

			// on regle la taille du nom des villes
			g.setFont(new Font("TimesRoman", Font.PLAIN, 12));

			// Placement de la ville sur la carte
			g.fillRect(3 * x / 4, 3 * y / 4, 4, 4);
			g.setColor(Color.ORANGE);

			// On dessine les routes
			
			List<Ville>voisines = ville.getVoisines();
			for (int i = 0; i < voisines.size(); i++) {
				g.drawLine(3 * x / 4, 3 * y / 4, voisines.get(i).getX() * 3 / 4,
						voisines.get(i).getY() * 3 / 4);
				// Avec cette m�thode, toutes les routes sont dessin�es deux
				// fois, une fois la route de A � B, puis la route de B � A
			}

			// Si la ville est celle qui est s�lectionn�e ou est sur
			// l'itineraire, on change la police
			// en rouge, gras et taille 18
			if (ville.getCode() == infoCarte.getVilleSelectionnee()
					|| (infoCarte.getItineraire() != null && infoCarte.getItineraire()
							.checkVille(ville))) {
				g.setFont(new Font("TimesRoman", Font.BOLD, 18));
				g.setColor(Color.RED);
				g.drawString(ville.getNom(), 3 * x / 4, 3 * y / 4);
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.BLACK);
				g.drawString(ville.getNom(), 3 * x / 4, 3 * y / 4);
			}
		}

		// On va repeindre les routes de l'itin�raire en rouge (par-dessus celle
		// en orange)
		if (infoCarte.getItineraire() != null) {
			g.setColor(Color.BLUE);

			List<Ville> villesItineraire = infoCarte.getItineraire()
					.getVillesItineraire();

			for (int i = 0; i < villesItineraire.size() - 1; i++) {
				Ville v1 = villesItineraire.get(i);
				Ville v2 = villesItineraire.get(i + 1);
				g.drawLine(v1.getX() * 3 / 4, v1.getY() * 3 / 4,
						v2.getX() * 3 / 4, v2.getY() * 3 / 4);
			}
		}
	}

	public void update(Graphics g) {
		super.update(g);
		paintComponent(g);
	}
	
	/**
	 * On affiche la ville demand�e, si elle existe
	 * @param ville
	 */
	public void setVilleSelectionnee(String ville) {
		Ville villeSelect = this.monde.getVilleParNom(ville);

		if (villeSelect != null) {
			// on met itineraire a null pour n'avoir qu'une seule chose �
			// l'�cran (soit la ville selectionnee, soit la l'itin�raire)
			infoCarte.setVilleSelectionnee(villeSelect);
			repaint();
		}
	}

	/**
	 * On essaie de creer un itineraire et si �a marche, on appele repaint 
	 * pour qu'il s'affiche
	 * @param ville1
	 * @param ville2
	 */
	public void setItineraire(String ville1, String ville2) {
		Ville villeSelect1 = this.monde.getVilleParNom(ville1);
		Ville villeSelect2 = this.monde.getVilleParNom(ville2);

		if (villeSelect1 != null && villeSelect2 != null) {
			// on met enleve la ville selectionnee pour n'avoir qu'une seule
			// chose � l'�cran (soit la ville selectionnee, soit la
			// l'itin�raire)
			infoCarte.setItineraire(villeSelect1, villeSelect2);
			repaint();
		}
	}

	/**
	 * Retourne des infos sur la ville ou l'itineraire affiche sur la carte
	 * � ce moment
	 */
	public String getCarteInfo() {
		return infoCarte.toString();
	}

	public void setObserverOfInfo(CommandPanel commandPanel) {
		infoCarte.addObserver(commandPanel);		
	}
}