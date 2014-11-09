import java.util.List;
import java.util.Observable;

/**
 * Sert à contenir des informations sur la ville ou l'itineraire affiché sur la carte
 * @author Baudouin
 *
 */
public class InfoCarte extends Observable {
	int villeSelectionnee = -1;
	Itineraire itineraire = null;
	private Monde monde;

	public InfoCarte(Monde monde) {
		this.monde = monde;
	}

	/**
	 * Retourne des infos sur la ville ou l'itineraire
	 */
	public String toString() {
		if (itineraire != null) {
			List<Ville> villesItineraire = itineraire.getVillesItineraire();
			String res = villesItineraire.get(0).getNom()
					+ " à "
					+ villesItineraire.get(villesItineraire.size() - 1)
							.getNom();
			res += "\nLongueur du trajet : "
					+ itineraire.getDistanceGeometrique();
			return res;
		} else if (villeSelectionnee != -1) {
			return monde.getVilleParCode(villeSelectionnee).getNom();
		} else {
			return "Rien";
		}
	}

	public void setItineraire(Ville villeSelect1, Ville villeSelect2) {
		villeSelectionnee = -1;
		itineraire = new Itineraire(villeSelect1, villeSelect2);
		
		setChanged();
		notifyObservers(this.toString());
	}

	public void setVilleSelectionnee(Ville villeSelect) {
		itineraire = null;
		villeSelectionnee = villeSelect.getCode();
		
		setChanged();
		notifyObservers(this.toString());		
	}
}
