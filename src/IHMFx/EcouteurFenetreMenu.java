package IHMFx;

import Controleur.Moteur;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EcouteurFenetreMenu {
	private IHM ihm;
	private Moteur m;
	@FXML
	private ImageView boutonValider;
	@FXML
	private ImageView boutonAnnuler;
	
	public EcouteurFenetreMenu(IHM ihm) {
		this.ihm = ihm;
		this.m = ihm.m;
	}
	
	@FXML
	private void boutonAnnuler(MouseEvent e) {
		ihm.fj.montrer();
		ihm.fm.cacher();
	}
	
	@FXML
	private void boutonValider(MouseEvent e) {
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
}
