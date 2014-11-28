package domaine;
import java.util.List;
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
	private Pays pays;

	public InfoCarte(Pays pays) {
		this.pays = pays;
	}

	/**
	 * Retourne des infos sur la ville ou l'itineraire
	 */
	public String toString() {
		if (itineraire != null) {
			List<Ville> villesItineraire = itineraire.getVillesItineraire();
			String res = "<html>";
			res += "<u>" + villesItineraire.get(0).getNom()
					+ " à "
					+ villesItineraire.get(villesItineraire.size() - 1)
							.getNom() + "</u>";
			res += "<br/>Longueur geometrique du trajet : "
					+ itineraire.getDistanceGeometrique() + " km";
			res += "<br/>Longueur par la route du trajet : "
					+ itineraire.getDistanceRoute() + " km";
			res += "<br/><u>Villes de l'itineraire :</u><br/>";
			res += listVille();
			res += "</html>";
			return res;
		} else if (villeSelectionnee != -1) {
			return pays.getVilleParCode(villeSelectionnee).getNom();
		} else {
			return "Rien";
		}
	}

	public int getVilleSelectionnee() {
		return villeSelectionnee;
	}

	public Itineraire getItineraire() {
		return itineraire;
	}

	public void setItineraire(Ville villeSelect1, Ville villeSelect2) {
		villeSelectionnee = -1;
		itineraire = new Itineraire(villeSelect1, villeSelect2);
		new ItineraireDijkstra(villeSelect1, villeSelect2);
		setChanged();
		notifyObservers(this.toString());
	}

	public void setVilleSelectionnee(Ville villeSelect) {
		itineraire = null;
		villeSelectionnee = villeSelect.getCode();

		setChanged();
		notifyObservers(this.toString());
	}

	private String listVille(){
		List<Ville> listVilles = itineraire.getVillesItineraire();
		
		String res = "";
		
		for (int i = 1; i<listVilles.size()-1; i++) {
			res += listVilles.get(i).toString()+"<br/>";
		}
		
		return res;
	}
}
