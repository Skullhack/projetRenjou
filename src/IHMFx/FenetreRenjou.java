package IHMFx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FenetreRenjou extends Stage{	
	private EcouteurFenetreRenjou efr;
	
	public FenetreRenjou(EcouteurFenetreRenjou efr) throws IOException {
        this.efr = efr;
		FXMLLoader fx = new FXMLLoader(getClass().getResource("LayoutFenetreRenjou.fxml"));
		fx.setController(efr);
		Parent root = fx.load();
    	this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Renjou");
        this.setScene(new Scene(root, 600, 400));
        this.centerOnScreen();
	}
	
	public void montrer() {
		this.show();
	}
	
	public void cacher() {
		this.hide();
	}
}
