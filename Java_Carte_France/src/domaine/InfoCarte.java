package domaine;
import java.util.Observable;

/**
 * Sert à contenir des informations sur la ville ou l'itineraire affiché sur la
 * carte
 * 
 * @author Baudouin
 * 
 */
public class InfoCarte extends Observable {
	private int villeSelectionnee = -1;
	private Itineraire itineraire = null;
	private ItineraireDijkstra itDijkstra = null;
	private Pays pays;

	public InfoCarte(Pays pays) {
		this.pays = pays;
	}

//	/**
//	 * Retourne des infos sur la ville ou l'itineraire
//	 */
//	public String toString() {
//		if (itineraire != null) {
//			List<Ville> villesItineraire = itineraire.getVillesItineraire();
//			String res = "<html>";
//			res += "<u>" + villesItineraire.get(0).getNom()
//					+ " à "
//					+ villesItineraire.get(villesItineraire.size() - 1)
//					.getNom() + "</u>";
//			res += "<br/>";
//			res += "<u>Algorithme idiot :</u>";
//			
//			res += "<br/>Longueur du trajet : "
//					+ itineraire.getDistanceRoute() + " km  ";
//			res += "<br/><u>Villes de l'itineraire :</u><br/>";
//			res += listVille();
//			
//			res += "<u>Algorithme de Disjkstra :</u>";
//			res += "<br/>Longueur du trajet : "
//					+ itDijkstra.getDistanceRoute() + " km  ";
//			res += "<br/><u>Villes de l'itineraire :</u><br/>";
//			res += listVilleDijkstra();
//			
//			res += "</html>";
//			
//			
//			
//			return res;
//		} else if (villeSelectionnee != -1) {
//			return pays.getVilleParCode(villeSelectionnee).getNom();
//		} else {
//			return "Rien";
//		}
//	}

	public Ville getVilleSelectionnee() {
		return pays.getVilleParCode(villeSelectionnee);
	}

	public Itineraire getItineraireIdiot() {
		return itineraire;
	}

	public void setItineraire(Ville villeSelect1, Ville villeSelect2) {
		villeSelectionnee = -1;
		itineraire = new Itineraire(villeSelect1, villeSelect2);
		itDijkstra = new ItineraireDijkstra(villeSelect1, villeSelect2);
		setChanged();
		notifyObservers(this.toString());
	}

	public void setVilleSelectionnee(Ville villeSelect) {
		itineraire = null;
		villeSelectionnee = villeSelect.getCode();

		setChanged();
		notifyObservers(this.toString());
	}

	public ItineraireDijkstra getItDijkstra() {
		return itDijkstra;
	}
}
