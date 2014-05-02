import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class Carte extends JPanel {

	private List<Ville> villes;
	private int villeSelectionnee = -1;

	public Carte() {
		setBounds(0, 0, 800, 800);

		Monde monde = new Monde();
		monde.charger();
		this.villes = monde.getVilles();

		setVisible(true);
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		for (Ville ville : villes) {
			int x = ville.getX();
			int y = ville.getY();

			// on regle la taille du nom des villes
			g.setFont(new Font("TimesRoman", Font.PLAIN, 12));

			// Placement de la ville sur la carte
			g.fillRect(3 * x / 4, 3 * y / 4, 4, 4);

			g.setColor(Color.ORANGE);

			Ville[] voisines = ville.getVoisines();
			for (int i = 0; i < 10 && voisines[i] != null; i++) {
				g.drawLine(3 * x / 4, 3 * y / 4, voisines[i].getX() * 3 / 4,
						voisines[i].getY() * 3 / 4);
			}

			// Si la ville est celle qui est sélectionnée, on change la couleur
			// en rouge
			if (ville.getCode() == villeSelectionnee) {
				g.setColor(Color.RED);
				g.drawString(ville.getNom(), 3 * x / 4, 3 * y / 4);
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.BLACK);
				g.drawString(ville.getNom(), 3 * x / 4, 3 * y / 4);
			}
		}
	}

	public void update(Graphics g) {
		super.update(g);
		paintComponent(g);
	}

	public void setVilleSelectionnee(Ville v) {
		villeSelectionnee = v.getCode();
	}
}
