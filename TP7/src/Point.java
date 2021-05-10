/**
 * Classe Point : point en 3D : 3 coordonn�es (double) en X,Y,Z
 */
import java.util.Random;
public class Point {
    
	private double x;
	private double y;
	private double z;

	public Point(double cx, double cy, double cz) {
		this.x = cx;
		this.y = cy;
		this.z = cz;

	}

	public Point() {
		this.x = 0;
		this.y = 0;
		this.z = 0;

	}

	/**
	 * change les coordonn�es d'un point de mani�re al�atoire
	 */
	public static int AMPLITUDE = 250;
	public void randomCoord() {
		Random rand = new Random(242584615);
		// For each coordinate decide if they change or not
		this.x += (int)(Math.random() * 2 * AMPLITUDE) + 100;
		this.y += (int)(Math.random() * 2 * AMPLITUDE) + 100;
		this.z += (int)(Math.random() * 2 * AMPLITUDE) - AMPLITUDE;
	}

	public double x() {
		return this.x;
	}

	public double y() {
		return this.y;
	}

	public double z() {
		return this.z;
	}

	public void setx(double vx) {
		this.x = vx;
	}

	public void sety(double vy) {
		this.y = vy;
	}

	public void setz(double vz) {
		this.z = vz;
	}

	/**
	 * Calcule la distance entre le point courant et un point entr� en param�tre
	 * @param pCompare
	 *            : point auquel comparer le point courant
	 * @return : double = distance du point courant au point pass� en param�tre
	 * 
	 */
	public double distance(Point pCompare) {
		return Math.sqrt(((this.x- pCompare.x())*(this.x- pCompare.x())) + ((this.y- pCompare.y())*(this.y- pCompare.y())) +((this.z- pCompare.z())*(this.z- pCompare.z())));
	}

	/**
	 * rotation : du point autour d'un point central dans le plan XY
	 * @param org point autour duquel se fait la rotation
	 * @param angle angle de la rotation en degr�s
	 */
	public void rotation(Point org, double angle) {

		double radang = Math.toRadians(angle);

		double dx = this.x - org.x();
		double dy = this.y - org.y();

		double xp = org.x + dx * Math.cos(radang) + dy * Math.sin(radang);
		double yp = org.y - dx * Math.sin(radang) + dy * Math.cos(radang);

		this.x = xp;
		this.y = yp;
	}

	/**
	 * rotation : du point autour d'un point central dans le plan XZ
	 * 
	 * @param org
	 *            : point autour duquel se fait la rotation
	 * @param angle
	 *            : angle de la rotation en degr�s
	 */
	public void rotationXZ(Point org, double angle) {

		double radang = Math.toRadians(angle);
		System.out.println("RotationXZ:" + angle);
		double dx = this.x - org.x();
		double dz = this.z - org.z();

		double xp = org.x + dx * Math.cos(radang) + dz * Math.sin(radang);
		double zp = org.z - dx * Math.sin(radang) + dz * Math.cos(radang);

		this.x = xp;
		this.z = zp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Point)) {
			return false;
		}
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
			return false;
		}
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
			return false;
		}
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z)) {
			return false;
		}
		return true;
	}

}