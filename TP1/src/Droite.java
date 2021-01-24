/**
 Classe représentant une droite du plan.

 @author Caisson Mathis - Groupe D
 @version 1.0
 */
public class Droite {
    Point p1;
    Point p2;

    /**
     * méthode coonstructeur d'une droite à partir de deux points
     * @param ptemp1 point origine pour construire la droite
     * @param ptemp2 point final pour construire la droite
     */
    public Droite(Point ptemp1, Point ptemp2){
        p1 = ptemp1;
        p2 = ptemp2;
    }

    /**
     * fonction pour vériifer que 2 droites soit paralleles
     * @param d droite avec laquelle il faut vérifier la parralélité
     * @return un boolean nous disant si les deux droites sont parralèles
     */
    public boolean parallele(Droite d){
        Vecteur v1 = new Vecteur(this.p1,this.p2);
        Vecteur v2 = new Vecteur(d.p1,d.p2);
        return v1.parallele(v2);
    }

    /**
     * fonction pour vériifer que 2 droites soit orthogonal
     * @param d droite avec laquelle il faut vérifier l'orthogonalité'
     * @return un boolean nous disant si les deux droites sont orthogonales
     */
    public boolean orthogonal(Droite d){
        Vecteur v1 = new Vecteur(this.p1,this.p2);
        Vecteur v2 = new Vecteur(d.p1,d.p2);
        return v1.orthogonal(v2);
    }

    /**
     * méthode pour voir si une droite contient un point
     * @param p_temp point à vérifier d'être contenu dans la droite
     * @return un boolean nous disant si le point est contenu dans la droite.
     */
    public boolean contient(Point p_temp){
        Vecteur v1 = new Vecteur(this.p1,this.p2);
        Vecteur v2 = new Vecteur(this.p1,p_temp);
        return v1.parallele(v2);
    }
}
