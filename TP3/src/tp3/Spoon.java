package tp3;

public class Spoon extends Ustensile {
    public static int LAVAGE_MAX=10;
    public static int SPOONS=0;
    public int spoon_id = SPOONS++;
    public boolean broken = false;

    /**
     * Methode pour laver un ustensile, specifique a chaque cas concret.
     * Par exemple, suivant sa nature il resistera plus ou moins longtemps avant de casser.
     */
    public void laver(){
        if (!(nbLavages>LAVAGE_MAX)){
            System.out.println("The Spoon is Broken");
            broken = true;
        } else {
            nbLavages++;
        }
    }

    public String toString() {
        // il sera etre utile de redefinir mieux cette methode dans les classes derivees
        return "Spoon (S"+spoon_id+"; NB_Lavages="+nbLavages +" broken?:"+broken+")";
    }
}
