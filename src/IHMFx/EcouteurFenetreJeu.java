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
import Enum.TypeTabous;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import sun.awt.image.SurfaceManager.FlushableCacheData;

/**
 *
 * @author michauad
 */
public class EcouteurFenetreJeu implements Initializable {
    private Moteur m;
    private IHM ihm;
    private boolean seuleFois;
    private Image ancienPlateau;
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
    @FXML
    private ImageView tabou1Image;
    @FXML
    private ImageView tabou2Image;
    @FXML
    private ImageView tabou3Image;
    @FXML
    private Label tabou1;
    @FXML
    private Label tabou2;
    @FXML
    private Label tabou3;
    @FXML
    private AnchorPane tabou1Panel;
    @FXML
    private AnchorPane tabou2Panel;
    @FXML
    private AnchorPane tabou3Panel;
    @FXML
    private Label list1;
    @FXML
    private Label list2;
    @FXML
    private Label list3;
    @FXML
    private Label list4;
    @FXML
    private ImageView listImage1;
    @FXML
    private ImageView listImage2;
    @FXML
    private ImageView listImage3;
    @FXML
    private ImageView listImage4;
    @FXML
    private ImageView flecheHaut;
    @FXML
    private ImageView flecheBas;
    @FXML
    private ImageView imageTuto;


