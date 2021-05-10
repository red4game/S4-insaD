package tp.controleur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import tp.modele.Courbe;

public class UIControleur implements Initializable {
	@FXML PieChart pie;
	@FXML ScatterChart<Double, Double> curve;
	@FXML TextField eqField;
	@FXML TextField nbField;
	@FXML TextField maxFieldX;
	@FXML TextField minFieldX;
	@FXML CheckBox polarBox;
	
	// Le modèle de l'application
	Courbe courbe;
	
	// L'opération initialize est automatiquement appelée juste après la création de la vue.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Q2
		lireFichier();

		//Q1
		//pie.getData().add(new Data("Données 1", 33));
		//pie.getData().add(new Data("Données 2", 33));
		//pie.getData().add(new Data("Données 3", 33));
		
		// Sets the position of the legend.
		pie.setLegendSide(Side.LEFT);
		
		// Création du modèle.
		courbe = new Courbe(200, "x"); // 4 x mul cos
		courbe.setMinX(0);
		courbe.setMaxX(360);
		
		// Q6
		courbe.equationProperty().bindBidirectional(eqField.textProperty());
		courbe.setEquation("x");
		updatePlot();
	}
	
	
	private void lireFichier() {
		try(FileReader fr = new FileReader(new File("data.txt"));
			BufferedReader br = new BufferedReader(fr)) {
			String line;
			while ((line = br.readLine()) != null){
				String[] datas = line.split(";");
				pie.getData().add(new Data(datas[0],Integer.parseInt(datas[1])));
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void updatePlot() {
		XYChart.Series<Double,Double> series = new XYChart.Series<>();
		curve.getData().clear();

		if(polarBox.isSelected()){
			updatePlotPolar(series.getData());
		}
		else{
			updatePlotCartesian(series.getData());
		}

		
		curve.getData().add(series);
	}
	
	
	private void updatePlotCartesian(List<XYChart.Data<Double, Double>> data) {
		for(double x=courbe.getMinX(), step = courbe.getPlottingStep(); x<=courbe.getMaxX(); x+=step) {
			data.add(new XYChart.Data<>(x, courbe.getY(x)));
		}
	}
	
	
	private void updatePlotPolar(List<XYChart.Data<Double, Double>> data) {
		for(double x=courbe.getMinX(), step = courbe.getPlottingStep(); x<=courbe.getMaxX(); x+=step) {
			Point2D point = getPolarPoint(x);
			data.add(new XYChart.Data<>(point.getX(), point.getY()));
		}
	}
	
	
	@FXML
	void onEquationChanged(ActionEvent evt) {
		updatePlot();
	}

	@FXML
	void onNbPtsEquationChanged(ActionEvent evt) {
		courbe.setNombrePoints(Integer.parseInt(nbField.getText()));
		updatePlot();
	}

	@FXML
	void SetBorneMaxX(ActionEvent evt) {
		courbe.setMaxX(Integer.parseInt(maxFieldX.getText()));
		updatePlot();
	}
	@FXML

	void SetBorneMinX(ActionEvent evt) {
		courbe.setMinX(Integer.parseInt(minFieldX.getText()));
		updatePlot();
	}
	
	
	@FXML
	void onPolarBox(ActionEvent evt) {
		updatePlot();
	}
	
	
	/**
	 * Computes the polar coordinate for a given cartesian coordinate.
	 * @param x The X-coordinate to use.
	 * @return The polar coordinate. Cannot be null.
	 */
	private Point2D getPolarPoint(final double x) {
		final double radius = courbe.getY(x);
		final double angle = Math.toRadians(x);
		final double x1 = radius * Math.cos(angle);
		final double y1 = -radius * Math.sin(angle);
		return new Point2D(x1*40, y1*40);
	}
}
