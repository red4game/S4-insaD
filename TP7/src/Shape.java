import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Shape : classe impl�mentant la notion de forme une shape est principalement
 * un ensemble de facettes
 */
public class Shape {
	/**
	 * Nombre Maximal de facettes
	 */
	private static final int MAX_FAC = 2000;
	/**
	 * Table contenant des facettes
	 */
	private Facette[] tab;
	/**
	 * Current number of facettes in the table
	 */
	private int nbfac;

	// Constructeurs

	/**
	 * creates a table of at most MAX_FAC facettes The table is originally empty
	 */
	public Shape() {
		tab = new Facette[MAX_FAC];
		nbfac = 0;
	}

	/**
	 * ree une table de MAX_FAC facettes remplie de nbf facettes al�atoires
	 */
	public Shape(int nbf) {
		this.nbfac=0;
		this.tab = new Facette[nbf];
		random(nbf);
	}

	/**
	 * renvoie la facette no i
	 */
	public Facette getFacette(int i) {
		return tab[i];
	}

	/**
	 * @return  renvoie le nombre de facettes
	 */
	public int nbfac() {
		return nbfac;

	}

	/**
	 * Ajoute une facette � la forme
	 * @param fac est la facette ajout�e
	 */
	public void add(Facette fac) {
		tab[nbfac] = fac;
		nbfac++;
	}

	/**
	 * Genere nb facettes dans la forme de mani�re al�atoire
	 * @param nb est le nombre de facettes g�n�r�es
	 */
	public void random(int nb) {
		for(int i =0;i<nb;i++){
			Point p1 = new Point(0,0,0);
			Point p2 = new Point(0,0,0);
			Point p3 = new Point(0,0,0);
			p1.randomCoord();
			p2.randomCoord();
			p3.randomCoord();
			add(new Facette(p1,p2,p3));
		}
	}

	/**
	 * charge une description donn�e comme une liste de facette dans un fichier
	 * dont le nom est donn� en param�tre
	 */
	public void load(String filename) {

		try {
			FileReader ir = new FileReader(filename);
			System.out.println("File opened");

			BufferedReader br = new BufferedReader(ir);

			while (br.ready()) {

				Facette fac = new Facette(br.readLine());
				this.add(fac);
				System.out.println("facette created");

			}
			br.close();
		} catch (IOException e) {
			System.out.println("error when parsing " + filename);
		}
	}

	/**
	 * sauve une forme dans le fichier dont le nom est pass� en param�tre
	 * @param filename : nom du fichier
	 */
	public void save(String filename) {
		// sauve une description donn�e comme une liste de facette
		try {
			FileWriter wr = new FileWriter(filename);
			System.out.println("File opened");

			for (int i = 0; i < nbfac; i++) {

				Facette fac = tab[i];
				String s = "" + (int) fac.getP1().x() + " " + (int) fac.getP1().y()
						+ " " + (int) fac.getP1().z() + " " + (int) fac.getP1().x() + " "
						+ (int) fac.getP2().y() + " " + (int) fac.getP2().z() + " "
						+ (int) fac.getP3().x() + " " + (int) fac.getP3().y() + " "
						+ (int) fac.getP3().z() + "\n";

				wr.write(s);

			}
			wr.close();

		} catch (IOException e) {
			System.out.println("error when parsing " + filename);
		}
	}

	/**
	 * calcule un barycentre pour toutes les facettes
	 */
	public void computeBarycentres() {
		for (int i=0;i<nbfac;i++){
			this.tab[i].barycentre();
		}
	}

	/**
	 * calcule la distance de chaque facette au point origin
	 */
	public void computeDistances(Point origin) {
		for (int i=0;i<nbfac;i++){
			this.tab[i].setDistance(origin);
		}
	}

	/**
	 * renvoie la distance maximale trouv�e dans la liste de facettes attention,
	 * les distances doivent avoir �t� caclul�es
	 */
	public double findMaxDistance() {
		computeDistances(new Point(200,200,0));
		double maxi = tab[0].getDistance();
		for (int i =0;i<nbfac;i++){
			if (maxi < tab[i].getDistance()){
					maxi = tab[i].getDistance();
			}
		}
		if (maxi!=-100000){
			return maxi;
		}
		else{
			return 0;
		}
	}

	/**
	 * renvoie la dstance minimale trouv�e dans la liste de facettes attention,
	 * les distances doivent avoir �t� caclul�es
	 */
	public double findMinDistance() {
		computeDistances(new Point(200,200,0));
		double mini = tab[0].getDistance();
		for (int i =0;i<nbfac;i++){
			if (mini > tab[i].getDistance()){
				mini = tab[i].getDistance();
			}
		}
		return mini;
	}

