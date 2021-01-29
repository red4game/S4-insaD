/**
   Classe représentant un point du plan par ses deux coordonnées réelles.
   
   @author Caisson Mathis - Groupe D
   @version 1.0
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
      @return l'ordonnée du point.
  */
  public double y() { return y; }

  /**
   * Constructeur de point à partir de coordonnées x et y.
   * @param vx coordonée x du point
   * @param vy coordonée y du point
   */
  public Point(double vx, double vy) {
    x = vx;
    y = vy;
  }

  /**
   * constructeur de point à partir d'un autre point
   * @param p point qui va servir à créer le point courant
   */
  public Point(Point p){
    x = p.x;
    y = p.y;
  }

  /**
   * constructeur d'un point de coordonées (1,1)
   */
  public Point(){
    x = 1;
    y = 1;
  }

  /**
   * Déplacement du point.
   * @param dx déplacement de la coordonée x du point
   * @param dy déplacement de la coordonée y du point
   */
  public void deplace(double dx, double dy) {
    this.x+=dx;
    this.y+=dy;
  }

  /**
   * méthode qui rend le string de l'objet point
   * @return le string du point
   */
  @Override
  public String toString(){
    return("point : ("+ this.x +","+ this.y +")" );
  }

  /**
   * sert à savoir si 2 points sont les mêmes en comparant les coordonées
   * @param p autre point pour comparer l'égalité
   * @return l'égalité entre 2 point
   */
  public Boolean equals(Point p){
    if (p.x == this.x && p.y == this.y){
      return true;
    }
    return false;
  }

  /**
   * Crée un point qui est le barycentre du point courant et du point passé en paramètre affectés des
   * coefficients a (pour le point courant) et b (pour le point passé en paramètre).
   * @param a coefficient barycentre point courant
   * @param b coefficient barycentre point fourni en paramètre
   * @param Y autre point pour le barycentre
   * @return barycentre des deux points
   */
  public Point barycentre(double a, double b, Point Y){
      Point p_temp = new Point((a*this.x + b*Y.x)/(a+b),(a*this.y + b*Y.y)/(a+b));
      return(p_temp);
  }

  /**
   *
   * @param Y autre point passé en paramètre
   * @return point milieu des 2 points, courant et passé en paramètre
   */
  public Point milieu(Point Y){
    Point p_temp = barycentre(1,1,Y);
    return(p_temp);
  }

  /**
   *
   * @return copie d'un point
   */
  public Point copie(){
    Point p_temp = new Point(this.x,this.y);
    return p_temp;
  }
}
