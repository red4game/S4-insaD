/**
   Classe représentant un point du plan par ses deux coordonnées réelles.
   
   @author et autre indications à intégrer ici pour identifier votre TP (cf. sujet fourni).
*/
public class Point {

  private double x;		// Abscisse du point
  private double y;		// Ordonnée du point

  /** 
      Méthode d'accès à l'abscisse du point.
      @return l'abscisse du point.
  */
  public double x() { return x; } 

  /** 
      Méthode d'accès à l'ordonnée du point.
      #return l'ordonnée du point.
  */
  public double y() { return y; }

  /** 
      Constructeur de point à partir de coordonnées x et y.
   */
  public Point(double vx, double vy) {
    x = vx;
    y = vy;
  }

  public Point(Point p){
    x = p.x;
    y = p.y;
  }

  public Point(){
    x = 1;
    y = 1;
  }

  /** 
      Déplacement du point.
  */
  public void deplace(double dx, double dy) {
    this.x+=dx;
    this.y+=dy;
  }

  public String toString(){
    return("point : ("+ this.x +","+ this.y +")" );
  }

  public Boolean equals(Point p){
    if (p.x == this.x && p.y == this.y){
      return true;
    }
    return false;
  }

  public Point barycentre(double a, double b, Point Y){
      Point p_temp = new Point((a*this.x + b*Y.x)/(a+b),(a*this.y + b*Y.y)/(a+b));
      return(p_temp);
  }

  public Point milieu(Point Y){
    Point p_temp = new Point((this.x +Y.x)/2,(this.y +Y.y)/2);
    return(p_temp);
  }


  public Point copie(){
    Point p_temp = new Point(this.x,this.y);
    return p_temp;
  }
}
