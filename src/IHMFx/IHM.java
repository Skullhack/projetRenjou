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
	public Stage[] stages;
	public Scene[] scenes;
	
    public static void main(String[] args) {
    	
        Application.launch(IHM.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
    	m = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);
    	fj = new FenetreJeu(this);
    	Parent root;
		try {		
			FXMLLoader fx = new FXMLLoader(getClass().getResource("LayoutFenetreJeu.fxml"));
			fx.setController(fj);
			root = fx.load();
	    	Scene scene = new Scene(root, 950, 650);
	    	primaryStage.setScene(scene);
	        primaryStage.show();
	        primaryStage.setTitle("Renjou");
	        primaryStage.centerOnScreen();
	    	m.enregistrerObserveur(this);
	    	actualiser();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@Override
	public void actualiser() {
		System.out.println("yolo");
		fj.update();
	}

}