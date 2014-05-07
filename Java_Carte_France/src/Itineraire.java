import java.util.ArrayList;
import java.util.List;


public class Itineraire {
	
	private List<Ville> villesItineraire = new ArrayList<Ville>();
	private int nbrVilles = 0;
	
	public Itineraire(Ville depart,Ville arrivee) {
		
		villesItineraire.add(depart);
		
		Ville curVille = depart;
		
		while (curVille.getCode() != arrivee.getCode()) {
			
			nbrVilles++;
			
			curVille = curVille.getPlusProcheVoisines(arrivee);
			
			villesItineraire.add(curVille);			
		}
	}
	
	public List<Ville> getVillesItineraire() {
		return villesItineraire;
	}
	public int getNbrVilles() {
		return nbrVilles;
	}
	
	public String toString(){
		String r = "";
		
		for (Ville v : villesItineraire) {
			r+= v.getNom() + ", ";
		}
		return r;
	}
	
	/**
	 *	Verifie si la route entre les deux villes fait partie de cet itinéraire
	 */
	public boolean checkVillesItineraire(Ville V1, Ville V2) {
		//TODO
	}
	
}
