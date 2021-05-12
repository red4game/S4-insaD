import java.text.DecimalFormat;

/**
 * Compteur auxiliaire pour instrumenter le code :
 * comptage des additions, des multiplications et de la durée.
 * 
 * @version 20.0505
 */
public class Compteur {
	
	/** Nombre d'additions, mis à jour par l'utilisateur. */
	public int add;
	/** Nombre de multiplications, mis à jour par l'utilisateur. */
	public int mul;
	
	private long start; // temps en nanosecondes, calculé en interne
	
	
	/** Constructeur qui initialise tous les compteurs à zéro. */
	public Compteur() {raz();}
	
	/** Remise à zéro des compteurs. */
	public void raz() {
		mul = add = 0;
		start = System.nanoTime(); // Récupère l'horloge actuelle
	}

	/** Texte des valeurs en cours des compteurs. */
	public String toString() {
		long nanos = System.nanoTime()-start; // Durée écoulée selon l'horloge en nanosecondes
		
		// Affichage plus lisible quand beaucoup de chiffres 
		// (, et . fonction de la langue locale => espace et virgule en français)
		DecimalFormat f = new DecimalFormat("###,###,###,###.###");
		String txt = f.format(add) + " + et " + f.format(mul) + " * en ";
		
		// Produit une valeur dans l'unité la plus appropriée :
		// un entier en ns, µs ou ms, voire un décimal en s ou min
		if (nanos<1000)
			txt += nanos + " ns";
		else if (nanos/1000L < 1000)
			txt += nanos/1000L + " µs";
		else if (nanos/1000000L < 1000)
			txt += nanos/1000000L + " ms";
		else if (nanos/1000000000L < 60)
			txt += f.format(nanos/1000000000.0) + " s";
		else 
			txt += f.format(nanos/60000000000.0) + " min";
		
		return txt;
	}
	
	/** Affiche les valeurs en cours des compteurs, puis passe à la ligne.
	 @see #toString()
	 @return le compteur actuel pour permettre des appels en cascade.
	 */
	public Compteur println() {
		return this.println("");
	}
	
	/** Affiche un texte devant les valeurs en cours des compteurs.
	 @see #println()
	 @param txt Le texte à afficher avant les valeurs.
	 @return le compteur actuel pour permettre des appels en cascade.
	 */
	public Compteur println(String txt) {
		System.out.println(txt+this);
		return this;
	}
	
	// Tests.
	public static void main(String[] args) {
		Compteur cpt = new Compteur(); 	// Le compteur de temps est lancé
		System.out.println(cpt);
		
		cpt.raz();
		for (int i = 0; i<100000; i++) {
			System.out.println(i+1);
			cpt.add++; 					// On augmente le compteur d'additions
		}
		cpt.println().raz();			// On affiche tout, puis on remet tout à zéro
		
		for (int i = 0; i<1000; i++) {
			System.out.print(i*i + " ");
			cpt.mul++;					// On augmente le compteur de multiplications
		}
		cpt.println("\nNouvelles valeurs des compteurs : ").raz();	// On affiche tout, avec du texte devant
	}
}