    public EcouteurFenetreJeu(IHM ihm) {
        //Variables
    	this.seuleFois = false;
    	this.ihm = ihm;
        this.m = ihm.m;
		try {
			ihm.fj = new FenetreJeu(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tooltip tooltipTabou1 = new Tooltip();
		tooltipTabou1.setText("Trois-Trois : Si NOIR aligne trois pions dans deux directions différentes simultanément, il perd. \n Un clic sur l'icone vous renvoie dans la fenêtre de configuration des tabous.");
		tabou1.setTooltip(tooltipTabou1);
		tabou1.setStyle("-fx-font-size: 20");
		Tooltip tooltipTabou2 = new Tooltip();
		tooltipTabou2.setText("Quatre-Quatre : Si NOIR aligne quatre pions dans deux directions différentes simultanément, il perd. \n Un clic sur l'icone vous renvoie dans la fenêtre de configuration des tabous.");
		tabou2.setTooltip(tooltipTabou2);
		tabou2.setStyle("-fx-font-size: 20");
		Tooltip tooltipTabou3 = new Tooltip();
		tooltipTabou3.setText("Six-Sept : Si NOIR aligne plus de cinq pions connexes, il perd. \n Un clic sur l'icone vous renvoie dans la fenêtre de configuration des tabous.");
		tabou3.setTooltip(tooltipTabou3);
		tabou3.setStyle("-fx-font-size: 20");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		flecheHaut.setImage(ihm.i.getFlecheHautDisab());
		flecheHaut.setDisable(true);
		flecheBas.setImage(ihm.i.getFlecheBasDisab());
		flecheBas.setDisable(true);
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
		setIconeSablierAmpouleNoir();
		setIconeSablierAmpouleBlanc();
	}
	
	private void setIconeSablierAmpouleNoir() {
		if (m.getRenjou().getJoueurs()[0].getType() == TypeJoueur.Humain) {
			iconeNoir.setImage(ihm.i.getAmpoule());
		} else {
			iconeNoir.setImage(ihm.i.getSablier());
		}
	}
	
	private void setIconeSablierAmpouleBlanc() {
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
	private void dragDebutFlecheHaut() {
		String theme=m.getRenjou().getEmplacementThemes();
		if (!flecheHaut.isDisabled())
			flecheHaut.setImage(ihm.i.getFlecheHautDrag());
	}
	
	@FXML
	private void dragFinFlecheHaut() {
		String theme=m.getRenjou().getEmplacementThemes();
		if (!flecheHaut.isDisabled())
			flecheHaut.setImage(ihm.i.getFlecheHaut());
	}
	
	@FXML
	private void dragDebutFlecheBas() {
		String theme=m.getRenjou().getEmplacementThemes();
		if (!flecheBas.isDisabled())
			flecheBas.setImage(ihm.i.getFlecheBasDrag());
	}
	
	@FXML
	private void dragFinFlecheBas() {
		String theme=m.getRenjou().getEmplacementThemes();
		if (!flecheBas.isDisabled())
			flecheBas.setImage(ihm.i.getFlecheBas());
	}
	
	@FXML
	private void clickAffichageTabou1(MouseEvent e) {
		if (ihm.etatTuto != 6) {
			setDisabled();
			tabousMenu(null);
			ihm.efm.tabou1DebutDrag(null);
		}	
	}
	
	@FXML
	private void clickAffichageTabou2(MouseEvent e) {
		if (ihm.etatTuto != 6) {
			setDisabled();
			tabousMenu(null);
			ihm.efm.tabou2DebutDrag(null);
		}	
	}
	
	@FXML
	private void clickAffichageTabou3(MouseEvent e) {
		if (ihm.etatTuto != 6) {
			setDisabled();
			tabousMenu(null);
			ihm.efm.tabou3DebutDrag(null);
		}	
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
		setDisabled();
		ihm.fm.setNouvellePartie(true);
		ihm.fm.montrer();
		ihm.fm.setAlwaysOnTop(true);
		ihm.efm.getTabPane().getSelectionModel().select(0);
		if (ihm.etatTuto == 8) {
			imageTuto.setImage(null);
			ihm.efm.update();
		}	
	}
	
	@FXML
	private void sauvegarderMenu(ActionEvent e) {
		if (ihm.etatTuto != 8) {
			String fichierSauvegarder = null;
	        JFileChooser ouvertureFenetre = new JFileChooser();
	        int checkEtatFenetre = ouvertureFenetre.showSaveDialog(null);
	        if (checkEtatFenetre == ouvertureFenetre.APPROVE_OPTION) {
	            fichierSauvegarder = ouvertureFenetre.getSelectedFile().getAbsolutePath();
	            m.sauvegarder(fichierSauvegarder);
	        }
		}
	}
	
	@FXML
	private void chargerMenu(ActionEvent e) {
		if (ihm.etatTuto != 8) {
			String fichierCharger = null;
	        JFileChooser ouvertureFenetre = new JFileChooser();
	        int checkEtatFenetre = ouvertureFenetre.showOpenDialog(null);
	        if (checkEtatFenetre == ouvertureFenetre.APPROVE_OPTION) {
	            fichierCharger = ouvertureFenetre.getSelectedFile().getAbsolutePath();
	            m.charger(fichierCharger);
	            ihm.efm.selectionnerTheme(m.getRenjou().getEmplacementThemes());
	            ihm.efm.setAncienTheme("");
	        }
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
    		if (ihm.modeTuto && (ihm.etatTuto == 3 || ihm.etatTuto ==4)) {
    			ihm.etatTuto = ihm.etatTuto+1;
    			imageTuto.setImage(ihm.i.getImagesTuto().get(ihm.etatTuto-1));
    		}
    	}
   	}
	
	@FXML
	private void joueurMenu(ActionEvent e) {
		setDisabled();
		ihm.fm.setNouvellePartie(false);
		ihm.fm.montrer();
		ihm.fm.setAlwaysOnTop(true);
		ihm.efm.getTabPane().getSelectionModel().select(0);
	}
	
	@FXML
	private void tabousMenu(ActionEvent e) {
		setDisabled();
		ihm.fm.setNouvellePartie(false);
		ihm.fm.montrer();
		ihm.fm.setAlwaysOnTop(true);
		ihm.efm.getTabPane().getSelectionModel().select(1);
	}
	
	@FXML
	private void themeMenu(ActionEvent e) {
		setDisabled();
		ihm.fm.setNouvellePartie(false);
		ihm.fm.montrer();
		ihm.fm.setAlwaysOnTop(true);
		ihm.efm.getTabPane().getSelectionModel().select(2);
	}
	
	@FXML
	private void reglesMenu(ActionEvent e) {
		setDisabled();
		ihm.fa.montrer();
		ihm.fa.setAlwaysOnTop(true);
		ihm.efa.getTabPane().getSelectionModel().select(0);
	}
	
	@FXML
	private void tutorielMenu(ActionEvent e) {
		ihm.modeTuto = true;
		ArrayList<TypeTabous> tabous = new ArrayList<TypeTabous>();
		tabous.add(TypeTabous.TROIS_TROIS);
		tabous.add(TypeTabous.QUATRE_QUATRE);
		tabous.add(TypeTabous.SIX_SEPT);
        m.configurerPartie(TypeJoueur.Humain, TypeJoueur.Humain, tabous, true, true);
		update();
	}
	
	@FXML
	private void aProposMenu(ActionEvent e) {
		setDisabled();
		ihm.fa.montrer();
		ihm.fa.setAlwaysOnTop(true);
		ihm.efa.getTabPane().getSelectionModel().select(1);
	}
	
	public void update() {
		//Mode Tuto
		if (ihm.modeTuto && ihm.etatTuto == 1) {
			imageTuto.setImage(ihm.i.getImagesTuto().get(ihm.etatTuto-1));
		}
		
		if (m.getRenjou().getEtatPartie() == EtatPartie.EnCours) {
			seuleFois = false;
		}
		//Panel Gauche
		pionNoir.setImage(ihm.i.getPionNoir());
		pionBlanc.setImage(ihm.i.getPionBlanc());
		labelNoir.setText(m.getRenjou().getJoueurs()[0].getType().toString());
		labelBlanc.setText(m.getRenjou().getJoueurs()[1].getType().toString());
		nbPieceNoir.setText(Integer.toString(m.getRenjou().getJoueurs()[0].getNbPion()));
		nbPieceBlanc.setText(Integer.toString(m.getRenjou().getJoueurs()[1].getNbPion()));
		
		//Pointille sur joueur en cours
		if (m.getRenjou().getJoueurCourant() == 0 && m.getRenjou().getEtatPartie() == EtatPartie.EnCours) {
			panelJ2.setStyle("-fx-border-width : 5;-fx-border-color : gray;");
			panelJ1.setStyle("-fx-border-width : 5;-fx-border-color : yellow;");
		} else if (m.getRenjou().getJoueurCourant() == 1 && m.getRenjou().getEtatPartie() == EtatPartie.EnCours) {
			panelJ1.setStyle("-fx-border-width : 5;-fx-border-color : gray;");
			panelJ2.setStyle("-fx-border-width : 5;-fx-border-color : yellow;");
		} else {
			panelJ1.setStyle("-fx-border-width : 5;-fx-border-color : gray;");
			panelJ2.setStyle("-fx-border-width : 5;-fx-border-color : gray;");
		}
		
		setIconeSablierAmpoule();
		//Plateau
		repeindrePlateau();		
		disabEnabAnnulerRefaire();
		//Voir si partie gagne
		partieGagne();

		fond.setImage(ihm.i.getFond());
		tabou1Image.setImage(ihm.i.getTroisTroisImage());
		tabou2Image.setImage(ihm.i.getQuatreQuatreImage());
		tabou3Image.setImage(ihm.i.getSixSeptImage());
		recommencer.setImage(ihm.i.getRecommencer());
		
		//Disable les clic tabou si non actif
		boolean troisTroisActif = m.getRenjou().getTabouJeu().estDansListeTabous(TypeTabous.TROIS_TROIS);
		tabou1.setDisable(!troisTroisActif);
		tabou1Panel.setDisable(!troisTroisActif);
		if (!troisTroisActif) {
			tabou1Panel.setStyle("-fx-background-color : black;");
		} else {
			tabou1Panel.setStyle(null);
		}
		boolean quatreQuatreActif = m.getRenjou().getTabouJeu().estDansListeTabous(TypeTabous.QUATRE_QUATRE);
		tabou2.setDisable(!quatreQuatreActif);
		tabou2Panel.setDisable(!quatreQuatreActif);
		if (!quatreQuatreActif) {
			tabou2Panel.setStyle("-fx-background-color : black;");
		} else {
			tabou2Panel.setStyle(null);
		}
		boolean sixSeptActif = m.getRenjou().getTabouJeu().estDansListeTabous(TypeTabous.SIX_SEPT);
		tabou3.setDisable(!sixSeptActif);
		tabou3Panel.setDisable(!sixSeptActif);
		if (!sixSeptActif) {
			tabou3Panel.setStyle("-fx-background-color : black;");
		} else {
			tabou3Panel.setStyle(null);
		}
		
		//Desactiver sablier lorsque tour adverse
		if (m.getRenjou().getJoueurCourant() == 0) {
			iconeNoir.setDisable(false);
			setIconeSablierAmpouleNoir();
			iconeBlanc.setDisable(true);
			iconeBlanc.setImage(ihm.i.getImageVide());
			tempsBlanc.setText("");
			tempsNoir.setText("");
		} else {
			iconeBlanc.setDisable(false);
			setIconeSablierAmpouleBlanc();
			iconeNoir.setDisable(true);
			iconeNoir.setImage(ihm.i.getImageVide());
			tempsBlanc.setText("");
			tempsNoir.setText("");
		}
		
		//Gere le panel annuler refaire
		panelAnnulerRefaire();
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
		
		//Si c'est au tour de l'IA de jouer on desactive annuler et refaire
		if (m.getRenjou().getJoueurs()[m.getRenjou().getJoueurCourant()].getType() != TypeJoueur.Humain 
				&& m.getRenjou().getEtatPartie() == EtatPartie.EnCours) {
			annuler.setImage(ihm.i.getAnnulerDisab());
			annuler.setDisable(true);
			refaire.setImage(ihm.i.getRefaireDisab());
			refaire.setDisable(true);
		}
		
		//Image pour le panel est
		listImage1.setImage(ihm.i.getPlateau());
		listImage2.setImage(ihm.i.getPlateau());
		listImage3.setImage(ihm.i.getPlateau());
		listImage4.setImage(ihm.i.getPlateau());
	}

	private void partieGagne() {
		if (!seuleFois) {
	    	String message;
	    	if (!(m.getRenjou().getEtatPartie() == EtatPartie.EnCours)) {
		    	if (m.getRenjou().getEtatPartie() == EtatPartie.NoirGagne) {
		    		message = "Le joueur noir a gagne la partie !";
		    	} else if (m.getRenjou().getEtatPartie() == EtatPartie.BlancGagne) {
		    		message = "Le joueur blanc a gagne la partie !";
		    	} else if (m.getRenjou().getEtatPartie() == EtatPartie.BlancGagneParTabou) {
		    		message = "Le joueur blanc a gagne la partie grace a un tabou !";
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
		    	seuleFois = true;
	    	}
		}	
    }
	
	private void repeindrePlateau() {
		Image plat = ihm.i.getPlateau();

		Canvas canvas = new Canvas(plat.getWidth(),plat.getHeight());
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		
		//On dessine l'image dans le canvas
		graphicsContext.drawImage(plat, 0, 0);
		
		//On ajoute les pions
		PionJoue pion = null;
		Image iPionBlanc;
		Image iPionNoir;
		Image croixRouge = ihm.i.getCroixRouge();
		Image cercleVert = ihm.i.getCercleVert();
		for (int i=0;i<m.getRenjou().getPlateauDeJeu().getPlateau().length;i++) {
			for (int j=0;j<m.getRenjou().getPlateauDeJeu().getPlateau()[i].length;j++) {
				TypeCase p = m.getRenjou().getPlateauDeJeu().getPlateau()[i][j]; 
				if (m.getRenjou().getListeAnnuler().size() > 0)
					pion = m.getRenjou().getListeAnnuler().get(m.getRenjou().getListeAnnuler().size()-1);
				if (pion != null && pion.getCoordonnees().getColonne() == j && pion.getCoordonnees().getLigne() == i) {
					iPionBlanc = ihm.i.getPionBlancJoue();
					iPionNoir = ihm.i.getPionNoirJoue();
				} else {
					iPionBlanc = ihm.i.getPionBlanc();
					iPionNoir = ihm.i.getPionNoir();
				}
			    if (p == TypeCase.PionBlanc) {
			        dessinerPion(plat, graphicsContext, iPionBlanc, i, j);
			    } else if (m.getRenjou().estModeDebutant() && p == TypeCase.Tabou) {
			    	dessinerPion(plat, graphicsContext, croixRouge, i, j);
			    } else if (m.getRenjou().estModeDebutant() && p == TypeCase.Jouable && (m.premierCoup() || m.deuxiemeCoup())) {
			        dessinerPion(plat, graphicsContext, cercleVert, i, j);
			    } else if (p == TypeCase.PionNoir) {
			        dessinerPion(plat, graphicsContext, iPionNoir, i, j);
			    }    
			}
		}
		//On retransforme en Image
		WritableImage writableImage = new WritableImage((int)plat.getWidth(), (int)plat.getHeight());
		canvas.snapshot(null, writableImage);
		
		Image i = (Image) writableImage;
		plateau.setImage(i);
	}   
	
	private void dessinerPion(Image plat, GraphicsContext graphicsContext, Image image,int i, int j) {	
    	double width = (plat.getWidth())/16;
    	double height = (plat.getHeight())/16;
    	double x = (j*width)+(width/2);
        double y = (i*height)+(height/2);
        graphicsContext.drawImage(image, x, y, width, height);
	}
	
	@FXML
	private void peindreAide() {
		if (ihm.etatTuto == 7) {
			ihm.etatTuto = ihm.etatTuto+1;
			imageTuto.setImage(ihm.i.getImagesTuto().get(ihm.etatTuto-1));
		}
		
		if (m.getRenjou().getJoueurs()[m.getRenjou().getJoueurCourant()].getType() == TypeJoueur.Humain) {
			this.repeindrePlateau();
			Coordonnees c = m.aide();
			Canvas canvas = new Canvas(plateau.getImage().getWidth(),plateau.getImage().getHeight());
			GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
			
			//On dessine l'image dans le canvas
			graphicsContext.drawImage(plateau.getImage(), 0, 0);
			
			dessinerPion(plateau.getImage(), graphicsContext, ihm.i.getCercleVertPlein() , c.getLigne(), c.getColonne());
			
			//On retransforme en Image
			WritableImage writableImage = new WritableImage((int)plateau.getImage().getWidth(), (int)plateau.getImage().getHeight());
			canvas.snapshot(null, writableImage);
			
			Image i = (Image) writableImage;
			plateau.setImage(i);
		}	
	}
	
	private void panelAnnulerRefaire() {
		
    	this.list1.setText("");
    	this.list2.setText("");
    	this.list3.setText("");
    	this.list4.setText("");
		
    	int indiceFinTab = m.getRenjou().getIndiceFinHistorique();
		int indiceDebutTab = m.getRenjou().getIndiceDebutHistorique();
    	int tailleAnulleRefaire = indiceFinTab - indiceDebutTab;
    	
    	if(tailleAnulleRefaire < 4){
    		m.getRenjou().setIndiceFinHistorique(tailleAnulleRefaire);
    		m.getRenjou().setIndiceDebutHistorique(0);
    		
    		indiceDebutTab = 0;
    		indiceFinTab = m.getRenjou().getListeAnnuler().size() + m.getRenjou().getListeRefaire().size();
    	}
    	
		int i = indiceDebutTab;
		if(i != indiceFinTab){
			if(i == m.getRenjou().getNbDemiTourCourant()){
				listImage1.setStyle("-fx-background-color:black;");
				list1.setText("Tour "+i +" En Cours");
			}else{
				listImage1.setStyle(null);
				list1.setText("Tour "+i);
			}
			
			i++;
		}
		if(i != indiceFinTab){
			if(i == m.getRenjou().getNbDemiTourCourant()){
				listImage2.setStyle("-fx-background-color:black;");
				list2.setText("Tour "+i +" En Cours");
			}else{
				listImage2.setStyle(null);
				list2.setText("Tour "+i);
			}
			
			i++;
		}
		if(i != indiceFinTab){
			if(i == m.getRenjou().getNbDemiTourCourant()){
				listImage3.setStyle("-fx-background-color:black;");
				list3.setText("Tour "+i +" En Cours");
			}else{
				listImage3.setStyle(null);
				list3.setText("Tour "+i);
			}
			
			i++;
		}
		if(i != indiceFinTab){
			if(i == m.getRenjou().getNbDemiTourCourant()){
				listImage4.setStyle("-fx-background-color:black;");
				list4.setText("Tour "+i +" En Cours");
			}else{
				listImage4.setStyle(null);
				list4.setText("Tour "+i);
			}
			
			i++;
		}
			
		//Disable ou non la fleche haut
		if(m.getRenjou().getIndiceDebutHistorique() == 0){
			flecheHaut.setImage(ihm.i.getFlecheHautDisab());
			flecheHaut.setDisable(true);
		}else{
			flecheHaut.setDisable(false);
			flecheHaut.setImage(ihm.i.getFlecheHaut());
		}
		
		//Disable ou non la fleche bas
		if((m.getRenjou().getIndiceFinHistorique() == m.getRenjou().getListeAnnuler().size() + m.getRenjou().getListeRefaire().size()) || m.getRenjou().getIndiceFinHistorique() - m.getRenjou().getIndiceDebutHistorique() <4){
			flecheBas.setImage(ihm.i.getFlecheBasDisab());
			flecheBas.setDisable(true);
		}else{
			flecheBas.setDisable(false);
			flecheBas.setImage(ihm.i.getFlecheBas());
		}
		
	}
	@FXML
	private void clickFlecheHaut(MouseEvent e){
		int indiceDebutTab = m.getRenjou().getIndiceDebutHistorique();		
		if(indiceDebutTab > 0){
			m.getRenjou().setIndiceDebutHistorique(m.getRenjou().getIndiceDebutHistorique() -1);
			m.getRenjou().setIndiceFinHistorique(m.getRenjou().getIndiceFinHistorique() -1);			
		}
		panelAnnulerRefaire();
	}
	@FXML
	private void clickFlecheBas(MouseEvent e){

		int indiceFinTab = m.getRenjou().getIndiceFinHistorique();
		if(indiceFinTab < m.getRenjou().getListeAnnuler().size() + m.getRenjou().getListeRefaire().size()){
			m.getRenjou().setIndiceDebutHistorique(m.getRenjou().getIndiceDebutHistorique() + 1);
			m.getRenjou().setIndiceFinHistorique(m.getRenjou().getIndiceFinHistorique() + 1);
		}
		panelAnnulerRefaire();
	}
	
	@FXML
	private void clickList1(MouseEvent e){
		clickList(0);
	}
	
	@FXML
	private void clickList2(MouseEvent e){
		clickList(1);
	}
	
	@FXML
	private void clickList3(MouseEvent e){
		clickList(2);
	}
	
	@FXML
	private void clickList4(MouseEvent e){
		clickList(3);
	}
	
	private void clickList(int i){
		int indiceFinTab = m.getRenjou().getIndiceFinHistorique();
		int indiceDebutTab = m.getRenjou().getIndiceDebutHistorique();
		if(indiceFinTab - i >= 0){
			ancienPlateau = null;
			int nbCoupAAnulleeOuRefaire = m.getRenjou().getNbDemiTourCourant() - (indiceDebutTab + i);
			if(nbCoupAAnulleeOuRefaire < 0){
				nbCoupAAnulleeOuRefaire = 0 - nbCoupAAnulleeOuRefaire;
				m.refaireNDemiCoup(nbCoupAAnulleeOuRefaire);
			}else if(nbCoupAAnulleeOuRefaire > 0){
				m.annulerNDemiCoup(nbCoupAAnulleeOuRefaire);
			}
		}
	}

	@FXML
	private void dragFinList1(MouseEvent e){
		if (ancienPlateau != null) {
			plateau.setImage(ancienPlateau);
		}
		ancienPlateau = null;
	}
	@FXML
	private void dragFinList2(MouseEvent e){
		if (ancienPlateau != null) {
			plateau.setImage(ancienPlateau);
		}
		ancienPlateau = null;
	}
	@FXML
	private void dragFinList3(MouseEvent e){
		if (ancienPlateau != null) {
			plateau.setImage(ancienPlateau);
		}
		ancienPlateau = null;	}
	@FXML
	private void dragFinList4(MouseEvent e){
		if (ancienPlateau != null) {
			plateau.setImage(ancienPlateau);
		}
		ancienPlateau = null;	}
	
	@FXML
	private void dragDebutList1(MouseEvent e){
		dragList(0);
	}
	@FXML
	private void dragDebutList2(MouseEvent e){
		dragList(1);
	}
	@FXML
	private void dragDebutList3(MouseEvent e){
		dragList(2);
	}
	@FXML
	private void dragDebutList4(MouseEvent e){
		dragList(3);
	}
	
	private void dragList(int i){
		int indiceFinTab = m.getRenjou().getIndiceFinHistorique();
		int indiceDebutTab = m.getRenjou().getIndiceDebutHistorique();
		
		if(indiceFinTab - i >= 0){
			int nbCoupAAnulleeOuRefaire = m.getRenjou().getNbDemiTourCourant() - (indiceDebutTab + i);
			if(nbCoupAAnulleeOuRefaire < 0){
				//Refaire
				//affichage des pions jusqu'a pos courant puis affichage transparent
				afficherPlateauPionTransparent(indiceDebutTab+i, true);
			}else if(nbCoupAAnulleeOuRefaire > 0){
				//Annuler
				//affichage des pions jusqu'a clique (indiceFinTab-i) puis affichage transparent
				afficherPlateauPionTransparent(indiceDebutTab + i, false);
			}
		}
	}

	private void afficherPlateauPionTransparent(int bornePion, boolean type) {
		ancienPlateau = plateau.getImage();

		
		//false annuler
		Image plat = ihm.i.getPlateau();

		Canvas canvas = new Canvas(plat.getWidth(),plat.getHeight());
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		
		//On dessine l'image dans le canvas
		graphicsContext.drawImage(plat, 0, 0);
		
		//On ajoute les pions
		PionJoue pion = null;
		Image iPionBlanc = ihm.i.getPionBlanc();
		Image iPionNoir = ihm.i.getPionNoir();
		//true refaire
		if (type) {
					
			//On dessine l'état actuel
			for (int i=0;i<m.getRenjou().getListeAnnuler().size();i++) {
					PionJoue p = m.getRenjou().getListeAnnuler().get(i); 
				    if (p.getTypeCase() == TypeCase.PionBlanc) {
				        dessinerPion(plat, graphicsContext, iPionBlanc, p.getCoordonnees().getLigne(), p.getCoordonnees().getColonne());
				    } else if (p.getTypeCase() == TypeCase.PionNoir) {
				        dessinerPion(plat, graphicsContext, iPionNoir, p.getCoordonnees().getLigne(), p.getCoordonnees().getColonne());
				    }    
			}
			//On dessine en transparent ce qui peut être ajouté
			iPionBlanc = ihm.i.getCercleVertPlein();
			iPionNoir = ihm.i.getCercleVertPlein();
			for (int i=0;i<bornePion - m.getRenjou().getListeAnnuler().size();i++) {
				PionJoue p = m.getRenjou().getListeRefaire().get( m.getRenjou().getListeRefaire().size() -i -1); 
			    if (p.getTypeCase() == TypeCase.PionBlanc) {
			        dessinerPion(plat, graphicsContext, iPionBlanc, p.getCoordonnees().getLigne(), p.getCoordonnees().getColonne());
			    } else if (p.getTypeCase() == TypeCase.PionNoir) {
			        dessinerPion(plat, graphicsContext, iPionNoir, p.getCoordonnees().getLigne(), p.getCoordonnees().getColonne());
			    }      
			}
		} else {
			//On dessine l'état actuel
			for (int i=0;i<bornePion && i<m.getRenjou().getListeAnnuler().size(); i++) {
					PionJoue p = m.getRenjou().getListeAnnuler().get(i); 
				    if (p.getTypeCase() == TypeCase.PionBlanc) {
				        dessinerPion(plat, graphicsContext, iPionBlanc, p.getCoordonnees().getLigne(), p.getCoordonnees().getColonne());
				    } else if (p.getTypeCase() == TypeCase.PionNoir) {
				        dessinerPion(plat, graphicsContext, iPionNoir, p.getCoordonnees().getLigne(), p.getCoordonnees().getColonne());
				    }    
			}
			//On dessine en transparent ce qui peut être ajouté
			iPionBlanc = ihm.i.getCercleVertPlein();
			iPionNoir = ihm.i.getCercleVertPlein();
			for (int i=bornePion; i<m.getRenjou().getListeAnnuler().size(); i++) {
				PionJoue p = m.getRenjou().getListeAnnuler().get(i); 
			    if (p.getTypeCase() == TypeCase.PionBlanc) {
			        dessinerPion(plat, graphicsContext, iPionBlanc, p.getCoordonnees().getLigne(), p.getCoordonnees().getColonne());
			    } else if (p.getTypeCase() == TypeCase.PionNoir) {
			        dessinerPion(plat, graphicsContext, iPionNoir, p.getCoordonnees().getLigne(), p.getCoordonnees().getColonne());
			    }     
			}
		}
		
		//On retransforme en Image
				WritableImage writableImage = new WritableImage((int)plat.getWidth(), (int)plat.getHeight());
				canvas.snapshot(null, writableImage);
				
				Image i = (Image) writableImage;
				plateau.setImage(i);
		
	}
	
	public void setDisabled() {
		labelNoir.setDisable(true);
	    pionNoir.setDisable(true);
	    nbPieceNoir.setDisable(true);
	    iconeNoir.setDisable(true);
	    tempsNoir.setDisable(true);
	    annuler.setDisable(true);
	    refaire.setDisable(true);
	    labelBlanc.setDisable(true);
	    pionBlanc.setDisable(true);
	    nbPieceBlanc.setDisable(true);
	    iconeBlanc.setDisable(true);
	    tempsBlanc.setDisable(true);
	    recommencer.setDisable(true);
	    plateau.setDisable(true);
	    panelJ1.setDisable(true);
	    panelJ2.setDisable(true);
	    fond.setDisable(true);
	    tabou1Image.setDisable(true);
	    tabou2Image.setDisable(true);
	    tabou3Image.setDisable(true);
	    tabou1.setDisable(true);
	    tabou2.setDisable(true);
	    tabou3.setDisable(true);
	    tabou1Panel.setDisable(true);
	    tabou2Panel.setDisable(true);
	    tabou3Panel.setDisable(true);
	    list1.setDisable(true);
	    list2.setDisable(true);
	    list3.setDisable(true);
	    list4.setDisable(true);
	    listImage1.setDisable(true);
	    listImage2.setDisable(true);
	    listImage3.setDisable(true);
	    listImage4.setDisable(true);
	    flecheHaut.setDisable(true);
	    flecheBas.setDisable(true);
	}
	
	public void setEnabled() {
		labelNoir.setDisable(false);
	    pionNoir.setDisable(false);
	    nbPieceNoir.setDisable(false);
	    iconeNoir.setDisable(false);
	    tempsNoir.setDisable(false);
	    annuler.setDisable(false);
	    refaire.setDisable(false);
	    labelBlanc.setDisable(false);
	    pionBlanc.setDisable(false);
	    nbPieceBlanc.setDisable(false);
	    iconeBlanc.setDisable(false);
	    tempsBlanc.setDisable(false);
	    recommencer.setDisable(false);
	    plateau.setDisable(false);
	    panelJ1.setDisable(false);
	    panelJ2.setDisable(false);
	    fond.setDisable(false);
	    tabou1Image.setDisable(false);
	    tabou2Image.setDisable(false);
	    tabou3Image.setDisable(false);
	    tabou1.setDisable(false);
	    tabou2.setDisable(false);
	    tabou3.setDisable(false);
	    tabou1Panel.setDisable(false);
	    tabou2Panel.setDisable(false);
	    tabou3Panel.setDisable(false);
	    list1.setDisable(false);
	    list2.setDisable(false);
	    list3.setDisable(false);
	    list4.setDisable(false);
	    listImage1.setDisable(false);
	    listImage2.setDisable(false);
	    listImage3.setDisable(false);
	    listImage4.setDisable(false);
	    flecheHaut.setDisable(false);
	    flecheBas.setDisable(false);
	}
	
	public void Tutoriel() {
		if (ihm.etatTuto== 1 || ihm.etatTuto == 2 || ihm.etatTuto == 5 || ihm.etatTuto == 6) {
			ihm.etatTuto = (ihm.etatTuto+1);
			imageTuto.setImage(ihm.i.getImagesTuto().get(ihm.etatTuto-1));
		}
	}
	
	@FXML
	private void clicOnTuto() {
		Tutoriel();
	}
}
