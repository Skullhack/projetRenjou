/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHMFx;

import Images.*;
import Utilitaire.Coordonnees;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import Controleur.Moteur;
import Controleur.PionJoue;
import Enum.EtatPartie;
import Enum.TypeCase;
import Enum.TypeJoueur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

/**
 *
 * @author michauad
 */
public class EcouteurFenetreJeu implements Initializable {
    private Moteur m;
    private IHM ihm;
    private ArrayList<Image> imageAnnuler;
    private ArrayList<Image> imageRefaire;
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
    @FXML
    private FlowPane panelJ1;
    @FXML
    private FlowPane panelJ2;
    @FXML
    private ImageView fond;
   
    
    public EcouteurFenetreJeu(IHM ihm) {
        //Variables
    	this.ihm = ihm;
        this.m = ihm.m;
        this.imageAnnuler = new ArrayList<>();
        this.imageRefaire = new ArrayList<>();
		try {
			ihm.fj = new FenetreJeu(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void dragDebutSablierAmpoule1(MouseEvent e) {
		String theme=m.getRenjou().getEmplacementThemes();
		if (m.getRenjou().getJoueurs()[0].getType() == TypeJoueur.Humain) {
			iconeNoir.setImage(ihm.i.getAmpouleDrag());
		} else {
			iconeNoir.setImage(ihm.i.getSablier());
		}
	}
	
	@FXML
	private void dragFinSablierAmpoule1(MouseEvent e) {
		String theme=m.getRenjou().getEmplacementThemes();
		if (m.getRenjou().getJoueurs()[0].getType() == TypeJoueur.Humain) {
			iconeNoir.setImage(ihm.i.getAmpoule());
		} else {
			iconeNoir.setImage(ihm.i.getSablier());
		}
	}
	
	@FXML
	private void dragDebutSablierAmpoule2(MouseEvent e) {
		String theme=m.getRenjou().getEmplacementThemes();
		if (m.getRenjou().getJoueurs()[1].getType() == TypeJoueur.Humain) {
			iconeBlanc.setImage(ihm.i.getAmpouleDrag());
		} else {
			iconeBlanc.setImage(ihm.i.getSablier());
		}
	}
	
	@FXML
	private void dragFinSablierAmpoule2(MouseEvent e) {
		String theme=m.getRenjou().getEmplacementThemes();
		if (m.getRenjou().getJoueurs()[1].getType() == TypeJoueur.Humain) {
			iconeBlanc.setImage(ihm.i.getAmpoule());
		} else {
			iconeBlanc.setImage(ihm.i.getSablier());
		}
	}
	
	@FXML
	private void dragDebutRecommencer(MouseEvent e) {
		String theme=m.getRenjou().getEmplacementThemes();
		recommencer.setImage(ihm.i.getRecommencerDrag());
	}
	
	@FXML
	private void dragFinRecommencer(MouseEvent e) {
		String theme=m.getRenjou().getEmplacementThemes();
		recommencer.setImage(ihm.i.getRecommencer());
	}
	
	@FXML
	private void setIconeSablierAmpoule() {
		String theme=m.getRenjou().getEmplacementThemes();
		if (m.getRenjou().getJoueurs()[0].getType() == TypeJoueur.Humain) {
			iconeNoir.setImage(ihm.i.getAmpoule());
		} else {
			iconeNoir.setImage(ihm.i.getSablier());
		}
		if (m.getRenjou().getJoueurs()[1].getType() == TypeJoueur.Humain) {
			iconeBlanc.setImage(ihm.i.getAmpoule());
		} else {
			iconeBlanc.setImage(ihm.i.getSablier());
		}
	}
	
	@FXML
	private void dragDebutAnnuler() {
		String theme=m.getRenjou().getEmplacementThemes();
		if (!annuler.isDisabled())
			annuler.setImage(ihm.i.getAnnulerDrag());
	}
	
	@FXML
	private void dragFinAnnuler() {
		String theme=m.getRenjou().getEmplacementThemes();
		if (!annuler.isDisabled())
		annuler.setImage(ihm.i.getAnnuler());
	}
	
	@FXML
	private void dragDebutRefaire() {
		String theme=m.getRenjou().getEmplacementThemes();
		if (!refaire.isDisabled())
			refaire.setImage(ihm.i.getRefaireDrag());
	}
	
	@FXML
	private void dragFinRefaire() {
		String theme=m.getRenjou().getEmplacementThemes();
		if (!refaire.isDisabled())
			refaire.setImage(ihm.i.getRefaire());
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
		//rendre FenetreJeu inactif : TODO
		ihm.fm.setNouvellePartie(true);
		ihm.fm.montrer();
		ihm.fm.setAlwaysOnTop(true);
		ihm.efm.getTabPane().getSelectionModel().select(0);
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
		Coordonnees c;
    	int width =(int) ((plateau.getFitWidth())/16);
    	int height = (int) ((plateau.getFitHeight())/16);
    	
    	double widthMax = plateau.getFitWidth() - width/1.4;
    	double heightMax = plateau.getFitHeight() - height/1.4;
    	
    	if (e.getX()<widthMax && e.getY()<heightMax) {
    		int ligne = (int) ((e.getY() - height/2) /height %15);
    		int colonne = (int) ((e.getX() - width/2) /width %15);
    		c = new Coordonnees(ligne,colonne);
    		m.operationJouer(c, TypeJoueur.Humain);
    	}
   	}
	
	@FXML
	private void joueurMenu(ActionEvent e) {
		//rendre FenetreJeu inactif : TODO
		ihm.fm.setNouvellePartie(false);
		ihm.fm.montrer();
		ihm.fm.setAlwaysOnTop(true);
		ihm.efm.getTabPane().getSelectionModel().select(0);
	}
	
	@FXML
	private void tabousMenu(ActionEvent e) {
		//rendre FenetreJeu inactif : TODO
		ihm.fm.setNouvellePartie(false);
		ihm.fm.montrer();
		ihm.fm.setAlwaysOnTop(true);
		ihm.efm.getTabPane().getSelectionModel().select(1);
	}
	
	@FXML
	private void themeMenu(ActionEvent e) {
		ihm.fm.setNouvellePartie(false);
		ihm.fm.montrer();
		ihm.fm.setAlwaysOnTop(true);
		ihm.efm.getTabPane().getSelectionModel().select(2);
	}
	
	@FXML
	private void reglesMenu(ActionEvent e) {
		//rendre FenetreJeu inactif : TODO
		ihm.fa.montrer();
		ihm.fa.setAlwaysOnTop(true);
		ihm.efa.getTabPane().getSelectionModel().select(0);
	}
	
	@FXML
	private void tutorielMenu(ActionEvent e) {
		//rendre FenetreJeu inactif : TODO
		ihm.fa.montrer();
		ihm.fa.setAlwaysOnTop(true);
		ihm.efa.getTabPane().getSelectionModel().select(1);
	}
	
	@FXML
	private void aProposMenu(ActionEvent e) {
		//rendre FenetreJeu inactif : TODO
		ihm.fa.montrer();
		ihm.fa.setAlwaysOnTop(true);
		ihm.efa.getTabPane().getSelectionModel().select(2);
	}
	
	public void update() {
		//Panel Gauche
		pionNoir.setImage(ihm.i.getPionNoir());
		pionBlanc.setImage(ihm.i.getPionBlanc());
		labelNoir.setText(m.getRenjou().getJoueurs()[0].getType().toString());
		labelBlanc.setText(m.getRenjou().getJoueurs()[1].getType().toString());
		nbPieceNoir.setText(Integer.toString(m.getRenjou().getJoueurs()[0].getNbPion()));
		nbPieceBlanc.setText(Integer.toString(m.getRenjou().getJoueurs()[1].getNbPion()));
		
		//Pointillé sur joueur en cours
		if (m.getRenjou().getJoueurCourant() == 0 && m.getRenjou().getEtatPartie() == EtatPartie.EnCours) {
			panelJ1.setStyle("-fx-border-width : 5;-fx-border-color : yellow;");
			panelJ2.setStyle(null);
		} else if (m.getRenjou().getJoueurCourant() == 1 && m.getRenjou().getEtatPartie() == EtatPartie.EnCours) {
			panelJ1.setStyle(null);
			panelJ2.setStyle("-fx-border-width : 5;-fx-border-color : yellow;");
		} else {
			panelJ1.setStyle(null);
			panelJ2.setStyle(null);
		}
		
		setIconeSablierAmpoule();
		//Plateau
		repeindrePlateau();		
		disabEnabAnnulerRefaire();
		//Voir si partie gagne
		partieGagne();
		//Si c'est au tour de l'IA de jouer on desactive annuler et refaire
		if (m.getRenjou().getJoueurs()[m.getRenjou().getJoueurCourant()].getType() != TypeJoueur.Humain 
				&& m.getRenjou().getEtatPartie() == EtatPartie.EnCours) {
			annuler.setImage(ihm.i.getAnnulerDisab());
			annuler.setDisable(true);
			refaire.setImage(ihm.i.getRefaireDisab());
			refaire.setDisable(true);
		}
		
		fond.setImage(ihm.i.getFond());
	}
	
	public void disabEnabAnnulerRefaire() {
		if (m.getRenjou().getListeRefaire().isEmpty()) {
			refaire.setImage(ihm.i.getRefaireDisab());
			refaire.setDisable(true);
		} else {
			refaire.setDisable(false);
			refaire.setImage(ihm.i.getRefaire());
		}
		
		if (m.getRenjou().getListeAnnuler().isEmpty()) {
			annuler.setImage(ihm.i.getAnnulerDisab());
			annuler.setDisable(true);
		} else {
			annuler.setDisable(false);
			annuler.setImage(ihm.i.getAnnuler());
		}
	}

	private void partieGagne() {
    	String message;
    	if (!(m.getRenjou().getEtatPartie() == EtatPartie.EnCours)) {
	    	if (m.getRenjou().getEtatPartie() == EtatPartie.NoirGagne) {
	    		message = "Le joueur noir a gagné la partie !";
	    	} else if (m.getRenjou().getEtatPartie() == EtatPartie.BlancGagne) {
	    		message = "Le joueur blanc a gagné la partie !";
	    	} else if (m.getRenjou().getEtatPartie() == EtatPartie.BlancGagneParTabou) {
	    		message = "Le joueur blanc a gagné la partie grace a  un tabou !";
	    	}
	    	else if(m.getRenjou().getEtatPartie() == EtatPartie.PartieNulle) {
	    		message = "Partie nulle !";
	    	} else {
	    		message = "Erreur !";
	    	}
	    	int confirm = JOptionPane.showOptionDialog(
		             null, message, 
		             "Fin de partie", JOptionPane.CLOSED_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
    	}
    }
	
	private void repeindrePlateau() {
		String theme = m.getRenjou().getEmplacementThemes();
		Image plat = ihm.i.getPlateau();

		Canvas canvas = new Canvas(plat.getWidth(),plat.getHeight());
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		
		//On dessine l'image dans le canvas
		graphicsContext.drawImage(plat, 0, 0);
		
		//On ajoute les pions
		Image iPionBlanc;
		Image iPionNoir;
		for (int i=0;i<m.getRenjou().getListeAnnuler().size();i++) {
			if (i>m.getRenjou().getListeAnnuler().size()-3) {
				iPionBlanc = ihm.i.getPionBlancJoue();
				iPionNoir = ihm.i.getPionNoirJoue();
			} else {
				iPionBlanc = ihm.i.getPionBlanc();
				iPionNoir = ihm.i.getPionNoir();
			}
			PionJoue p = m.getRenjou().getListeAnnuler().get(i); 
	    	double width = (plat.getWidth())/16;
	    	double height = (plat.getHeight())/16;
	    	double x = (p.getCoordonnees().getColonne()*width+4)+(width/2);
	        double y = (p.getCoordonnees().getLigne()*height+4)+(height/2);
	        
	        if (p.getTypeCase() == TypeCase.PionBlanc)
	        	graphicsContext.drawImage(iPionBlanc, x, y, width, height);
	        else
	        	graphicsContext.drawImage(iPionNoir, x, y, width, height);
		}
		
		//On retransforme en Image
		WritableImage writableImage = new WritableImage((int)plat.getWidth(), (int)plat.getHeight());
		canvas.snapshot(null, writableImage);
		
		Image i = (Image) writableImage;
		//imageAnnuler.add(i);
		plateau.setImage(i);
	}   
}
