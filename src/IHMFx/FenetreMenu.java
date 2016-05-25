package IHMFx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FenetreMenu extends Stage{	
	private boolean nouvellePartie;
	private EcouteurFenetreMenu efm;
	
	public FenetreMenu(EcouteurFenetreMenu efm) throws IOException {
        this.efm = efm;
		FXMLLoader fx2 = new FXMLLoader(getClass().getResource("LayoutFenetreMenu.fxml"));
		fx2.setController(efm);
		Parent root2 = fx2.load();
        this.setTitle("Menu");
        this.setScene(new Scene(root2, 700, 500));
        this.centerOnScreen();
	}
	
	public void montrer() {
		efm.selectionnerRadioJoueur();
		this.show();
	}
	
	public void cacher() {
		this.hide();
	}
	
	public void toujoursMettreEnHaut() {
		this.setAlwaysOnTop(true);
	}
	
	public boolean getNouvellePartie() {
		return this.nouvellePartie;
	}
	
	public void setNouvellePartie(boolean nouvellePartie) {
		this.nouvellePartie = nouvellePartie;
	}
}
