package domaine;

import java.util.ArrayList;
import java.util.List;

public abstract class InterfaceItineraire {
	protected List<Ville> villesItineraire = new ArrayList<Ville>();
	protected int nbrVilles = 0;
	protected int distanceRoute = 0;
	
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
	public int getDistanceRoute() {
		return distanceRoute;
	}
}
