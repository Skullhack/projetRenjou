package IHMFx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FenetreAffichageTabou extends Stage{	
	
	public FenetreAffichageTabou(EcouteurFenetreAffichageTabou efat) throws IOException {
		FXMLLoader fx = new FXMLLoader(getClass().getResource("LayoutFenetreAffichageTabou.fxml"));
		fx.setController(efat);
		Parent root = fx.load();
    	this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Aide");
        this.setScene(new Scene(root, 500, 400));
        this.centerOnScreen();
	}
	
	public void montrer() {
		this.show();
	}
	
	public void cacher() {
		this.hide();
	}
}