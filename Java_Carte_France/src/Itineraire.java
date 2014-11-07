import java.util.ArrayList;
import java.util.List;

public class Itineraire {

	private List<Ville> villesItineraire = new ArrayList<Ville>();
	private int nbrVilles = 0;

	public Itineraire(Ville depart, Ville arrivee) {

		villesItineraire.add(depart);

		Ville curVille = depart;

		nbrVilles++;

		while (curVille.getCode() != arrivee.getCode()) {

			curVille = curVille.getPlusProcheVoisines(arrivee);

			villesItineraire.add(curVille);

			nbrVilles++;
		}
	}

	public List<Ville> getVillesItineraire() {
		return villesItineraire;
	}

	public int getNbrVilles() {
		return nbrVilles;
	}

	/**
	 * Verifie si la ville fait partie de l'itineraire
	 */
	public boolean checkVille(Ville v) {
		return this.villesItineraire.contains(v);
	}
}
