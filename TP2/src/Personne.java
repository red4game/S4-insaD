/**
 Classe representant une personne associee a son numero de telephone.

 @author Departement informatique
 @version 16.0128
 */
public class Personne {


	// CONSTRUCTEUR A PROGRAMMER
	public Personne(String s, String dusse) {

	}

	/** Associe le numero de telephone. */
	public void setTel(int tel) {
		// A PROGRAMMER
	}

	/** Fournit le numero de telephone. */
	public int getTel() {
		return 112; // A PROGRAMMER
	}
	
	/** Teste l'égalite du nom et du prenom */
	// A PROGRAMMER

	/**
     Produit une chaine indiquant la personne. 
     (Par exemple Jules CESAR)
	 */
	public String toString() {
		return "X XXXXXXX"; // A PROGRAMMER
	}

	/**
     Tests.
	 */
	public static void main (String[] args) {

		Personne toto = new Personne("Jean-Claude","Dusse");

		System.out.println(toto);
		System.out.println(toto.toString());

		toto.setTel(4321);
		System.out.println(toto.getTel());

		System.out.println(toto.equals(new Personne("Jean-Claude","DUSSE")));
		System.out.println(toto.equals(new Personne("Jean-Claude","Musse")));
		//System.out.println(toto.estAvant(new Personne("Jean-Claude","Van Damme")));
	}
}