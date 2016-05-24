package IHMFx;

import Images.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

import Controleur.Moteur;
import Controleur.MoteurObserveur;
import Enum.TypeJoueur;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import javafx.stage.Stage;
public class IHM extends Application implements MoteurObserveur{
	public Moteur m;
	public FenetreJeu fj;
	public Images i;
	public Stage menuStage;
	public Stage aideStage;

    public static void main(String[] args) {
        Application.launch(IHM.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
    	m = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);
    	i = new Images(m);
    	fj = new FenetreJeu(this);
		try {		
			//Premiere Fenetre
			FXMLLoader fx = new FXMLLoader(getClass().getResource("LayoutFenetreJeu.fxml"));
			fx.setController(fj);
			Parent root = fx.load();
	    	primaryStage.setScene(new Scene(root, 950, 650));
	        primaryStage.show();
	        primaryStage.setTitle("Renjou");
	        primaryStage.centerOnScreen();
	        
	        //Seconde Fenetre
	        FXMLLoader fx2 = new FXMLLoader(getClass().getResource("LayoutFenetreMenu.fxml"));
			fx2.setController(fj);
			Parent root2 = fx2.load();
	        menuStage = new Stage();
	        menuStage.setTitle("Menu");
	        menuStage.setScene(new Scene(root2, 700, 500));
	        menuStage.show();
	        menuStage.centerOnScreen();
	        
	        //Troisieme Fenetre
	        
	    	m.enregistrerObserveur(this);
	    	actualiser();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@Override
	public void actualiser() {
		fj.update();
	}

}