package IHMFx;

import java.io.IOException;
import java.util.ArrayList;

import Controleur.Moteur;
import Enum.TypeJoueur;
import Enum.TypeTabous;
import IHMFx.FenetreMenu;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EcouteurFenetreMenu {
	private IHM ihm;
	private Moteur m;
	@FXML
	private ImageView boutonValider;
	@FXML
	private ImageView boutonAnnuler;
	private ToggleGroup noir;
	private ToggleGroup blanc;
	@FXML
	private RadioButton noir1;
	@FXML
	private RadioButton noir2;
	@FXML
	private RadioButton noir3;
	@FXML
	private RadioButton noir4;
	@FXML
	private RadioButton noir5;
	@FXML
	private RadioButton blanc1;
	@FXML
	private RadioButton blanc2;
	@FXML
	private RadioButton blanc3;
	@FXML
	private RadioButton blanc4;
	@FXML
	private RadioButton blanc5;
	@FXML
	private TabPane tabs;
	@FXML
	private CheckBox tabou1;
	@FXML
	private CheckBox tabou2;
	@FXML
	private CheckBox tabou3;
	
	
	public EcouteurFenetreMenu(IHM ihm) {
		this.ihm = ihm;
		this.m = ihm.m;
		try {
			ihm.fm = new FenetreMenu(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		noir = new ToggleGroup();
		blanc = new ToggleGroup();
		noir1.setToggleGroup(noir);
		noir2.setToggleGroup(noir);
		noir3.setToggleGroup(noir);
		noir4.setToggleGroup(noir);
		noir5.setToggleGroup(noir);
		blanc1.setToggleGroup(blanc);
		blanc2.setToggleGroup(blanc);
		blanc3.setToggleGroup(blanc);
		blanc4.setToggleGroup(blanc);
		blanc5.setToggleGroup(blanc);
	}
	
	@FXML
	private void boutonAnnuler(MouseEvent e) {
		annuler();
	}
	
	public void annuler() {
		ihm.fj.montrer();
		ihm.fm.cacher();
	}
	
	@FXML
	private void boutonValider(MouseEvent e) {
		TypeJoueur[] tab = new TypeJoueur[2];
		ArrayList<TypeTabous> tabous = new ArrayList<TypeTabous>();
		
		//Valeurs sélectionnées radiobutton joueur
		if(noir1.isSelected()) {
			tab[0] = TypeJoueur.values()[0];
		} else if(noir2.isSelected()) {
			tab[0] = TypeJoueur.values()[1];
		} else if(noir3.isSelected()) {
			tab[0] = TypeJoueur.values()[2];
		} else if(noir4.isSelected()) {
			tab[0] = TypeJoueur.values()[3];
		} else if(noir5.isSelected()) {
			tab[0] = TypeJoueur.values()[4];
		}
		
		if(blanc1.isSelected()) {
			tab[1] = TypeJoueur.values()[0];
		} else if(blanc2.isSelected()) {
			tab[1] = TypeJoueur.values()[1];
		} else if(blanc3.isSelected()) {
			tab[1] = TypeJoueur.values()[2];
		} else if(blanc4.isSelected()) {
			tab[1] = TypeJoueur.values()[3];
		} else if(blanc5.isSelected()) {
			tab[1] = TypeJoueur.values()[4];
		}
		
		//Valeurs sélectionnées dans tabou
		if (tabou1.isSelected()) {
			tabous.add(TypeTabous.QUATRE_QUATRE);
		}
		if (tabou2.isSelected()) {
			tabous.add(TypeTabous.SIX_SEPT);
		}
		if (tabou3.isSelected()) {
			tabous.add(TypeTabous.TROIS_TROIS);
		}
		
        if (tab[0] != null && tab[1] != null) {
        	//gerer charger et nouvelle partie : TODO
            m.configurerPartie(tab[0], tab[1], tabous, ihm.fm.getNouvellePartie());
        }
		ihm.fj.montrer();
		ihm.fm.cacher();
	}
	
	@FXML
	private void boutonValiderFinDrag(MouseEvent e) {
		boutonValider.setImage(ihm.i.getBoutonValider());
	}
	
	
	@FXML
	private void boutonValiderDebutDrag(MouseEvent e) {
		boutonValider.setImage(ihm.i.getBoutonValiderDrag());
	}
	
	@FXML
	private void boutonAnnulerDebutDrag(MouseEvent e) {
		boutonAnnuler.setImage(ihm.i.getBoutonAnnulerDrag());
	}
	
	@FXML
	private void boutonAnnulerFinDrag(MouseEvent e) {
		boutonAnnuler.setImage(ihm.i.getBoutonAnnuler());
	}
	
	public void selectionnerRadioJoueur() {
		if(m.getRenjou().getJoueurs()[0].getType() == TypeJoueur.values()[0]) {
			noir1.setSelected(true);
		} else if(m.getRenjou().getJoueurs()[0].getType() == TypeJoueur.values()[1]) {
			noir2.setSelected(true);
		} else if(m.getRenjou().getJoueurs()[0].getType() == TypeJoueur.values()[2]) {
			noir3.setSelected(true);
		} else if(m.getRenjou().getJoueurs()[0].getType() == TypeJoueur.values()[3]) {
			noir4.setSelected(true);
		} else if(m.getRenjou().getJoueurs()[0].getType() == TypeJoueur.values()[4]) {
			noir5.setSelected(true);
		}
		if(m.getRenjou().getJoueurs()[1].getType() == TypeJoueur.values()[0]) {
			blanc1.setSelected(true);
		} else if(m.getRenjou().getJoueurs()[1].getType() == TypeJoueur.values()[1]) {
			blanc2.setSelected(true);
		} else if(m.getRenjou().getJoueurs()[1].getType() == TypeJoueur.values()[2]) {
			blanc3.setSelected(true);
		} else if(m.getRenjou().getJoueurs()[1].getType() == TypeJoueur.values()[3]) {
			blanc4.setSelected(true);
		} else if(m.getRenjou().getJoueurs()[1].getType() == TypeJoueur.values()[4]) {
			blanc5.setSelected(true);
		}
	}
	
	public TabPane getTabPane() {
		return tabs;
	}
}
