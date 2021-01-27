import java.util.Arrays;

/**
 Classe representant un ensemble de personnes.
 
 @author Departement informatique
 @version 16.0128
 */
public class EnsembleP {
	static final int CAP_INIT=0;
	int cardinal=CAP_INIT;
	Personne[] elements;

    public EnsembleP(){
	    elements = new Personne[cardinal];
    }

    public EnsembleP(EnsembleP another){
        this.elements = new Personne[another.cardinal];
        for (int i=0;i<another.cardinal;i++){
            this.elements[i]=another.elements[i];
        }
    }

    public int card(){
	    return this.cardinal;
    }

    public boolean contient(Personne personne){
        for (int i=0;i<this.cardinal;i++){
            if (elements[i].equals(personne)) {
                return true;
            }
        }
        return false;
    }

    public void ajouter(Personne personne){
        if (!this.contient(personne)) {
            Personne[] MyTab = new Personne[this.cardinal + 1];
            for (int i = 0; i < this.cardinal; i++) {
                MyTab[i] = this.elements[i];
            }
            MyTab[this.cardinal] = personne;
            elements = MyTab;
            this.cardinal++;
        } else {
            System.out.println("cette personne est déjà dans la liste");
        }
	}

    public void oter(Personne personne){
        if (this.contient(personne)) {
            Personne[] MyTab = new Personne[this.cardinal - 1];
            int offset = 0;
            for (int i = 0; i < this.cardinal - 1; i++) {
                if (this.elements[i] == personne) {
                    offset = 1;
                }
                MyTab[i] = this.elements[i + offset];
            }
            this.elements=MyTab;
            cardinal--;
        } else{
            System.out.println("cette personne n'est pas dans la liste");
        }
    }



    public EnsembleP union(EnsembleP E){
	    EnsembleP Ef = new EnsembleP();
        for (int i=0;i<E.cardinal;i++){
            Ef.ajouter(E.elements[i]);
        }
        for (int i=0;i<this.cardinal;i++){
            if (!(Ef.contient(this.elements[i]))){
                Ef.ajouter(this.elements[i]);
            }
        }
        return Ef;
    }

    public EnsembleP inter(EnsembleP E){
        EnsembleP Ef = new EnsembleP();
        for (int i=0;i<this.cardinal;i++) {
            if (E.contient(this.elements[i])){
                Ef.ajouter(this.elements[i]);
            }
        }
        return Ef;
    }

    @Override
    public String toString() {
        return "EnsembleP{" +
                "cardinal=" + cardinal +
                ", elements=" + Arrays.toString(elements) + //améliorer pour pas afficher les null
                '}';
    }

    public static void main(String[] args) {
        EnsembleP E1 = new EnsembleP();
        Personne toto = new Personne("Jean-Claude","Dusse");
        Personne titi = new Personne("Jean-Eude","Fus");
        Personne tata = new Personne("andreas","honet");
        E1.ajouter(toto);
        E1.ajouter(titi);
        System.out.println(E1);
        System.out.println(E1.card());
        E1.oter(toto);
        System.out.println(E1);
        E1.ajouter(toto);
        System.out.println(E1);

        EnsembleP E2 = new EnsembleP();
        System.out.println(E2);
        E2.ajouter(titi);
        E2.ajouter(tata);
        System.out.println(E1.union(E2));
        System.out.println(E1.inter(E2));


    }
}
