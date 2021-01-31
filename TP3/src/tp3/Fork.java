package tp3;

public class Fork extends Ustensile {
    public static int LAVAGE_MAX=20;
    public static int FORKS=0;
    public int fork_id = FORKS++;
    public boolean broken = false;

    /**
     * Methode pour laver un ustensile, specifique a chaque cas concret.
     * Par exemple, suivant sa nature il resistera plus ou moins longtemps avant de casser.
     */
    public void laver(){
        if (!(nbLavages>LAVAGE_MAX)){
            System.out.println("The Fork is Broken");
            broken = true;
        } else {
            nbLavages++;
        }
    }

    public String toString() {
        // il sera etre utile de redefinir mieux cette methode dans les classes derivees
        return "Fork (F"+fork_id+"; NB_Lavages="+nbLavages +" broken?:"+broken+")";
    }
}
