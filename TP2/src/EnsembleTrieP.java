/**
 Classe representant un ensemble de personnes.

 @author Departement informatique
 @version 16.0128
 */
public class EnsembleTrieP extends EnsembleP {

    /**
     * Constructor with no parameter
     */
    public EnsembleTrieP() {
        super();
    }

    public EnsembleTrieP(EnsembleTrieP other) {
        super(other);
    }

    /**
     * Cardinality of the set
     *
     * @return card Cardinality
     */
    public int card()	{ return this.cardinal; }

    /**
     * Check whether or not we contain a person
     *
     * @param p A person
     * @return contains Whether or not we contain said person
     */
    public boolean contient(Personne p) {
        if (this.cardinal == 0)
            return false;
        int up_bound = 0;
        int low_bound = this.cardinal-1;
        while (up_bound - low_bound > 0) {
            int mid_bound = (up_bound + low_bound)/2;
            Personne np = this.elements[mid_bound];

            if (np.estAvant(p)) {
                low_bound = mid_bound;
            } else {
                up_bound = mid_bound;
            }
        }
        for (int i = low_bound; i <= up_bound; i++) {
            if (this.elements[i].equals(p))
                return true;
        }
        return false;
    }

    /**
     * Add a person to the set
     *
     * @param p A new person
     */
    public void ajouter(Personne p) {
        // If we already contain the person, skip
        if (!this.contient(p)) {
            //System.out.println("ADDING TO THE LIST : " + this);
            // Create new list. This is absurdly inefficient.
            Personne[] ntab = new Personne[this.cardinal + 1];
            int c = 0;
            while (c < this.cardinal) {
                Personne np = this.elements[c];
                if (np != null && np.estAvant(p)) {
                    ntab[c] = this.elements[c];
                    c++;
                } else {
                    break;
                }
            }
            ntab[c] = p;
            while (c < this.cardinal) {
                ntab[c+1] = this.elements[c];
                c++;
            }
            this.elements = ntab;
            this.cardinal++;
        }
    }

    /**
     * Remove a person from the set
     *
     * @param p A person
     */
    public void oter(Personne p) {
        if (!this.contient(p))
            return;
        // Step 1 : locate the person
        int up_bound = this.cardinal-1;
        int low_bound = 0;
        while (up_bound - low_bound > 1) {
            int mid_bound = (up_bound + low_bound)/2;
            Personne mid_p = this.elements[mid_bound];

            if (mid_p.estAvant(p)) {
                low_bound = mid_bound;
            } else {
                up_bound = mid_bound;
            }
        }

        int index = up_bound;
        for (int k = low_bound; k <= up_bound; k++) {
            if (this.elements[k].equals(p)) {
                index = k;
                break;
            }
        }

        // Step 2 : Create a new table and copy everything up to that index
        Personne[] ntab = new Personne[this.cardinal-1];
        for (int c = 0; c < index; c++)
            ntab[c] = this.elements[c];

        // Step 3 : Copy everything at index+1 onwards
        for (int c = index+1; c < this.cardinal; c++)
            ntab[c-1] = this.elements[c];

        // Step 4 : Copy the table and lower the cardinal
        this.elements = ntab;
        this.cardinal--;
    }

    /**
     * Le main
     *
     * @param arg Les paramètres
     */
    public static void main(String[] arg) {
        EnsembleTrieP k = new EnsembleTrieP();
        EnsembleTrieP u = new EnsembleTrieP();
        Personne a	= new Personne("Amélie", "P");
        Personne b	= new Personne("Lux", "B");
        Personne c	= new Personne("Antony", "C");
        k.ajouter(b);
        k.ajouter(a);
        u.ajouter(c);
        u.ajouter(c);
        //k.oter(c);

        System.out.println(k.union(u));
    }
}