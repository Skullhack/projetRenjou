package IHMFx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FenetreJeu extends Stage {
	public FenetreJeu(EcouteurFenetreJeu efj) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("LayoutFenetreJeu.fxml"));
		fx.setController(efj);
		Parent root = fx.load();
    	this.setScene(new Scene(root, 950, 650));
    	this.setTitle("Renjou");
    	this.centerOnScreen();
	}
	
	public void montrer() {
		this.show();
	}
	
	public void toujoursMettreEnHaut() {
		this.setAlwaysOnTop(true);
	}
}
