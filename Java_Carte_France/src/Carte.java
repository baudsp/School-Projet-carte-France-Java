import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class Carte extends JPanel {

	private List<Ville> villes;
	private int villeSelectionnee = -1;
	private Monde monde;
	private Itineraire itineraire = null;

	public Carte() {
		setBounds(0, 0, 800, 800);

		monde = new Monde();
		monde.charger();
		this.villes = monde.getVilles();

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
			Ville[] voisines = ville.getVoisines();
			for (int i = 0; i < 10 && voisines[i] != null; i++) {
				g.drawLine(3 * x / 4, 3 * y / 4, voisines[i].getX() * 3 / 4,
						voisines[i].getY() * 3 / 4);
				// Avec cette méthode, toutes les routes sont dessinées deux
				// fois, une fois la route de A à B, puis la route de B à A
			}

			// Si la ville est celle qui est sélectionnée ou est sur
			// l'itineraire, on change la police
			// en rouge, gras et taille 18
			if (ville.getCode() == this.villeSelectionnee
					|| (this.itineraire != null && this.itineraire
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

		// On va repeindre les routes de l'itinéraire en rouge (par-dessus celle
		// en orange)
		if (this.itineraire != null) {
			g.setColor(Color.BLUE);

			List<Ville> villesItineraire = this.itineraire
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

	public void setVilleSelectionnee(String ville) {
		Ville villeSelect = this.monde.getVilleParNom(ville);

		if (villeSelect != null) {
			// on met itineraire a null pour n'avoir qu'une seule chose à
			// l'écran (soit la ville selectionnee, soit la l'itinéraire)
			this.itineraire = null;

			this.villeSelectionnee = villeSelect.getCode();
			repaint();
		}
	}

	public void setItineraire(String ville1, String ville2) {
		Ville villeSelect1 = this.monde.getVilleParNom(ville1);
		Ville villeSelect2 = this.monde.getVilleParNom(ville2);

		if (villeSelect1 != null && villeSelect2 != null) {
			// on met enleve la ville selectionnee pour n'avoir qu'une seule
			// chose à l'écran (soit la ville selectionnee, soit la l'itinéraire)
			this.villeSelectionnee = -1;

			this.itineraire = new Itineraire(villeSelect1, villeSelect2);
			repaint();
		}
	}
}
