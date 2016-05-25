package IHMFx;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FenetreMenu extends Stage {	
	private boolean nouvellePartie;
	private EcouteurFenetreMenu efm;
	
	public FenetreMenu(EcouteurFenetreMenu efm) throws IOException {
        this.efm = efm;
		FXMLLoader fx = new FXMLLoader(getClass().getResource("LayoutFenetreMenu.fxml"));
		fx.setController(efm);
		Parent root = fx.load();
        this.setTitle("Menu");
        this.setScene(new Scene(root, 700, 500));
        this.centerOnScreen();
	}
	
	public void montrer() {
		efm.selectionnerRadioJoueur();
		this.show();
	}
	
	public void cacher() {
		this.hide();
	}

	public boolean getNouvellePartie() {
		return this.nouvellePartie;
	}
	
	public void setNouvellePartie(boolean nouvellePartie) {
		this.nouvellePartie = nouvellePartie;
	}
}
