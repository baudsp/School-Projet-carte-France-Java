package domaine;
import java.util.ArrayList;
import java.util.List;

public class Itineraire {

	private List<Ville> villesItineraire = new ArrayList<Ville>();

	private int nbrVilles = 0;
	private int distanceGeometrique = 0;
	private int distanceRoute = 0;

	public Itineraire(Ville depart, Ville arrivee) {

		villesItineraire.add(depart);

		Ville curVille = depart;

		nbrVilles++;

		while (curVille.getCode() != arrivee.getCode()) {

			Ville temp = curVille.getPlusProcheVoisines(arrivee);
			
			distanceGeometrique += curVille.distanceGeometrique(temp);
			distanceRoute += curVille.distanceRoute(temp);
			
			curVille = temp;

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

	/**
	 * Renvoie la longueur geometrique de l'itineraire
	 */
	public int getDistanceGeometrique() {
		return distanceGeometrique;
	}

	public int getDistanceRoute() {
		return distanceRoute;
	}
}
