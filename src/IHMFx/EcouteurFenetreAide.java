package IHMFx;

import java.io.IOException;

import IHMFx.FenetreAide;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;

public class EcouteurFenetreAide {
	private IHM ihm;
	//private Moteur m;
	@FXML
	private ImageView fond;
	@FXML
	private TabPane tabs;
	@FXML
	private ImageView boutonRetour;
	
	
	public EcouteurFenetreAide(IHM ihm) {
		this.ihm = ihm;
		//this.m = ihm.m;
		try {
			ihm.fa = new FenetreAide(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void boutonRetour() {
		ihm.fj.montrer();
		ihm.efj.setEnabled();
		ihm.fa.cacher();
	}
	
	@FXML
	private void boutonRetourDebutDrag() {
		boutonRetour.setImage(ihm.i.getBoutonRetourDrag());
	}
	
	@FXML
	private void boutonRetourFinDrag() {
		boutonRetour.setImage(ihm.i.getBoutonRetour());
	}
	
	public TabPane getTabPane() {
		return tabs;
	}
	
	public void update() {
		fond.setImage(ihm.i.getFond());
		boutonRetour.setImage(ihm.i.getBoutonRetour());
	}
}

