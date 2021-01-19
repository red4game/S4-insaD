public class Droite {
    Point p1;
    Point p2;

    public Droite(Point ptemp1, Point ptemp2){
        p1 = ptemp1;
        p2 = ptemp2;
    }
    public boolean parallele(Droite d){
        Vecteur v1 = new Vecteur(this.p1,this.p2);
        Vecteur v2 = new Vecteur(d.p1,d.p2);
        return v1.parallele(v2);
    }

    public boolean orthogonal(Droite d){
        Vecteur v1 = new Vecteur(this.p1,this.p2);
        Vecteur v2 = new Vecteur(d.p1,d.p2);
        return v1.orthogonal(v2);
    }

    public boolean contient(Point p_temp){
        Vecteur v1 = new Vecteur(this.p1,this.p2);
        Vecteur v2 = new Vecteur(this.p1,p_temp);
        return v1.parallele(v2);
    }
}
