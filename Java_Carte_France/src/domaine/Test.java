package domaine;

public class Test {

	public static void main(String[] args) {
		//new FenetrePrincipale();
		
		Pays p = new Pays();
		
		p.charger();
		
		p.getVilleParNom("Bordeaux");
		
		new ItineraireDijkstra(p.getVilleParNom("Bordeaux"), 
				p.getVilleParNom("Strasbourg"));
	}
} 