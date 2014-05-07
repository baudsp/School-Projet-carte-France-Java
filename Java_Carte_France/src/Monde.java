import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Monde {

	private List<Ville> villes = new ArrayList<Ville>();

	public void charger() {

		// On charge les villes
		TextFile tf = new TextFile("ville.dat");
		System.out.println(tf.getSize());
		for (int i = 0; i < tf.getSize(); i++) {
			String[] tab = tf.getLine(i).split(";");

			System.out.println(tab[0]);

			String nom = tab[0];
			int code = Integer.parseInt(tab[1]);
			int x = Integer.parseInt(tab[2]);
			int y = Integer.parseInt(tab[3]);
			Ville ville = new Ville(nom, code, x, y);
			villes.add(ville);
		}

		// On charge les routes
		TextFile rf = new TextFile("Route.dat");
		System.out.println("nb de routes : " + rf.getSize());
		for (int i = 0; i < rf.getSize(); i++) {
			String[] tab = rf.getLine(i).split(";");

			int codeDepart = Integer.parseInt(tab[0]);
			int codeArrivee = Integer.parseInt(tab[1]);
			int distance = Integer.parseInt(tab[2]);

			Ville villeDepart = getVilleParCode(codeDepart);

			if (villeDepart != null) {
				villeDepart.ajouterVoisine(getVilleParCode(codeArrivee),
						distance);
			}
			
			Ville villeArrivee = getVilleParCode(codeArrivee);
			
			if (villeArrivee != null) {
				villeArrivee.ajouterVoisine(getVilleParCode(codeDepart), distance);
			}
		}
	}

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}

	public Ville getVilleParNom(String nomVille) {

		// Un itérateur qui va parcourir toutes les villes
		Iterator<Ville> iterator = villes.iterator();

		while (iterator.hasNext()) {
			Ville curVille = iterator.next();
			if (curVille.getNom().equalsIgnoreCase(nomVille.trim())) {
				return curVille;
			}
		}

		// On n'a rien trouvé, on retourne NULL
		return null;
	}

	public Ville getVilleParCode(int code) {
		// Un itérateur qui va parcourir toutes les villes
		Iterator<Ville> iterator = villes.iterator();

		while (iterator.hasNext()) {
			Ville curVille = iterator.next();
			if (curVille.getCode() == code) {
				return curVille;
			}
		}

		// On n'a rien trouvé, on retourne NULL
		return null;
	}
}
