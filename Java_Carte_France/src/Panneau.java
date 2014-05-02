import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class Panneau extends JPanel {

	List<Ville> villes;

	public Panneau(List<Ville> villes) {
		this.villes = villes;
	}

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
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
