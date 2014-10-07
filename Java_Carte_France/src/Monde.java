import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Monde {

	private List<Ville> villes = new ArrayList<Ville>();

	public void charger() {

		// On charge les villes
		FileInputStream fisVille;
		 Scanner scanner;
		try {
			fisVille = new FileInputStream("ville.dat");
			scanner = new Scanner(fisVille);
			
			while(scanner.hasNextLine()) {			
		
				String[] tab = scanner.nextLine().split(";");

				System.out.println(tab[0]);

				String nom = tab[0];
				int code = Integer.parseInt(tab[1]);
				int x = Integer.parseInt(tab[2]);
				int y = Integer.parseInt(tab[3]);
				Ville ville = new Ville(nom, code, x, y);
				villes.add(ville);
			}

			// On charge les routes
			FileInputStream fisRoute = new FileInputStream("route.dat");
			scanner = new Scanner(fisRoute);
			while (scanner.hasNextLine()) {
				String[] tab = scanner.nextLine().split(";");

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
					villeArrivee.ajouterVoisine(getVilleParCode(codeDepart),
							distance);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}

	public Ville getVilleParNom(String nomVille) {

		// Un it�rateur qui va parcourir toutes les villes
		Iterator<Ville> iterator = villes.iterator();

		while (iterator.hasNext()) {
			Ville curVille = iterator.next();
			if (curVille.getNom().equalsIgnoreCase(nomVille.trim())) {
				return curVille;
			}
		}
		// On n'a rien trouv�, on retourne null
		return null;
	}

	public Ville getVilleParCode(int code) {
		// Un it�rateur qui va parcourir toutes les villes
		Iterator<Ville> iterator = villes.iterator();

		while (iterator.hasNext()) {
			Ville curVille = iterator.next();
			if (curVille.getCode() == code) {
				return curVille;
			}
		}
		// On n'a rien trouv�, on retourne null
		return null;
	}

}
