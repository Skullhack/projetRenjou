package IHMFx;

import java.io.IOException;

import Controleur.Moteur;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EcouteurFenetreAffichageTabou {
	private IHM ihm;
	private Moteur m;
	@FXML
	private ImageView imageTabou;
	
	
	public EcouteurFenetreAffichageTabou(IHM ihm) {
		this.ihm = ihm;
		this.m = ihm.m;
		try {
			ihm.fat = new FenetreAffichageTabou(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setImageTabou(Image image) {
		imageTabou.setImage(image);
	}
}	