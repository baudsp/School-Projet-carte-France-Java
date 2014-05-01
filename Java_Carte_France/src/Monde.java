import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Monde {
	List<Ville> villes = new ArrayList<Ville>();

	public void charger() {
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

	}

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}

	public Ville getVilleParNom(String nomVille){
		
		// Un itérateur qui va parcourir toutes les villes
		Iterator<Ville> iterator = villes.iterator();
		
		while (iterator.hasNext()) {
			Ville curVille = iterator.next();
			if (curVille.getNom().equalsIgnoreCase(nomVille)) {
				return curVille;
			}
		}
		
		// On n'a rien trouvé, on retourne NULL
		return null;
	}
}
