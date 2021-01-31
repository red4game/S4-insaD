package tp3;

public class Plate extends Ustensile {
    public static int LAVAGE_MAX=100;
    public static int PLATES=0;
    public int plate_id = PLATES++;
    public boolean broken = false;

    /**
     * Methode pour laver un ustensile, specifique a chaque cas concret.
     * Par exemple, suivant sa nature il resistera plus ou moins longtemps avant de casser.
     */
    public void laver(){
        if (!(nbLavages>LAVAGE_MAX)){
            System.out.println("The Plate is Broken");
            broken = true;
        } else {
            nbLavages++;
        }
    }

    public String toString() {
        // il sera etre utile de redefinir mieux cette methode dans les classes derivees
        return "Plate (P"+plate_id+"; NB_Lavages="+nbLavages +" broken?:"+broken+")";
    }
}
