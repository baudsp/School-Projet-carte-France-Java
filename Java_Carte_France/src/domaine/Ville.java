package domaine;
import java.util.ArrayList;
import java.util.List;

public class Ville {

	private String nom;
	private int code;
	private int x;
	private int y;
	private int nbVoisines = 0;
	private List<Ville> voisines = new ArrayList<>();
	private List<Integer> distanceVoisines = new ArrayList<>();

	public Ville(String nom, int code, int x, int y) {
		super();
		this.nom = nom;
		this.code = code;
		this.x = x;
		this.y = y;
	}

	public String getNom() {
		return nom;
	}

	public int getCode() {
		return code;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void ajouterVoisine(Ville v, int distance) {

		if (v == null) {
			return;
		}

		this.voisines.add(v);
		this.distanceVoisines.add(distance);

		this.nbVoisines++;
	}

	public List<Ville> getVoisines() {
		return this.voisines;
	}

	public int distanceGeometrique(Ville v) {
		return (int) Math.sqrt(Math.pow((v.x - this.x), 2)
				+ Math.pow((v.y - this.y), 2));
	}

	/**
	 * Recherche, dans les voisines de l'instance de cette classe, la ville la
	 * plus proche du paramètre v (en distance geopgraphique)
	 */
	public Ville getPlusProcheVoisines(Ville v) {
		int distAvecVoisine = 1100; // Distance maximale entre deux villes en
									// France à vol d'oiseau

		Ville plusProcheVoisine = null;

		for (int i = 0; i < nbVoisines; i++) {
			if (distAvecVoisine > voisines.get(i).distanceGeometrique(v)) {
				plusProcheVoisine = voisines.get(i);
				distAvecVoisine = voisines.get(i).distanceGeometrique(v);
			}
		}
		return plusProcheVoisine;
	}
	
	/**
	 * Retourne la distance entre cette ville et celle passée en paramètre, 
	 * si elle fait partie de ses voisines
	 * @param ville
	 * @return
	 */
	public int distanceRoute(Ville ville) {
		if (this.code == ville.getCode()) {
			return 0;
		}
		
		int indexOfVoisine = voisines.indexOf(ville);
		if (indexOfVoisine != -1) {
			return distanceVoisines.get(indexOfVoisine);
		}
		
		return -1;
	}

	public String toString() {
		return this.nom;
	}
}
