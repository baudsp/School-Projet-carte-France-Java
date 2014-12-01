package apparence;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import domaine.InfoCarte;
import domaine.Pays;
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
	private Pays pays;
	private InfoCarte infoCarte;

	public Carte() {

		pays = new Pays();
		pays.charger();
		this.villes = pays.getVilles();

		infoCarte = new InfoCarte(pays);

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

			List<Ville> voisines = ville.getVoisines();
			for (int i = 0; i < voisines.size(); i++) {
				g.drawLine(3 * x / 4, 3 * y / 4,
						voisines.get(i).getX() * 3 / 4,
						voisines.get(i).getY() * 3 / 4);
				// Avec cette méthode, toutes les routes sont dessinées deux
				// fois, une fois la route de A à B, puis la route de B à A
			}

			// Si la ville est celle qui est sélectionnée ou est sur
			// un des itineraire, on change la police
			// en rouge, gras et taille 18
			if (ville.getCode() == infoCarte.getVilleSelectionnee()
					|| ((infoCarte.getItineraire() != null && infoCarte
							.getItineraire().checkVille(ville)) || (infoCarte
							.getItDijkstra() != null && infoCarte
							.getItDijkstra().checkVille(ville)))) {
				g.setFont(new Font("TimesRoman", Font.BOLD, 18));
				g.setColor(Color.RED);
				g.drawString(ville.getNom(), 3 * x / 4, 3 * y / 4);
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.BLACK);
				g.drawString(ville.getNom(), 3 * x / 4, 3 * y / 4);
			}
		}

		// On va repeindre les routes de l'itinéraire en bleue (par-dessus celle
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
		// On repeind les routes de l'itineraire calcule avec dijkstra
		// en vert
		if (infoCarte.getItDijkstra() != null) {
			g.setColor(Color.GREEN);

			List<Ville> villesItineraire = infoCarte.getItDijkstra()
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
	 * On affiche la ville demandée, si elle existe
	 * 
	 * @param ville
	 */
	public void setVilleSelectionnee(String ville) {
		Ville villeSelect = this.pays.getVilleParNom(ville);

		if (villeSelect != null) {
			// on met itineraire a null pour n'avoir qu'une seule chose à
			// l'écran (soit la ville selectionnee, soit la l'itinéraire)
			infoCarte.setVilleSelectionnee(villeSelect);
			repaint();
		}
	}

	/**
	 * On essaie de creer un itineraire et si ça marche, on appele repaint pour
	 * qu'il s'affiche
	 * 
	 * @param ville1
	 * @param ville2
	 */
	public void setItineraire(String ville1, String ville2) {
		Ville villeSelect1 = this.pays.getVilleParNom(ville1);
		Ville villeSelect2 = this.pays.getVilleParNom(ville2);

		if (villeSelect1 != null && villeSelect2 != null) {
			// on met enleve la ville selectionnee pour n'avoir qu'une seule
			// chose à l'écran (soit la ville selectionnee, soit la
			// l'itinéraire)
			infoCarte.setItineraire(villeSelect1, villeSelect2);
			repaint();
		}
	}

	/**
	 * Retourne des infos sur la ville ou l'itineraire affiche sur la carte à ce
	 * moment
	 */
	public String getCarteInfo() {
		return infoCarte.toString();
	}

	public void setObserverOfInfo(CommandPanel commandPanel) {
		infoCarte.addObserver(commandPanel);
	}
}
