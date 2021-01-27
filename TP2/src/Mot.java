/**
 Classe representant un mot.
 
 @author Departement informatique
 @version 16.0128
 */
class Mot {
	String texte;
	/** Constructeur */
	public Mot(String texte){
		this.texte=texte;
	}


	/** Teste l'egalite, insensible a la casse */
	@Override
	public boolean equals(Object o) {
		if(o instanceof Mot) {
			Mot m = (Mot) o;
			return (this.texte == m.texte) || (this.texte != null && this.texte.toLowerCase().equals(m.texte.toLowerCase()));
		}
		return false;
	}

	/* méthode non polymorphe
	public boolean equals(Mot monmot) {
		if (this.texte.toLowerCase().equals(monmot.texte.toLowerCase())){
			return true;
		}
		return false;
	}
	 */

	public boolean compare(Mot m){
		if (this.texte.compareTo(m.texte)<0){
			return true;
		}
		return false;
	}

	/** Retourne une version String du mot */
	public String toString() {
        return texte;
	}

	/**
    Tests.
	 */
	public static void main(String[] args) {
		Mot y = new Mot("Yann");
		
		System.out.println("egal a YANN ? " + y.equals(new Mot("YANN")));

	}
}
