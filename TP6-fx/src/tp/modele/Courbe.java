package tp.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ps.PSFunctionParser;

public class Courbe {
	private final StringProperty equation;
	private final IntegerProperty nombrePoints;
	private final DoubleProperty minX;
	private final DoubleProperty maxX;
	
	private PSFunctionParser parser;

	
	public Courbe(int nbPts, String eq) {
		super();
		minX = new SimpleDoubleProperty();
		maxX = new SimpleDoubleProperty();
		nombrePoints = new SimpleIntegerProperty(nbPts);
		equation = new SimpleStringProperty();
		// Version for Java version 1.8 (at least) with lambda-expression
		/*
		equation.addListener((o,v,v1) -> {
			try {
				parser = new PSFunctionParser(getEquation());
			}catch(IllegalArgumentException ex) {
				//
			}
		});
		*/
		// Version for any Java version
		equation.addListener(new ChangeListener<String>(){
			public void changed (ObservableValue<? extends String> s, String a, String b) {
				try {
					parser = new PSFunctionParser(getEquation());
				}catch(IllegalArgumentException ex) {
					//
				}
			}
		});
		setEquation(eq);
	}
	
	
	public void setNombrePoints(int nnPts) {
		if(nnPts>0)
			nombrePoints.set(nnPts);
	}
	
	public double getPlottingStep() {
		return (maxX.get()-minX.get())/(nombrePoints.get()-1);
	}

	public void setMinX(double x) {
		if(x<maxX.get())
			minX.set(x);
	}


	public void setMaxX(double x) {
		if(x>minX.get())
			maxX.set(x);
	}


	public int getNombrePoints() {
		return nombrePoints.get();
	}


	public double getMinX() {
		return minX.get();
	}


	public double getMaxX() {
		return maxX.get();
	}


	public double getY(double x) {
		return parser.getY(x);
	}
	
	
	public String getEquation() {
		return equation.get();
	}

	public void setEquation(String eq) {
		equation.set(eq);
	}
	
	public StringProperty equationProperty() {
		return equation;
	}
	
	public IntegerProperty nombrePointsProperty() {
		return nombrePoints;
	}
}
