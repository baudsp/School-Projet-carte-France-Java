package domaine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Itineraire créé avec l'algorithme de Dijkstra, avec les villes tenant le rôle
 * des noeuds et les routes les arètes
 * <br/>
 * Basé sur : http://openclassrooms.com/courses/le-pathfinding-avec-dijkstra
 * 
 * @author Baudouin
 * 
 */
public class ItineraireDijkstra {

	private List<Ville> villesItineraire;
	private int nbrVilles = 0;
	private int distanceRoute = 0;
	
	/**
	 * Tableau des poids : associe chaque ville à un son poids
	 * calculé lors de la création de l'itinéraire
	 */
	private Map<Ville, Integer> poids = new HashMap<>();
	/**
	 * Contient les villes qui ont déjà été parcourues
	 */
	private List<Ville> villesParcourues = new ArrayList<>();
	/**
	 * Indique le prédécesseur de chaque ville
	 */
	private Map<Ville, Ville> predecesseurs = new TreeMap<>(new Comparator<Ville>() {

		@Override
		public int compare(Ville v1, Ville v2) {
			
			return v1.getNom().compareTo(v2.getNom());
		}
	});

	public ItineraireDijkstra(Ville depart, Ville arrivee) {
		
		poids.put(depart, 0);
		
		while (!getVillePoidsFaible().equals(arrivee)) {
			Ville curVille = getVilleNonParcouruePoidsFaible();

			// On dis que cette ville est parcourue
			villesParcourues.add(curVille);

			Iterator<Ville> villeVoisinesIterator = curVille.getVoisines().iterator();
			
			while (villeVoisinesIterator.hasNext()) {
				Ville curVoisine = villeVoisinesIterator.next();
				
				int poidsCurVoisine;
				if (poids.containsKey(curVoisine)) {
					poidsCurVoisine = poids.get(curVoisine);
				} else {
					poidsCurVoisine = -1;
				}
				if (!villesParcourues.contains(curVoisine)   //On vérifie qu'on n'est pas encore passé par cette ville
						&& (poidsCurVoisine > poids.get(curVille) // et que c'est plus intéressant de passer par là
								+ curVoisine.distanceRoute(curVille))
						|| poidsCurVoisine == -1) { // ou qu'on y est jamais passé
					
					poids.put(curVoisine, poids.get(curVille) + curVille.distanceRoute(curVoisine));
							
					predecesseurs.put(curVoisine, curVille);
				}
			}
		}
		
		Iterator<Ville> it = predecesseurs.keySet().iterator();
		while(it.hasNext()) {
			Ville v = it.next();
			System.out.println(v + "<=" + predecesseurs.get(v));
		}
		
		//createVilleItineraire();
	}

	public List<Ville> getVillesItineraire() {
		return villesItineraire;
	}

	public int getNbrVilles() {
		return nbrVilles;
	}

	public int getDistanceRoute() {
		return distanceRoute;
	}

	/**
	 * Renvoie la Ville dont le poids est le plus faible et qui n'a jamais été
	 * parcourue
	 */
	private Ville getVilleNonParcouruePoidsFaible() {
		Map<Ville, Integer> copiePoids = this.poids;
		
		Iterator<Ville> iteratorVillesParcourues = this.villesParcourues.iterator();
		while (iteratorVillesParcourues.hasNext()) {
			copiePoids.remove(iteratorVillesParcourues.next());
		}
		
		return getVillePoidsFaible(copiePoids);
	}

	/**
	 * Renvoie la Ville dont le poids est le plus faible
	 */
	private Ville getVillePoidsFaible() {
		return getVillePoidsFaible(poids);
	}
	
	/**
	 * Renvoie la Ville dont le poids est le plus faible
	 */
	private Ville getVillePoidsFaible(Map<Ville, Integer> poidsVille) {
		Ville villePoidsFaible = null;

		Iterator<Ville> villeIterator = poidsVille.keySet().iterator();

		int poidsMinimum = Integer.MAX_VALUE;

		while (villeIterator.hasNext()) {
			Ville temp = villeIterator.next();
			int curPoids = poidsVille.get(temp);
			if (curPoids  < poidsMinimum) { 
				poidsMinimum = curPoids;
				villePoidsFaible = temp;
			}
		}
		return villePoidsFaible;
	}


//	private Map<Ville, int[]> creerTableauPoids() {
//		Map<Ville, int[]> poids = new HashMap<>();
//
//		Iterator<Ville> villeIterator = pays.getVilles().iterator();
//
//		int[] poidsVille = { -1, -1 };
//
//		while (villeIterator.hasNext()) {
//			Ville curVille = villeIterator.next();
//			poids.put(curVille, poidsVille);
//		}
//
//		return poids;
//	}
//
//	private Map<Ville, Ville> creerTableauPredecesseurs() {
//		Map<Ville, Ville> predecesseurs = new HashMap<>();
//
//		Iterator<Ville> villeIterator = pays.getVilles().iterator();
//
//		while (villeIterator.hasNext()) {
//			predecesseurs.put(villeIterator.next(), null);
//		}
//
//		return predecesseurs;
//	}
	
}
