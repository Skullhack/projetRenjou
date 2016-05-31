package IHMFx;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Controleur.Moteur;
import Enum.TypeJoueur;
import Enum.TypeTabous;
import IHMFx.FenetreAide;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EcouteurFenetreRenjou {
	private IHM ihm;
	private Moteur m;
	@FXML
	private ImageView partieRapide;
	@FXML
	private ImageView partiePersonnalisee;
	@FXML
	private ImageView chargerPartie;
	@FXML
	private ImageView lancerTutoriel;
	@FXML
	private ImageView quitter;
	
	
	public EcouteurFenetreRenjou(IHM ihm) {
		this.ihm = ihm;
		this.m = ihm.m;
		try {
			ihm.fr = new FenetreRenjou(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void boutonPartieRapideDebutDrag() {
		partieRapide.setImage(ihm.i.getPartieRapideDrag());
	}
	
	@FXML
	private void boutonPartieRapideFinDrag() {
		partieRapide.setImage(ihm.i.getPartieRapide());
	}
	
	@FXML
	private void boutonPartiePersonnaliseeDebutDrag() {
		partiePersonnalisee.setImage(ihm.i.getPartiePersonnaliseeDrag());
	}
	
	@FXML
	private void boutonPartiePersonnaliseeFinDrag() {
		partiePersonnalisee.setImage(ihm.i.getPartiePersonnalisee());
	}
	
	@FXML
	private void boutonChargerPartieDebutDrag() {
		chargerPartie.setImage(ihm.i.getChargerPartieDrag());
	}
	
	@FXML
	private void boutonChargerPartieFinDrag() {
		chargerPartie.setImage(ihm.i.getChargerPartie());
	}
	
	@FXML
	private void boutonLancerTutorielDebutDrag() {
		lancerTutoriel.setImage(ihm.i.getLancerTutorielDrag());
	}
	
	@FXML
	private void boutonLancerTutorielFinDrag() {
		lancerTutoriel.setImage(ihm.i.getLancerTutoriel());
	}
	
	@FXML
	private void boutonQuitterDebutDrag() {
		quitter.setImage(ihm.i.getQuitterDrag());
	}
	
	@FXML
	private void boutonQuitterFinDrag() {
		quitter.setImage(ihm.i.getQuitter());
	}
	
	@FXML
	private void partieRapide(MouseEvent e) {
		ihm.fr.hide();
		ihm.fj.montrer();
	}
	
	@FXML
	private void partiePersonnalisee(MouseEvent e) {
		ihm.fr.hide();
		ihm.fm.setNouvellePartie(true);
		ihm.fm.montrer();
		ihm.fm.setAlwaysOnTop(true);
		ihm.efm.getTabPane().getSelectionModel().select(0);
	}
	
	@FXML
	private void chargerPartie(MouseEvent e) {
		String fichierCharger = null;
        JFileChooser ouvertureFenetre = new JFileChooser();
        int checkEtatFenetre = ouvertureFenetre.showOpenDialog(null);
        if (checkEtatFenetre == ouvertureFenetre.APPROVE_OPTION) {
            fichierCharger = ouvertureFenetre.getSelectedFile().getAbsolutePath();
            m.charger(fichierCharger);
            ihm.efm.selectionnerTheme(m.getRenjou().getEmplacementThemes());
            ihm.efm.setAncienTheme("");
            ihm.fr.hide();
            ihm.fj.montrer();
        }
	}
	
	@FXML
	private void lancerTutoriel(MouseEvent e) {
		ihm.fr.hide();
		m.setModeTuto(true);
		ArrayList<TypeTabous> tabous = new ArrayList<TypeTabous>();
		tabous.add(TypeTabous.TROIS_TROIS);
		tabous.add(TypeTabous.QUATRE_QUATRE);
		tabous.add(TypeTabous.SIX_SEPT);
        m.configurerPartie(TypeJoueur.Humain, TypeJoueur.Humain, tabous, true, true);
		ihm.fj.montrer();
		ihm.efj.update();
	}
	
	@FXML
	private void quitter(MouseEvent e) {
		System.exit(0);
	}
}

