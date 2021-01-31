package tp3;

/**
 * Classe abstraite Ustensile,
 * dont on derive tout ustensile concret (assiette, verre, etc.)
 */
public abstract class Ustensile {

	protected int nbLavages;

	public Ustensile() {
		nbLavages = 0;
	}

	/** 
	 * Methode pour laver un ustensile, specifique a chaque cas concret.
	 * Par exemple, suivant sa nature il resistera plus ou moins longtemps avant de casser.
	 */
	public abstract void laver(); 

	/** 
	 * Methode pour afficher un ustensile.
	 */
	public String toString() { 
		// il sera etre utile de redefinir mieux cette methode dans les classes derivees
		return "ustensile";
	}

}
