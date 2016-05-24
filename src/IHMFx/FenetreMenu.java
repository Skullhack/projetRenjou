package IHMFx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FenetreMenu extends Stage{
	public FenetreMenu(EcouteurFenetreMenu efm) throws IOException {
        FXMLLoader fx2 = new FXMLLoader(getClass().getResource("LayoutFenetreMenu.fxml"));
		fx2.setController(efm);
		Parent root2 = fx2.load();
        this.setTitle("Menu");
        this.setScene(new Scene(root2, 700, 500));
        this.centerOnScreen();
	}
	
	public void montrer() {
		this.show();
	}
	
	public void toujoursMettreEnHaut() {
		this.setAlwaysOnTop(true);
	}
}
