/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHMFx;

import Images.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import javafx.event.ActionEvent;
import Controleur.Moteur;
import Enum.TypeJoueur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author michauad
 */
public class FenetreJeu implements Initializable {
    private Moteur m;
    //Panneau gauche;
    @FXML
    private Label labelNoir;
    @FXML
    private ImageView pionNoir;
    @FXML
    private Label nbPieceNoir;
    @FXML
    private ImageView iconeNoir; //Sablier ou Aide
    @FXML
    private Label tempsNoir;
    @FXML
    private ImageView annuler;
    @FXML
    private ImageView refaire;
    @FXML
    private Label labelBlanc;
    @FXML
    private ImageView pionBlanc;
    @FXML
    private Label nbPieceBlanc;
    @FXML
    private ImageView iconeBlanc; //Sablier ou Aide
    @FXML
    private Label tempsBlanc;
    @FXML
    private ImageView recommencer;
    @FXML
    private ImageView plateau;
   
    
    public FenetreJeu(IHM ihm) {
        //Variables
        this.m = ihm.m;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void dragDebutSablierAmpoule1(MouseEvent e) {
		String theme=m.getRenjou().getEmplacementThemes();
		if (m.getRenjou().getJoueurs()[0].getType() == TypeJoueur.Humain) {
			iconeNoir.setImage(new Image("./Images/"+theme+"/AmpouleDrag.png"));
		} else {
			iconeNoir.setImage(new Image("./Images/"+theme+"/Sablier.png"));
		}
	}
	
	@FXML
	private void dragFinSablierAmpoule1(MouseEvent e) {
		String theme=m.getRenjou().getEmplacementThemes();
		if (m.getRenjou().getJoueurs()[0].getType() == TypeJoueur.Humain) {
			iconeNoir.setImage(new Image("./Images/"+theme+"/Ampoule.png"));
		} else {
			iconeNoir.setImage(new Image("./Images/"+theme+"/Sablier.png"));
		}
	}
	
	@FXML
	private void dragDebutSablierAmpoule2(MouseEvent e) {
		String theme=m.getRenjou().getEmplacementThemes();
		if (m.getRenjou().getJoueurs()[1].getType() == TypeJoueur.Humain) {
			iconeBlanc.setImage(new Image("./Images/"+theme+"/AmpouleDrag.png"));
		} else {
			iconeBlanc.setImage(new Image("./Images/"+theme+"/Sablier.png"));
		}
	}
	
	@FXML
	private void dragFinSablierAmpoule2(MouseEvent e) {
		String theme=m.getRenjou().getEmplacementThemes();
		if (m.getRenjou().getJoueurs()[1].getType() == TypeJoueur.Humain) {
			iconeBlanc.setImage(new Image("./Images/"+theme+"/Ampoule.png"));
		} else {
			iconeBlanc.setImage(new Image("./Images/"+theme+"/Sablier.png"));
		}
	}
	
	@FXML
	private void dragDebutRecommencer(MouseEvent e) {
		
		String theme=m.getRenjou().getEmplacementThemes();
		recommencer.setImage(new Image("./Images/"+theme+"/RecommencerDrag.png"));
	}
	
	@FXML
	private void dragFinRecommencer(MouseEvent e) {
		String theme=m.getRenjou().getEmplacementThemes();
		recommencer.setImage(new Image("./Images/"+theme+"/Recommencer.png"));
	}
	
	@FXML
	private void setIconeSablierAmpoule() {
		String theme=m.getRenjou().getEmplacementThemes();
		if (m.getRenjou().getJoueurs()[0].getType() == TypeJoueur.Humain) {
			iconeNoir.setImage(new Image("./Images/"+theme+"/Ampoule.png"));
		} else {
			iconeNoir.setImage(new Image("./Images/"+theme+"/Sablier.png"));
		}
		if (m.getRenjou().getJoueurs()[1].getType() == TypeJoueur.Humain) {
			iconeBlanc.setImage(new Image("./Images/"+theme+"/Ampoule.png"));
		} else {
			iconeBlanc.setImage(new Image("./Images/"+theme+"/Sablier.png"));
		}
	}
	
	@FXML
	private void dragDebutAnnuler() {
		String theme=m.getRenjou().getEmplacementThemes();
		annuler.setImage(new Image("./Images/"+theme+"/AnnulerDrag.png"));
	}
	
	@FXML
	private void dragFinAnnuler() {
		String theme=m.getRenjou().getEmplacementThemes();
		annuler.setImage(new Image("./Images/"+theme+"/Annuler.png"));
	}
	
	@FXML
	private void dragDebutRefaire() {
		String theme=m.getRenjou().getEmplacementThemes();
		refaire.setImage(new Image("./Images/"+theme+"/RefaireDrag.png"));
	}
	
	@FXML
	private void dragFinRefaire() {
		String theme=m.getRenjou().getEmplacementThemes();
		refaire.setImage(new Image("./Images/"+theme+"/Refaire.png"));
	}
	
	@FXML
	private void annuler(MouseEvent e) {
		m.annuler();
	}
	
	@FXML
	private void refaire(MouseEvent e) {
		m.refaire();
	}

	@FXML
	private void recommencerClick(MouseEvent e) {
		m.recommencerPartie();
	}
	
	@FXML
	private void creerPartieMenu(ActionEvent e) {
		//TODO
	}
	
	@FXML
	private void sauvegarderMenu(ActionEvent e) {
		String fichierSauvegarder = null;
        JFileChooser ouvertureFenetre = new JFileChooser();
        int checkEtatFenetre = ouvertureFenetre.showSaveDialog(null);
        if (checkEtatFenetre == ouvertureFenetre.APPROVE_OPTION) {
            fichierSauvegarder = ouvertureFenetre.getSelectedFile().getAbsolutePath();
            m.sauvegarder(fichierSauvegarder);
        }
	}
	
	@FXML
	private void chargerMenu(ActionEvent e) {
		String fichierCharger = null;
        JFileChooser ouvertureFenetre = new JFileChooser();
        int checkEtatFenetre = ouvertureFenetre.showOpenDialog(null);
        if (checkEtatFenetre == ouvertureFenetre.APPROVE_OPTION) {
            fichierCharger = ouvertureFenetre.getSelectedFile().getAbsolutePath();
            m.charger(fichierCharger);
        }

	}
	
	@FXML
	private void quitterMenu(ActionEvent e) {
		System.exit(0);
	}
	
	@FXML
	private void clickPlateau(MouseEvent e) {
		
	}
	
	@FXML
	private void joueurMenu(ActionEvent e) {
		
	}
	
	@FXML
	private void tabousMenu(ActionEvent e) {
		
	}
	
	@FXML
	private void themeMenu(ActionEvent e) {
		
	}
	
	@FXML
	private void reglesMenu(ActionEvent e) {
		
	}
	
	@FXML
	private void tutorielMenu(ActionEvent e) {
		
	}
	
	@FXML
	private void aProposMenu(ActionEvent e) {
		
	}
	
	public void update() {
		//Panel Gauche
		labelNoir.setText(m.getRenjou().getJoueurs()[0].getType().toString());
		labelBlanc.setText(m.getRenjou().getJoueurs()[1].getType().toString());
		nbPieceNoir.setText(Integer.toString(m.getRenjou().getJoueurs()[0].getNbPion()));
		nbPieceBlanc.setText(Integer.toString(m.getRenjou().getJoueurs()[1].getNbPion()));
		setIconeSablierAmpoule();
	}   
}
