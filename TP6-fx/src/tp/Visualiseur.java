package tp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// La classe principale du programme.
// En JavaFX, cette classe doit hériter de la classe
// Application qui définie le concept d'application JavaFX
public class Visualiseur extends Application {
	
	// L'opération principale du programme.
	// En JavaFX, elle se contente généralement
	// d'appeler l'opération launch de la classe Application
	// qui, comme son nom l'indique, lance l'application graphique.
	public static void main(String[] args) {
		launch(args);
	}

	
	// Cette operation provient de Application et doit implémentation ici.
	// Elle va servir à construire l'interface graphique de l'application.
	// Le paramètre primaryStage correspond, en quelque sorte, à la fenêtre
	// principale.
	@Override
	public void start(Stage primaryStage) throws Exception {
		// L'interface graphique est décrite dans une document XML (au format FXML).
		// On charge ici ce document. JavaFX construit automatiquement l'IHM
		// grâce à ce document.
		Parent root = FXMLLoader.load(getClass().getResource("vue/UI.fxml"));
		// On associe ensuite l'IHM créée au "Stage" principal.
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		// On paramètre ensuite la fenêtre.
		primaryStage.setTitle("Visualisateur de fonctions");
		primaryStage.show();
		primaryStage.centerOnScreen();
	}
}
