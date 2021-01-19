public class Vecteur {
    Double dx;
    Double dy;

    public Vecteur(double x,double y){
        dx = x;
        dy = y;
    }
    public Vecteur(Point p1,Point p2){
        dx = p2.x() - p1.x();
        dy = p2.y() - p1.y();
    }

    public double produitScalaire(Vecteur v){
        return (this.dx*v.dx +this.dy*dy);
    }

    public double determinant(Vecteur v){
        return (this.dx*v.dy - v.dx*this.dy);
    }

    public boolean parallele(Vecteur v){
        if (determinant(v)==0){
            return true;
        }
        return false;
    }
    public boolean orthogonal(Vecteur v){
        if (this.produitScalaire(v) == 0){
            return true;
        }
        return false;
    }
}
