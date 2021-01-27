/**
 Classe representant une personne associee a son numero de telephone.

 @author Departement informatique
 @version 16.0128
 */
public class Personne {
	Mot nom;
	Mot prenom;
	int tel;

	public Personne(String prenom, String nom) {
		this.prenom= new Mot(prenom);
		this.nom = new Mot(nom);
	}

	/** Associe le numero de telephone. */
	public void setTel(int tel) {
		this.tel=tel;
	}

	/** Fournit le numero de telephone. */
	public int getTel() {
		return this.tel;
	}
	
	/** Teste l'égalite du nom et du prenom sous méthode polymorphe*/


	@Override
	public boolean equals(Object o) {
		if(o instanceof Personne) {
			Personne p = (Personne) o;
			return (this.prenom.equals(p.prenom) && this.nom.equals(p.nom));
		}
		return false;
	}

	public boolean estAvant(Personne p){
		if (!(this.nom.equals(p.nom))){
			return this.nom.compare(p.nom);
		} else if (!(this.prenom.equals(p.prenom))){
			return this.prenom.compare(p.prenom);
		}
		return false;
	}
	/**
     Produit une chaine indiquant la personne. 
     (Par exemple Jules CESAR)
	 */
	public String toString() {
		return ("nom:"+this.nom+" prenom:"+this.prenom);
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