	/**
	 * donne un niveau de gris entre 50 (sombre) et 220 (blanc) a toute facette.
	 * Les facettes les plus proches du point origine qui a servi a calculer les
	 * distances doivent �tre claires, les plus �loign�es sombres attention, les
	 * distances doivent avoir �t� caclul�es
	 */
	/*
	public void setGreyLevels(){
		double maxd = findMaxDistance();
		double mind = this.findMinDistance();
		double interval = maxd-mind;
		System.out.println("Intervalle: "+interval);
		System.out.println("Min: "+mind);

		Facette f;
		int colorlevel;
		for (int i=0;i< this.nbfac;i++) {
			f=this.tab[i];
			double ratio=(f.getDistance() -mind)/interval;
			//System.out.println("Ratio:"+ratio);
			colorlevel =  (int) (255 - ratio*205) ;
			//System.out.println("Distance "+ f.distance + " ColorLevel: "+colorlevel);
			f.setColor(colorlevel);
		}
	}

	 */
	public void setGreyLevels() {
		double mini = findMinDistance();
		double maxi = findMaxDistance();
		for (int i=0;i<nbfac;i++){
			double d = this.tab[i].getDistance();
			int shade = 50 +(int)((((d-mini)/(maxi-mini))*205));
			this.tab[i].setColor(shade);
		}

	}
	/**
	 * trie les facettes de la forme coutrante en fonction de la distance �
	 * l'origine on utilisera un des algorithmes vus en cours
	 */
	public static void merge(Facette[] a,Facette[] l, Facette[] r, int left, int right){
		int i=0,j=0,k=0;
		while (i<left && j<right){
			if (l[i].estAvant(r[j])){
				a[k] = l[i];
				k++;
				i++;
			}
			else{
				a[k] = r[j];
				k++;
				j++;
			}
		}
		while (i<left){
			a[k] = l[i];
			k++;
			i++;
		}
		while (j<left){
			a[k] = r[j];
			k++;
			j++;
		}
	}
	public static void mergesort(Facette[] a,int n) {
		if (n < 2) {
			return;
		}
		int mid = n / 2;
		Facette[] l = new Facette[mid];
		Facette[] r = new Facette[n - mid];

		for (int i = 0; i < mid; i++) {
			l[i] = a[i];
		}
		for (int i=mid;i<n;i++){
			r[i-mid] = a[i];
		}
		mergesort(l,mid);
		mergesort(r,n-mid);
		merge(a,l,r,mid,n-mid);
	}

	public void trieFacettes() {
		mergesort(tab,nbfac);
		// Utiliser un algorithme vu en cours

	}

	/**
	 * rotation d'une forme en XY Se fait en faisant tourner tous les points de
	 * la forme autour d'un point donn� comme centre de la rotation attention,
	 * chaque point ne doit tourner qu'une fois, et des points peuvent �tre
	 * partag�s entre plusieurs facettes
	 * 
	 * @param p
	 *            : Centre de la rotation
	 * @param angle
	 *            : angle de rotation en degr�s
	 */
	public void rotation(Point p, double angle) {

		/*
		 * Liste de point apparaissant dans la forme Utilis� pour �viter de
		 * faire tourner plusieurs fois le m�me point
		 */
		Set<Point> pointList = new HashSet<Point>();

		Facette f;

		for (int i = 0; i < this.nbfac; i++) {
			f = this.tab[i];

			if (!pointList.contains(f.getP1()) ) {
				f.getP1().rotation(p, angle);
				pointList.add(f.getP1());
			}
			if (!pointList.contains(f.getP2())) {
				f.getP2().rotation(p, angle);
				pointList.add(f.getP2());
			}
			if (!pointList.contains(f.getP3())) {
				f.getP3().rotation(p, angle);
				pointList.add(f.getP3());
			}
		}
	}

	/**
	 * rotation d'une forme en XZ Se fait en faisant tourner tous les points de
	 * la forme autour d'un point donn� comme centre de la rotation attention,
	 * chaque point ne doit tourner qu'une fois, et des points peuvent �tre
	 * partag�s entre plusieurs facettes
	 * 
	 * @param p
	 *            : Centre de la rotation
	 * @param angle
	 *            : angle de rotation en degr�s
	 */
	public void rotationXZ(Point p, double angle) {

		Set<Point> pointList = new HashSet<Point>();
	
		for (int i = 0; i < this.nbfac; i++) {
			Facette f = this.tab[i];

			if (!pointList.contains(f.getP1())) {
				f.getP1().rotationXZ(p, angle);
				pointList.add(f.getP1());
			}
			if (!pointList.contains(f.getP2())) {
				f.getP2().rotationXZ(p, angle);
				pointList.add(f.getP2());
			}
			if (!pointList.contains(f.getP3())) {
				f.getP3().rotationXZ(p, angle);
				pointList.add(f.getP3());
			}
		}
	}

	/**
	 * change de mani�re al�atoire l'ordre des facettes de la forme
	 */
	public void shuffle() {
		int i;
		ArrayList<Facette> l = new ArrayList<Facette>();
		for (i = 0; i < nbfac; i++) {
			l.add(tab[i]);
		}
		Collections.shuffle(l);
		i = 0;
		for (Facette f : l) {
			tab[i] = f;
			i++;

		}

	}

}// Fin de la classe
