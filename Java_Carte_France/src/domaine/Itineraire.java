package domaine;

public class Itineraire extends InterfaceItineraire {

	private int distanceGeometrique = 0;

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

	/**
	 * Renvoie la longueur geometrique de l'itineraire
	 */
	public int getDistanceGeometrique() {
		return distanceGeometrique;
	}
}
