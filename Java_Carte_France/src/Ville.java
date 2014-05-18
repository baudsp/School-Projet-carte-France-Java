public class Ville {

	private String nom;
	private int code;
	private int x;
	private int y;
	private int nbVoisines = 0;
	private Ville[] voisines = new Ville[10];

	private int[] distanceVoisines = new int[10];

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

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	void ajouterVoisine(Ville v, int distance) {

		if (v == null) {
			return;
		}

		// Si il y a deja 10 villes voisines, on n'en ajoute pas
		if (nbVoisines == 10) {
			return;
		}

		this.voisines[nbVoisines] = v;
		this.distanceVoisines[nbVoisines] = distance;

		this.nbVoisines++;
	}

	public Ville[] getVoisines() {
		return voisines;
	}

	public int distance(Ville v) {
		return (int) Math.sqrt(Math.pow((v.x - this.x), 2)
				+ Math.pow((v.y - this.y), 2));
	}

	/**
	 * Recherche, dans les voisines de l'instance de cette classe, la ville la
	 * plus proche du paramètre v
	 */
	public Ville getPlusProcheVoisines(Ville v) {
		int distAvecVoisine = 1100; // Distance maximale entre deux villes en France à vol d'oiseau
		
		Ville plusProcheVoisine = null;
		
		for (int i =0; i < nbVoisines; i++) {
			if (distAvecVoisine > voisines[i].distance(v)) {
				plusProcheVoisine = voisines[i];
				distAvecVoisine = voisines[i].distance(v);
			}
		}
		return plusProcheVoisine;
	}
	
	public String toString() {
		return this.nom;
	}
}
