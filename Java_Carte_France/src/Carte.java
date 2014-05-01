import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class Carte extends JPanel {

	List<Ville> villes;

	public Carte() {
		setBounds(0, 0, 800, 800);

		Monde monde = new Monde();
		monde.charger();
		this.villes = monde.getVilles();

		setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		for (Ville ville : villes) {
			int x = ville.getX();
			int y = ville.getY();
			g.fillRect(x, y, 5, 5);
			g.drawString(ville.getNom(), x, y);
		}
	}

}
