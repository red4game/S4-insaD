
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * 
 * Classe qui permet d'afficher des formes graphiques dans une fenetre (ronds,
 * rectangles,...) Pour les besoins du TP, nous utiliserons principalement
 * addFacette
 *
 */
public class ShapeDisplayer extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 562273828812167786L;

	private final int maxCharHeight = 15;
	private static final int minFontSize = 6;

	private static final Color bg = Color.white;

	private static final Color fg = Color.black;
	private static final Color red = Color.red;
	private static final Color white = Color.white;

	private static final BasicStroke stroke = new BasicStroke(2.0f);
	private static final BasicStroke wideStroke = new BasicStroke(8.0f);

	private static final float dash1[] = { 10.0f };
	
	private static final BasicStroke dashed = new BasicStroke(1.0f,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
	Dimension totalSize;
	FontMetrics fontMetrics;

	public void init() {
		// Initialize drawing colors
		setBackground(bg);
		setForeground(fg);
	}

	private FontMetrics pickFont(Graphics2D g2, String longString, int xSpace) {
		boolean fontFits = false;
		Font font = g2.getFont();
		FontMetrics fontMetrics = g2.getFontMetrics();
		int size = font.getSize();
		String name = font.getName();
		int style = font.getStyle();

		while (!fontFits) {
			if ((fontMetrics.getHeight() <= maxCharHeight)
					&& (fontMetrics.stringWidth(longString) <= xSpace)) {
				fontFits = true;
			} else {
				if (size <= minFontSize) {
					fontFits = true;
				} else {
					g2.setFont(font = new Font(name, style, --size));
					fontMetrics = g2.getFontMetrics();
				}
			}
		}

		return fontMetrics;
	}

	/**
	 * Display a facette
	 * 
	 * @param f
	 *            : the facette to display
	 */
	public void displayFacette(Facette f) {

		Point p1 = f.getP1();
		Point p2 = f.getP2();
		Point p3 = f.getP3();

		addFilledTriangle(p1.x(), p1.y(), p2.x(), p2.y(), p3.x(), p3.y(), f.getCol());
		addTriangle(p1.x(), p1.y(), p2.x(), p2.y(), p3.x(), p3.y());
	}

	public void addFilledTriangle(double x1, double y1, double x2, double y2,
			double x3, double y3, Color col) {

		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
		// Color fg3D = Color.green;
		Color fg3D = col;

		g2.setPaint(fg3D);

		// fill and stroke GeneralPath
		double x3Points[] = { x1, x2, x3 };
		double y3Points[] = { y1, y2, y3 };
		GeneralPath filledPolygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
				x3Points.length);
		filledPolygon.moveTo(x3Points[0], y3Points[0]);
		for (int index = 1; index < x3Points.length; index++) {
			filledPolygon.lineTo(x3Points[index], y3Points[index]);
		}
		
		filledPolygon.closePath();

		g2.setPaint(fg3D);
		g2.fill(filledPolygon);
		g2.draw(filledPolygon);

	}

	public void addTriangle(double x1, double y1, double x2, double y2,
			double x3, double y3) {

		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);

		// fill and stroke GeneralPath
		double x3Points[] = { x1, x2, x3 };
		double y3Points[] = { y1, y2, y3 };
		GeneralPath filledPolygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
				x3Points.length);
		filledPolygon.moveTo(x3Points[0], y3Points[0]);
		for (int index = 1; index < x3Points.length; index++) {
			filledPolygon.lineTo(x3Points[index], y3Points[index]);
		}
		
		filledPolygon.closePath();

		g2.setPaint(fg); // set color to black for borders
		g2.draw(filledPolygon);

	}

	public void addRectangle(int x, int y, int x2, int y2) {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);

		Color fg3D = Color.green;

		g2.setPaint(fg3D);

		g2.setPaint(fg3D);
		g2.draw3DRect(x, y, x2, y2, false);
		// g2.draw3DRect(x+3, y+3, d.width - 7, d.height - 7, false);
		g2.setPaint(fg);

	}

	public void addFilledRectangle(int x1, int y1, int x2, int y2) {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		// fill Rectangle2D.Double (red)
		g2.setPaint(fg);
		g2.fill(new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1));
		g2.setPaint(fg);
	}

	public void paint(Graphics g) {
	}

	public void paint2(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
		Dimension d = getSize();
		int gridWidth = d.width / 6;
		int gridHeight = d.height / 2;

		fontMetrics = pickFont(g2, "Filled and Stroked GeneralPath", gridWidth);

		Color fg3D = Color.lightGray;

		g2.setPaint(fg3D);
		g2.draw3DRect(0, 0, d.width - 1, d.height - 1, true);
		g2.draw3DRect(3, 4, d.width - 7, d.height - 7, false);
		g2.setPaint(fg3D);

		this.addRectangle(10, 10, 20, 20);

		int x = 5;
		int y = 7;
		int rectWidth = gridWidth - 2 * x;
		int stringY = gridHeight - 3 - fontMetrics.getDescent();
		int rectHeight = stringY - fontMetrics.getMaxAscent() - y - 2;

		// draw Line2D.Double
		g2.draw(new Line2D.Double(x, y + rectHeight - 1, x + rectWidth, y));
		g2.drawString("Line2D", x, stringY);
		x += gridWidth;

	}

	public void clear() {
		Dimension d = getSize();
		int gridWidth = d.width;
		int gridHeight = d.height;

		this.addFilledRectangle(0, 0, gridWidth, gridHeight);

	}

	public static void main(String args[]) {
		JFrame f = new JFrame("Triangles");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		JApplet applet = new ShapeDisplayer();
		f.getContentPane().add("Center", applet);
		applet.init();
		f.pack();
		f.setSize(new Dimension(600, 600));
		f.setVisible(true);

		((ShapeDisplayer) applet).addFilledTriangle(20, 20, 40, 40, 10, 40,
				Color.green);
		((ShapeDisplayer) applet).addFilledTriangle(100, 100, 100, 40, 10, 40,
				Color.green);
		((ShapeDisplayer) applet).addFilledRectangle(50, 50, 60, 100);

	}

}
