public class Annuaire extends EnsembleP {

    public Annuaire(){
        super();
    }

    @Override
    public String toString() {
        String res = "ANNUAIRE :\n";
        for (int i = 0; i < this.cardinal; i++) {
            int ntel = this.elements[i].getTel();
            res += ("\n\t- [" + (ntel > 0 ? ntel : "--") + "] " + this.elements[i]);
        }
        return res;
    }

    public int tel(String prenom,String nom){
        Personne np = new Personne(prenom,nom);
        for (int i=0;i<cardinal;i++){
            if (elements[i].equals(np)){
                return elements[i].getTel();
            }
        }
        return -1;
    }

    public Personne telinverse(int tel){
        for (int i=0;i<cardinal;i++){
            if (elements[i].getTel()==tel){
                Personne np = new Personne(elements[i].prenom.toString(),elements[i].nom.toString());
                return np;
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
