/**
 Classe representant un mot.
 
 @author Departement informatique
 @version 16.0128
 */
class Mot {

	/** Constructeur */
	public Mot(String texte){
		// A PROGRAMMER
	}
	
	/** Teste l'egalite, insensible a la casse */
	// A PROGRAMMER

	/** Retourne une version String du mot */
	public String toString() {
        return "X"; // A PROGRAMMER
	}

	/**
    Tests.
	 */
	public static void main(String[] args) {
		Mot y = new Mot("Yann");
		
		System.out.println("egal a YANN ? " + y.equals(new Mot("YANN")));
        // A PROGRAMMER : imaginer des tests supplementaires...
	}
}
