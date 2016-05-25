package IHMFx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FenetreAide extends Stage{	
	private EcouteurFenetreAide efa;
	
	public FenetreAide(EcouteurFenetreAide efa) throws IOException {
        this.efa = efa;
		FXMLLoader fx = new FXMLLoader(getClass().getResource("LayoutFenetreAide.fxml"));
		fx.setController(efa);
		Parent root = fx.load();
    	this.setResizable(false);
        this.setTitle("Aide");
        this.setScene(new Scene(root, 700, 500));
        this.centerOnScreen();
	}
	
	public void montrer() {
		this.show();
	}
	
	public void cacher() {
		this.hide();
	}
}
