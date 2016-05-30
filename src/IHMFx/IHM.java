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
	public EcouteurFenetreJeu efj;
	public EcouteurFenetreMenu efm;
	public EcouteurFenetreAide efa;
	public EcouteurFenetreRenjou efr;
	public Images i;
	public FenetreJeu fj;
	public FenetreMenu fm;
	public FenetreAide fa;
	public FenetreRenjou fr;
	public Stage aideStage;

    public static void main(String[] args) {
        Application.launch(IHM.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
    	m = new Moteur(TypeJoueur.Humain, TypeJoueur.IAFacile);
    	i = new Images(m);	
		//Premiere Fenetre
	    efj = new EcouteurFenetreJeu(this);
	        
	    //Seconde Fenetre
	    efm = new EcouteurFenetreMenu(this);
	        
	    //Troisieme Fenetre
	    efa = new EcouteurFenetreAide(this);
	    
	    //Quatrieme Fenetre
	    efr = new EcouteurFenetreRenjou(this);
	    fj.montrer();
	    
	    m.enregistrerObserveur(this);
	    actualiser();
    }

	@Override
	public void actualiser() {
		efj.update();
		efm.update();
		efa.update();

	}

}