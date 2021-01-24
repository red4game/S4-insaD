/**
 Classe représentant un vecteur du plan.

 @author Caisson Mathis - Groupe D
 @version 1.0
 */
public class Vecteur {
    Double dx;
    Double dy;

    /**
     * méthode de constructeur d'un vecteur à partir de coordonées
     * @param x coordonée x du vecteur
     * @param y coordonée y du vecteur
     */
    public Vecteur(double x,double y){
        dx = x;
        dy = y;
    }

    /**
     * méthode de constructeur d'un vecteur à partir de deux points
     * @param p1 point d'origine du vecteur
     * @param p2 point final du vecteur
     */
    public Vecteur(Point p1,Point p2){
        dx = p2.x() - p1.x();
        dy = p2.y() - p1.y();
    }

    /**
     * méthode de calcul du produit scalaire de 2 vecteur
     * @param v vecteur mis en paramètre
     * @return produit scalaire des deux vecteurs
     */
    public double produitScalaire(Vecteur v){
        return (this.dx*v.dx +this.dy*dy);
    }

    /**
     * méthode du calcul du determinant de 2 vecteurs
     * @param v vecteur mis en paramètre
     * @return determinant des deux vecteurs
     */
    public double determinant(Vecteur v){
        return (this.dx*v.dy - v.dx*this.dy);
    }

    /**
     * méthode de vérification de parralélité de 2 vecteurs
     * @param v vecteur mis en paramètre
     * @return boolean de verification de parralélité des 2 vecteurs
     */
    public boolean parallele(Vecteur v){
        if (determinant(v)==0){
            return true;
        }
        return false;
    }

    /**
     * méthode de vérification d'orthogonalité de 2 vecteurs
     * @param v vecteur mis en paramètre
     * @return boolean de verification d'orthogonalité des 2 vecteurs
     */
    public boolean orthogonal(Vecteur v){
        if (this.produitScalaire(v) == 0){
            return true;
        }
        return false;
    }
}
