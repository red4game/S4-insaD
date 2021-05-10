import java.awt.*;

/**
 * 
 * 
 * classe d�crivant une facette, i.e. un triplet de points
 */
public class Facette {

	private Point p1;
	private Point p2;
	private Point p3;

	/**
	 * Barycentre de la facette (instance de la classe Point initialis� par la
	 * m�thode computeBarycentre
	 */
	private Point barycentre;

	/**
	 * Distance de la facette � un point d'observation. Initialement inconnue �
	 * la cr�ation de la facette initialis�e par la m�thode computeDistance
	 */
	private double distance; // distance du barycentre au point d'observation

	/**
	 * couleur de la facette (en niveau de gris) Cette couleur a des composantes
	 * RVB de la forme (x,x,x) o� x est un entier entre 50 et 255
	 */
	private Color col; // couleur de la facette

	/**
	 * Construit une facette � partir de 3 points
	 * 
	 * @param cp1
	 *            : premier point
	 * @param cp2
	 *            : deuxi�me point
	 * @param cp3
	 *            : troisi�me point Par d�faut, la couleur de la facette est le
	 *            vert, ce qui permettra de voir si une facette a recu une
	 *            couleur A la cr�artion de la facette, comme le point
	 *            d'observation n'est pas connu la distance est a priori de 0
	 */
	public Facette(Point cp1, Point cp2, Point cp3) {

		this.p1 = cp1;
		this.p2 = cp2;
		this.p3 = cp3;
		this.col = Color.green;
		this.distance = 0;
	}

	/**
	 * Construit une facette � partir d'une chaine de caract�res contenant une
	 * liste de coordonn�es
	 * 
	 * @param s
	 *            chaine de caract�re contenant 9 coordonn�es, permettant de
	 *            cr��er 3 points, s�par�es par " "
	 */
	public Facette(String s) {

		String segments[] = s.split(" ");
		if (segments.length < 9) {
			System.out.println("Erreur when reading a facette");
			p1 = new Point(0, 0, 0);
			p2 = new Point(0, 0, 0);
			p3 = new Point(0, 0, 0);
			col = Color.green;
			this.distance = 0;

		} else {
			System.out.println("9 coordinates read, ok");
			p1 = new Point(Integer.parseInt(segments[0]),
					Integer.parseInt(segments[1]),
					Integer.parseInt(segments[2]));

			p2 = new Point(Integer.parseInt(segments[3]),
					Integer.parseInt(segments[4]),
					Integer.parseInt(segments[5]));

			p3 = new Point(Integer.parseInt(segments[6]),
					Integer.parseInt(segments[7]),
					Integer.parseInt(segments[8]));
			col = new Color(200, 200, 200);
			this.distance = 0;

		}

	}

	/**
	 * Renvoie une nouvelle facette, copie de l'objet courant
	 * 
	 * @return Une facette
	 */
	public Facette copy() {
		return new Facette(this.p1, this.p2, this.p3);
	}

	/**
	 * Met � jour la distance de la facette au point pass� en param�tre
	 * 
	 * @param p
	 *            point � partir duquel on calcule la distance
	 */
	public void setDistance(Point p) {

		double d = this.distance(p);
		System.out.println(d);
		this.distance = d;

	}

	/**
	 * calcule la distance � un point p pass� en param�tre
	 * 
	 * @param p
	 *            point d'observation
	 * @return double : distance au poitnd'observation La distance calcul�e est
	 *         la distance entre le point p et le barycentre de la facette
	 * 
	 */
	public double distance(Point p) {
		return p.distance(barycentre);
	}

	public double getDistance() {
		return distance;
	}

	/**
	 * fixe la couleur de la facette en niveau de gris
	 * 
	 * @param level
	 *            : entier, indiquant l'intensit� du niveau de gris
	 */
	public void setColor(int level) {

		this.col = new Color(level, level, level);
	}

	/**
	 * Calcule un barycentre pour la facette
	 */
	public void barycentre() {
		double xG = (p1.x() + p2.x() +p3.x())/3;
		double yG = (p1.y() + p2.y() +p3.y())/3;
		double zG = (p1.z() + p2.z() +p3.z())/3;
		Point Bary = new Point(xG,yG,zG);
		barycentre = Bary;
	}

	/**
	 * renvoie vrai si la facette courante est � une distance plus petite qu'une
	 * facette pass�e en param�tre
	 * */
	public boolean estAvant(Facette f) {
		if (this.distance<=f.distance){
			return true;
		}
		return false;
	}

	/**
	 * Rotation dans le plan XY
	 * 
	 * @param org
	 *            : centre de la rotation
	 * @param angle
	 *            : angle (en degr�s)
	 */
	public void rotation(Point org, double angle) {

		this.p1.rotation(org, angle);
		this.p2.rotation(org, angle);
		this.p3.rotation(org, angle);

	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	public Point getP3() {
		return p3;
	}

	public void setP3(Point p3) {
		this.p3 = p3;
	}

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

}// fin de classe facette

